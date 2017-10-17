package com.goldou.movie.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.activity.CityActivity;
import com.goldou.movie.activity.MainActivity;
import com.goldou.movie.utils.SpUtil;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MovieFragment extends BaseFragment {

    private String[] tab_names = {"正在热映", "即将上映"};
    private FragmentHot fragmentHot;
    private FragmentFuture fragmentFuture;
    private BaseFragment[] fragments;
    private ViewPager vp_movie;
    private TabLayout tabLayout;
    private MainActivity mainActivity;
    private TextView tv_city;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        fragmentHot = new FragmentHot();
        fragmentFuture = new FragmentFuture();
        fragments = new BaseFragment[]{fragmentHot, fragmentFuture};

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        for (int i = 0; i < tab_names.length; i++) {
            tabLayout.addTab(tabLayout.newTab(), i);
            tabLayout.getTabAt(i).setText(tab_names[i]);
        }
        vp_movie = (ViewPager) view.findViewById(R.id.vp_movie);
        vp_movie.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(vp_movie);

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
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab_names[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return tab_names.length;
        }
    }

    public void refreshHotMovie() {
        vp_movie.setCurrentItem(0);
        fragmentHot.refreshMovie();
    }

    public BroadcastReceiver locationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.goldou.location") && intent.getStringExtra("location") != null) {
                tv_city.setText(intent.getStringExtra("location").split("省")[1].split("市")[0]);
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
