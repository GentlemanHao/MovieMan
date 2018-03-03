package com.goldou.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.activity.CityActivity;
import com.goldou.movie.utils.SpUtil;

/**
 * Created by Administrator on 2018/3/3 0003.
 */

public class ShowFragment extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_city.setOnClickListener(this);
        tv_city.setText(SpUtil.getString(getContext(), "CITY", ""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_city:
                startActivity(new Intent(getActivity(), CityActivity.class));
                break;
        }
    }
}
