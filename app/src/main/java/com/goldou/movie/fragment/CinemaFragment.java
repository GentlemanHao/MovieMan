package com.goldou.movie.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.activity.CityActivity;
import com.goldou.movie.activity.MainActivity;
import com.goldou.movie.adapter.CinemaAdapter;
import com.goldou.movie.bean.CinemaInfo;
import com.goldou.movie.utils.OkHttpUtil;
import com.goldou.movie.utils.SpUtil;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class CinemaFragment extends Fragment implements View.OnClickListener {

    private XRecyclerView rl_cinema;
    private CinemaInfo allCinemaInfo;
    private RelativeLayout rl_region;
    private RelativeLayout rl_most;
    private RelativeLayout rl_brand;
    private RelativeLayout rl_server;
    private TextView tv_select_region;
    private TextView tv_select_most;
    private TextView tv_select_brand;
    private TextView tv_select_server;
    private ImageView iv_select_region;
    private ImageView iv_select_most;
    private ImageView iv_select_brand;
    private ImageView iv_select_server;
    private PopupWindow popupWindow;
    private int currentRegion = 0;
    private int currentMost = 0;
    private ListView lv_list;
    private int currentShow = -1;
    private List<String> regionList;
    private RelativeLayout rl_location;
    private TextView tv_location;
    private MainActivity mainActivity;
    private TextView tv_city;
    private LinearLayoutManager layoutManager;
    private View headerView;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (currentRegion == 0) {
                        rl_cinema.setAdapter(new CinemaAdapter(getContext(), allCinemaInfo.getData()));
                    } else {
                        showSelectCinemaList(regionList.get(currentRegion));
                    }
                    rl_cinema.refreshComplete();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cinema, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        rl_cinema = (XRecyclerView) view.findViewById(R.id.rl_cinema);
        layoutManager = new LinearLayoutManager(getContext());
        rl_cinema.setLayoutManager(layoutManager);
        rl_cinema.setRefreshProgressStyle(ProgressStyle.SysProgress);
        rl_cinema.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        rl_cinema.setArrowImageView(R.drawable.iconfont_downgrey);
        rl_cinema.setLoadingMoreEnabled(false);

        initHeader();

        rl_cinema.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();
            }

            @Override
            public void onLoadMore() {

            }
        });

        rl_cinema.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    rl_location.setVisibility(View.VISIBLE);
                } else {
                    rl_location.setVisibility(View.GONE);
                }
            }
        });

        rl_cinema.refresh();

        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CityActivity.class));
            }
        });
        tv_city.setText(SpUtil.getString(getContext(), "CITY", ""));
        mainActivity = (MainActivity) getActivity();
        registerBroadcastReceiver();

        rl_location = (RelativeLayout) view.findViewById(R.id.rl_location);
        rl_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_location.setText("正在定位，请稍候...");
                mainActivity.getLocation();
            }
        });

        tv_location = (TextView) view.findViewById(R.id.tv_location);

        mainActivity.getLocation();
    }

    private void initHeader() {
        headerView = View.inflate(getContext(), R.layout.cinema_header, null);
        rl_cinema.addHeaderView(headerView);

        rl_region = (RelativeLayout) headerView.findViewById(R.id.rl_region);
        rl_most = (RelativeLayout) headerView.findViewById(R.id.rl_most);
        rl_brand = (RelativeLayout) headerView.findViewById(R.id.rl_brand);
        rl_server = (RelativeLayout) headerView.findViewById(R.id.rl_server);

        tv_select_region = (TextView) headerView.findViewById(R.id.tv_select_region);
        tv_select_most = (TextView) headerView.findViewById(R.id.tv_select_most);
        tv_select_brand = (TextView) headerView.findViewById(R.id.tv_select_brand);
        tv_select_server = (TextView) headerView.findViewById(R.id.tv_select_server);

        iv_select_region = (ImageView) headerView.findViewById(R.id.iv_select_region);
        iv_select_most = (ImageView) headerView.findViewById(R.id.iv_select_most);
        iv_select_brand = (ImageView) headerView.findViewById(R.id.iv_select_brand);
        iv_select_server = (ImageView) headerView.findViewById(R.id.iv_select_server);

        rl_region.setOnClickListener(this);
        rl_most.setOnClickListener(this);
        rl_brand.setOnClickListener(this);
        rl_server.setOnClickListener(this);
    }

    private void initData() {
        OkHttpUtil.doGet("http://m.maoyan.com/cinemas.json", new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    formatData(response.body().string());
                }
            }
        });
    }

    private void formatData(String result) {
        String[] results = result.split("\\[");
        String data = "\"data\"" + ":";
        Gson gson = new Gson();
        allCinemaInfo = gson.fromJson("{" + data + "[" + "" + "]}", CinemaInfo.class);
        CinemaInfo cinemaInfo;
        regionList = new ArrayList<>();
        regionList.add("全部");
        for (int i = 1; i < results.length; i++) {
            String[] split = results[i].split("]");
            String cinema = split[0];
            cinema = "{" + data + "[" + cinema + "]}";
            cinemaInfo = gson.fromJson(cinema, CinemaInfo.class);
            regionList.add(cinemaInfo.getData().get(0).getArea());
            allCinemaInfo.getData().addAll(cinemaInfo.getData());
        }
        getDistance();
        handler.sendEmptyMessage(1);
    }

    //计算影院和我的距离
    private void getDistance() {
        if (allCinemaInfo.getData().size() > 0) {
            String latitude = SpUtil.getString(getActivity(), "latitude", "");
            String longitude = SpUtil.getString(getActivity(), "longitude", "");
            double latme = 0;
            double longme = 0;
            if (!TextUtils.isEmpty(latitude) && !TextUtils.isEmpty(longitude)) {
                latme = Double.parseDouble(latitude);
                longme = Double.parseDouble(longitude);
            }
            for (int i = 0; i < allCinemaInfo.getData().size(); i++) {
                double lat = allCinemaInfo.getData().get(i).getLat();
                double lng = allCinemaInfo.getData().get(i).getLng();
                float[] results = new float[1];
                Location.distanceBetween(latme, longme, lat, lng, results);
                allCinemaInfo.getData().get(i).setDistance(Math.round(results[0]));
            }
            /*for (int i = 0; i < allCinemaInfo.getData().size() - 1; i++) {
                for (int j = 0; j < allCinemaInfo.getData().size() - 1 - i; j++) {
                    if (allCinemaInfo.getData().get(j).getDistance() > allCinemaInfo.getData().get(j + 1).getDistance()) {
                        CinemaInfo.Data temp = allCinemaInfo.getData().get(j);
                        allCinemaInfo.getData().add(j, allCinemaInfo.getData().get(j + 1));
                        allCinemaInfo.getData().add(j + 1, temp);
                    }
                }
            }*/
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_region:
                showSelectData(regionList, iv_select_region, tv_select_region, 0);
                break;
            case R.id.rl_most:
                List<String> mostList = new ArrayList<>();
                mostList.add("离我最近");
                mostList.add("价格最低");
                mostList.add("好评优先");
                showSelectData(mostList, iv_select_most, tv_select_most, 1);
                break;
        }
    }

    private void showSelectData(final List<String> data, final ImageView imageView, final TextView textView, final int current) {

        if (popupWindow != null && popupWindow.isShowing()) {
            if (currentShow == current) {
                popupWindow.dismiss();
                return;
            } else {
                popupWindow.dismiss();
            }
        }

        currentShow = current;

        imageView.setImageResource(R.drawable.triangle_up);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        final View popupView = View.inflate(getContext(), R.layout.popup_list, null);
        lv_list = (ListView) popupView.findViewById(R.id.lv_list);

        if (data.size() >= 6) {
            ViewGroup.LayoutParams layoutParams = lv_list.getLayoutParams();
            layoutParams.height = dip2px(getContext(), 275);
            lv_list.setLayoutParams(layoutParams);
        }

        lv_list.setDivider(null);
        lv_list.setFocusable(true);
        lv_list.setFocusableInTouchMode(true);
        lv_list.setAdapter(new ListAdapter(data, current));

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (current == 0) {
                    currentRegion = position;
                    if (currentRegion == 0) {
                        handler.sendEmptyMessage(1);
                    } else {
                        showSelectCinemaList(data.get(currentRegion));
                    }
                } else if (current == 1) {
                    currentMost = position;
                }
                textView.setText(data.get(position));
                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(popupView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(headerView);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                imageView.setImageResource(R.drawable.triangle_down);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorSmall));
            }
        });
    }

    private void showSelectCinemaList(String region) {
        Gson gson = new Gson();
        CinemaInfo regionCinemaInfo = gson.fromJson("{" + "\"data\"" + ":" + "[" + "" + "]}", CinemaInfo.class);
        for (int i = 0; i < allCinemaInfo.getData().size(); i++) {
            if (allCinemaInfo.getData().get(i).getArea().equals(region)) {
                regionCinemaInfo.getData().add(allCinemaInfo.getData().get(i));
            }
        }
        rl_cinema.setAdapter(new CinemaAdapter(getContext(), regionCinemaInfo.getData()));
    }

    class ListAdapter extends BaseAdapter {

        private int current;
        private List<String> data;

        public ListAdapter(List<String> data, int current) {
            this.data = data;
            this.current = current;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.item_text, null);
                holder = new ViewHolder();
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_name.setText(data.get(position));
            if (current == 0) {
                holder.tv_name.setTextColor(position == currentRegion ?
                        ContextCompat.getColor(getContext(), R.color.colorPrimary) : ContextCompat.getColor(getContext(), R.color.colorSmall));
            } else if (current == 1) {
                holder.tv_name.setTextColor(position == currentMost ?
                        ContextCompat.getColor(getContext(), R.color.colorPrimary) : ContextCompat.getColor(getContext(), R.color.colorSmall));
            }
            return convertView;
        }
    }

    class ViewHolder {
        private TextView tv_name;
    }

    public static int dip2px(Context context, float dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    public BroadcastReceiver locationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.goldou.location") && intent.getStringExtra("location") != null) {
                tv_location.setText(intent.getStringExtra("location").split("省")[1]);
                tv_city.setText(intent.getStringExtra("location").split("省")[1].split("市")[0]);
            }
        }
    };

    public void registerBroadcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("com.goldou.location");
        mainActivity.registerReceiver(locationReceiver, myIntentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainActivity.unregisterReceiver(locationReceiver);
    }
}
