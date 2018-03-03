package com.goldou.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.adapter.PosterAdapter;
import com.goldou.movie.view.LoadingView;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class FragmentFuture extends BaseFragment {

    private LoadingView loadingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_future, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        loadingView = (LoadingView) view.findViewById(R.id.loadingView);
        RecyclerView rl_poster = (RecyclerView) view.findViewById(R.id.rl_poster);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rl_poster.setLayoutManager(layoutManager);
        String[] images = {"https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=5cf572e8e0c4b7452099bf44ae957572/6f061d950a7b02082d54cb2069d9f2d3562cc856.jpg",
        "https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=dcbc46f0aa8b87d6444fa34d6661435d/6c224f4a20a44623665751a39322720e0df3d756.jpg",
        "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=4f01cdd377cb0a46912f836b0a0a9d41/b17eca8065380cd7d2d18dc4ad44ad34588281cf.jpg",
        "https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=955651b060600c33e474d69a7b253a6a/bd3eb13533fa828b582b0f47f61f4134970a5a0e.jpg",
        "https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=f0f99e952a3fb80e18dc698557b8444b/48540923dd54564e6348769db8de9c82d1584f61.jpg",
        "https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=90b02037a64bd11310c0bf603bc6cf6a/242dd42a2834349b5b1eaac1c2ea15ce36d3be5e.jpg",
        "https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=d98587aca3773912d02b8d339970ed7d/8b13632762d0f703be0954cd02fa513d2697c56c.jpg"};
        PosterAdapter posterAdapter = new PosterAdapter(getActivity(), images);
        rl_poster.setAdapter(posterAdapter);
        loadingView.setVisibility(View.GONE);
    }
}
