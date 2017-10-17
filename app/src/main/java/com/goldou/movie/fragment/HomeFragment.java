package com.goldou.movie.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.activity.CityActivity;
import com.goldou.movie.activity.MainActivity;
import com.goldou.movie.adapter.HomeAdapter;
import com.goldou.movie.adapter.NewsAdapter;
import com.goldou.movie.bean.EveryDayInfo;
import com.goldou.movie.bean.MovieInfo;
import com.goldou.movie.bean.NewsInfo;
import com.goldou.movie.utils.OkHttpUtil;
import com.goldou.movie.utils.PicassoUtil;
import com.goldou.movie.utils.SpUtil;
import com.goldou.movie.view.LoadingView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.goldou.movie.global.MovieApplication.dip2px;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class HomeFragment extends BaseFragment {

    private MovieInfo movieInfo;
    private int[] imageList;
    private ViewPager vp_home;
    private int height;
    private RelativeLayout rl_search;
    private MainActivity mainActivity;
    private TextView tv_city;
    private LoadingView loadingView;
    private LinearLayout ll_point;
    private int pointPosition;
    private RecyclerView rl_news;
    private NewsInfo newsInfo;
    private HomeAdapter movieAdapter;
    private NewsAdapter newsAdapter;
    private RecyclerView rl_movie;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    loadingView.setVisibility(View.GONE);
                    movieAdapter = new HomeAdapter(getContext(), movieInfo.getData().getMovies());
                    rl_movie.setAdapter(movieAdapter);
                    srl_refresh.setRefreshing(false);
                    initNews();
                    break;
                case 2:
                    showEveryDayInfo();
                    break;
            }
        }
    };
    private LinearLayout ll_nomore;
    private SwipeRefreshLayout srl_refresh;
    private EveryDayInfo everyDayInfo;
    private AlertDialog todayDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rl_movie = (RecyclerView) view.findViewById(R.id.rl_movie);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rl_movie.setLayoutManager(linearLayoutManager);

        TextView tv_hotMovie = (TextView) view.findViewById(R.id.tv_hotMovie);
        tv_hotMovie.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        imageList = new int[]{R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner5, R.drawable.banner6};

        vp_home = (ViewPager) view.findViewById(R.id.vp_home);
        vp_home.setAdapter(new TitleAdapter());

        ll_point = (LinearLayout) view.findViewById(R.id.ll_point);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(11), dip2px(11));
        for (int i = 0; i < imageList.length; i++) {
            ImageView point = new ImageView(getActivity());
            if (i == 0) {
                point.setImageResource(R.drawable.point_select);
            } else {
                point.setImageResource(R.drawable.point_normal);
                params.leftMargin = dip2px(3);
            }
            point.setLayoutParams(params);
            ll_point.addView(point);
        }

        vp_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % imageList.length;
                ImageView point = (ImageView) ll_point.getChildAt(position);
                point.setImageResource(R.drawable.point_select);
                ImageView prePoint = (ImageView) ll_point.getChildAt(pointPosition);
                prePoint.setImageResource(R.drawable.point_normal);
                pointPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        new TitleTask().start();

        rl_search = (RelativeLayout) view.findViewById(R.id.rl_searchBar);
        NestedScrollView scrollView = (NestedScrollView) view.findViewById(R.id.os_scrollView);
        rl_search.setBackgroundColor(Color.argb(0, 220, 14, 60));
        height = vp_home.getLayoutParams().height;
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY < 0)
                    return;
                if (scrollY <= height) {
                    float scale = (float) scrollY / height;
                    float alpha = (255 * scale);
                    rl_search.setBackgroundColor(Color.argb((int) alpha, 220, 14, 60));
                } else {
                    rl_search.setBackgroundColor(Color.argb(255, 220, 14, 60));
                }
            }
        });

        TextView tv_all = (TextView) view.findViewById(R.id.tv_all);
        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.setBottomBarSelection(R.id.tab_movie);
            }
        });

        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CityActivity.class));
            }
        });
        tv_city.setText(SpUtil.getString(getContext(), "CITY", ""));
        mainActivity = (MainActivity) getActivity();
        registerBoradcastReceiver();

        loadingView = (LoadingView) view.findViewById(R.id.loadingView);

        rl_news = (RecyclerView) view.findViewById(R.id.rl_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rl_news.setLayoutManager(layoutManager);
        rl_news.setNestedScrollingEnabled(false);

        ll_nomore = (LinearLayout) view.findViewById(R.id.ll_nomore);

        srl_refresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_refresh);
        srl_refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorBlue);
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        initData();
    }

    class TitleTask implements Runnable {

        public void start() {
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(this, 6000);
        }

        @Override
        public void run() {
            vp_home.setCurrentItem(vp_home.getCurrentItem() + 1);
            handler.postDelayed(this, 6000);
        }
    }

    private void initData() {
        OkHttpUtil.doGet("http://m.maoyan.com/movie/list.json?type=hot&offset=" + 0 + "&limit=" + 12, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    movieInfo = gson.fromJson(response.body().string(), MovieInfo.class);
                    if (movieInfo.getStatus() == 0) {
                        handler.sendEmptyMessageDelayed(1, 2000);
                    }
                }
            }
        });
    }

    private boolean isShowEveryDay = true;

    private void initNews() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(getResources().getAssets().open("news"), "UTF-8");
        } catch (IOException e) {

        }
        String result = scanner.useDelimiter("\\A").next();
        scanner.close();
        if (!TextUtils.isEmpty(result)) {
            Gson gson = new Gson();
            newsInfo = gson.fromJson(result, NewsInfo.class);
            newsAdapter = new NewsAdapter(getActivity(), newsInfo.getData());
            rl_news.setAdapter(newsAdapter);
            ll_nomore.setVisibility(View.VISIBLE);

            newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int layoutPosition) {
                    Snackbar.make(rl_news, newsInfo.getData().get(layoutPosition).getArticle_title(), Snackbar.LENGTH_LONG).show();
                }
            });
            /*newsAdapter.setOnItemLongClickListener(new NewsAdapter.OnItemLongClickListener() {
                @Override
                public void onItemLongClick(View itemView, int layoutPosition) {
                    Snackbar.make(rl_news, newsInfo.getData().get(layoutPosition).getArticle_from(), Snackbar.LENGTH_LONG).show();
                }
            });*/

            if (isShowEveryDay) {
                isShowEveryDay = false;
                getEveryDayEnglish();
            }
        }
    }

    private void getEveryDayEnglish() {
        OkHttpUtil.doGet("http://open.iciba.com/dsapi/", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    if (!TextUtils.isEmpty(result)) {
                        Gson gson = new Gson();
                        everyDayInfo = gson.fromJson(result, EveryDayInfo.class);
                        handler.sendEmptyMessageDelayed(2, 5000);
                    }
                }
            }
        });
    }

    private void showEveryDayInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.custom_dialog);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.everyday_dialog, null);

        ImageView image = (ImageView) dialogView.findViewById(R.id.iv_image);
        ImageView iv_close = (ImageView) dialogView.findViewById(R.id.iv_close);
        TextView tv_chinese = (TextView) dialogView.findViewById(R.id.tv_chinese);
        TextView tv_english = (TextView) dialogView.findViewById(R.id.tv_english);
        PicassoUtil.load(getActivity(), everyDayInfo.getPicture2(), image);
        tv_chinese.setText("      " + everyDayInfo.getNote());
        tv_english.setText("      " + everyDayInfo.getContent());

        builder.setView(dialogView);
        todayDialog = builder.create();
        todayDialog.setCancelable(false);
        todayDialog.show();
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayDialog.dismiss();
            }
        });
    }

    class TitleAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % imageList.length;
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(imageList[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public BroadcastReceiver locationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.goldou.location") && intent.getStringExtra("location") != null) {
                String city = intent.getStringExtra("location").split("省")[1].split("市")[0];
                tv_city.setText(city);
                SpUtil.putString(getContext(), "CITY", city);
            }
        }
    };

    public void registerBoradcastReceiver() {
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
