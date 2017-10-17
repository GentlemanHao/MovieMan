package com.goldou.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goldou.movie.R;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class FragmentFuture extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_future, null);

        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
