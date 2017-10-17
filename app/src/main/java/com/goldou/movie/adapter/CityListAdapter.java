package com.goldou.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goldou.movie.R;
import com.goldou.movie.activity.MainActivity;
import com.goldou.movie.global.MovieApplication;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class CityListAdapter extends RecyclerView.Adapter {


    private String locationCity;
    private List<String> list;
    private Context context;
    private final int TYPE_LOCATION = 0;
    private final int TYPE_HOT = 1;
    private final int TYPE_NORMAL = 2;
    private final String[] hotCitys;

    public CityListAdapter(Context context, List<String> letters, String locationCity) {
        this.context = context;
        this.list = letters;
        hotCitys = new String[]{"上海市", "北京市", "广州市", "深圳市", "武汉市", "重庆市", "杭州市", "西安市"};
        this.locationCity = locationCity;
    }

    public int getLetterPosition(String letter) {
        if (letter.equals("定位")) {
            return 0;
        }
        if (letter.equals("热门")) {
            return 1;
        }
        for (int i = 2; i < list.size(); i++) {
            if (letter.equals(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOCATION) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ding, parent, false);
            return new ViewHolderLocation(view);
        } else if (viewType == TYPE_HOT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot, parent, false);
            return new ViewHolderHot(view);
        } else if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
            return new ViewHolderNormal(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderLocation) {
            ((ViewHolderLocation) holder).tv_abc.setText(list.get(position));
            ((ViewHolderLocation) holder).tv_location.setText(locationCity);
        } else if (holder instanceof ViewHolderHot) {
            ((ViewHolderHot) holder).tv_abc.setText(list.get(position));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = MovieApplication.dip2px(18);
            for (int i = 0; i < 4; i++) {
                TextView tv_city = (TextView) View.inflate(context, R.layout.city_hot, null);
                tv_city.setText(hotCitys[i]);
                ((ViewHolderHot) holder).ll_hot1.addView(tv_city, layoutParams);
            }
            for (int i = 4; i < 8; i++) {
                TextView tv_city = (TextView) View.inflate(context, R.layout.city_hot, null);
                tv_city.setText(hotCitys[i]);
                ((ViewHolderHot) holder).ll_hot2.addView(tv_city, layoutParams);
            }
        } else if (holder instanceof ViewHolderNormal) {
            final String city = list.get(position);
            ((ViewHolderNormal) holder).tv_abc.setText(city);
            ((ViewHolderNormal) holder).tv_abc.setEnabled(city.length() >= 2);
            ((ViewHolderNormal) holder).tv_abc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, city, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_LOCATION;
        } else if (position == 1) {
            return TYPE_HOT;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolderLocation extends RecyclerView.ViewHolder {

        private TextView tv_abc, tv_location;
        private ImageView iv_refresh;
        private RelativeLayout rl_refresh;

        public ViewHolderLocation(View itemView) {
            super(itemView);
            tv_abc = (TextView) itemView.findViewById(R.id.tv_abc);
            tv_location = (TextView) itemView.findViewById(R.id.tv_location);
            iv_refresh = (ImageView) itemView.findViewById(R.id.iv_refresh);
            rl_refresh = (RelativeLayout) itemView.findViewById(R.id.rl_refresh);
        }
    }

    private class ViewHolderHot extends RecyclerView.ViewHolder {

        private TextView tv_abc;
        private LinearLayout ll_hot1, ll_hot2;

        public ViewHolderHot(View itemView) {
            super(itemView);
            tv_abc = (TextView) itemView.findViewById(R.id.tv_abc);
            ll_hot1 = (LinearLayout) itemView.findViewById(R.id.ll_hot1);
            ll_hot2 = (LinearLayout) itemView.findViewById(R.id.ll_hot2);
        }
    }

    private class ViewHolderNormal extends RecyclerView.ViewHolder {

        private TextView tv_abc;

        public ViewHolderNormal(View itemView) {
            super(itemView);
            tv_abc = (TextView) itemView.findViewById(R.id.tv_abc);
        }
    }
}
