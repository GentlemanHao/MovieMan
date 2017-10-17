package com.goldou.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.goldou.movie.R;
import com.goldou.movie.activity.LoginActivity;
import com.goldou.movie.activity.MovieWantActivity;
import com.goldou.movie.activity.StateMentActivity;
import com.goldou.movie.utils.PicassoUtil;
import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MeFragment extends Fragment implements View.OnClickListener {

    private ImageView user_icon;
    private ImageView iv_back;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        CollapsingToolbarLayout ctl_user = (CollapsingToolbarLayout) view.findViewById(R.id.ctl_user);
        ctl_user.setTitle("点击登录");
        ctl_user.setOnClickListener(this);
        RelativeLayout rl_about = (RelativeLayout) view.findViewById(R.id.rl_about);
        rl_about.setOnClickListener(this);
        user_icon = (ImageView) view.findViewById(R.id.user_icon);
        int[] user_icons = {R.drawable.icon_user_boy, R.drawable.icon_user_girl};
        user_icon.setImageResource(user_icons[new Random().nextInt(2)]);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        int[] imageList = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4, R.drawable.banner5, R.drawable.banner6};
        iv_back.setImageResource(imageList[new Random().nextInt(6)]);
        Picasso.with(getActivity()).load(imageList[new Random().nextInt(6)])
                .transform(new PicassoUtil.BlurTransformation(getActivity())).into(iv_back);
        RelativeLayout rl_want = (RelativeLayout) view.findViewById(R.id.rl_want);
        rl_want.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_about:
                startActivity(new Intent(getActivity(), StateMentActivity.class));
                break;
            case R.id.ctl_user:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.rl_want:
                startActivity(new Intent(getActivity(), MovieWantActivity.class));
                break;
        }
    }

}
