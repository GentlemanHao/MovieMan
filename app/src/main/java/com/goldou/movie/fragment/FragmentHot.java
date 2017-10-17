package com.goldou.movie.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.activity.TodayMarketActivity;
import com.goldou.movie.adapter.MovieAdapter;
import com.goldou.movie.bean.MovieInfo;
import com.goldou.movie.utils.OkHttpUtil;
import com.goldou.movie.view.LoadingView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class FragmentHot extends BaseFragment {

    private XRecyclerView rl_movie;
    private int offset;
    private int limit = 10;
    private MovieInfo movieInfo;
    private MovieAdapter movieAdapter;
    private MovieInfo newMovieInfo;
    private LoadingView loadingView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    break;
                case 1:
                    loadingView.setVisibility(View.GONE);
                    movieAdapter = new MovieAdapter(getContext(), movieInfo.getData().getMovies());
                    rl_movie.setAdapter(movieAdapter);
                    rl_movie.refreshComplete();
                    rl_movie.setLoadingMoreEnabled(movieInfo.getData().isHasNext());
                    break;
                case 2:
                    movieAdapter.notifyDataSetChanged();
                    rl_movie.loadMoreComplete();
                    rl_movie.setLoadingMoreEnabled(newMovieInfo.getData().isHasNext());
                    break;
                case 3:
                    iv_top.hide();
                    break;
            }
        }
    };
    private LinearLayoutManager layoutManager;
    private FloatingActionButton iv_top;
    private int lastPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        loadingView = (LoadingView) view.findViewById(R.id.loadingView);
        iv_top = (FloatingActionButton) view.findViewById(R.id.iv_top);
        iv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_movie.smoothScrollToPosition(0);
            }
        });

        rl_movie = (XRecyclerView) view.findViewById(R.id.rl_movie);
        layoutManager = new LinearLayoutManager(getContext());
        rl_movie.setLayoutManager(layoutManager);
        rl_movie.setRefreshProgressStyle(ProgressStyle.SysProgress);
        rl_movie.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        rl_movie.setArrowImageView(R.drawable.iconfont_downgrey);

        View header = View.inflate(getContext(), R.layout.movie_header, null);
        TextView tv_tickethouse = (TextView) header.findViewById(R.id.tv_tickethouse);
        TextView tv_todaybigpan = (TextView) header.findViewById(R.id.tv_todaybigpan);
        TextView tv_bigpan = (TextView) header.findViewById(R.id.tv_bigpan);
        tv_tickethouse.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_todaybigpan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_bigpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TodayMarketActivity.class));
            }
        });

        rl_movie.addHeaderView(header);

        rl_movie.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                offset = offset + 10;
                initData();
            }
        });

        lastPosition = 0;
        rl_movie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = layoutManager.findFirstVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (position < 5 || position >= lastPosition) {
                        iv_top.hide();
                    } else {
                        iv_top.show();
                        handler.sendEmptyMessageDelayed(3, 5000);
                    }
                } else {
                    iv_top.hide();
                }
                lastPosition = position;
            }
        });
        iv_top.hide();

        rl_movie.refresh();
    }

    private void initData() {
        OkHttpUtil.doGet("http://m.maoyan.com/movie/list.json?type=hot&offset=" + offset + "&limit=" + limit, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    if (offset == 0) {
                        movieInfo = gson.fromJson(response.body().string(), MovieInfo.class);
                        if (movieInfo.getStatus() == 0) {
                            handler.sendEmptyMessage(1);
                        }
                    } else {
                        newMovieInfo = gson.fromJson(response.body().string(), MovieInfo.class);
                        if (newMovieInfo.getStatus() == 0) {
                            if (newMovieInfo.getData().getMovies().size() > 0) {
                                movieInfo.getData().getMovies().addAll(newMovieInfo.getData().getMovies());
                                handler.sendEmptyMessage(2);
                            }
                        }
                    }
                }
            }
        });
    }

    public void refreshMovie() {
        if (rl_movie != null) {
            rl_movie.refresh();
        }
    }
}
