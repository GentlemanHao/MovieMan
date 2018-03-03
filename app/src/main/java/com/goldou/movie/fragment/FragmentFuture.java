package com.goldou.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldou.movie.R;
import com.goldou.movie.adapter.PosterAdapter;
import com.goldou.movie.bean.NewsInfo;
import com.goldou.movie.view.LoadingView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;

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

        Scanner scanner = null;
        try {
            scanner = new Scanner(getResources().getAssets().open("news"), "UTF-8");
        } catch (IOException e) {

        }
        String result = scanner.useDelimiter("\\A").next();
        scanner.close();
        if (!TextUtils.isEmpty(result)) {
            Gson gson = new Gson();
            NewsInfo newsInfo = gson.fromJson(result, NewsInfo.class);
            PosterAdapter posterAdapter = new PosterAdapter(getActivity(), newsInfo.getData());
            rl_poster.setAdapter(posterAdapter);
            loadingView.setVisibility(View.GONE);
        }
    }
}
