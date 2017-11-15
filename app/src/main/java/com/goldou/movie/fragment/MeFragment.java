package com.goldou.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.Random;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MeFragment extends Fragment implements View.OnClickListener {

    private ImageView user_icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RelativeLayout rl_about = (RelativeLayout) view.findViewById(R.id.rl_about);
        rl_about.setOnClickListener(this);
        user_icon = (ImageView) view.findViewById(R.id.iv_user);
        int[] user_icons = {R.drawable.icon_user_boy, R.drawable.icon_user_girl};
        user_icon.setImageResource(user_icons[new Random().nextInt(2)]);
        RelativeLayout rl_want = (RelativeLayout) view.findViewById(R.id.rl_want);
        RelativeLayout rl_user = (RelativeLayout) view.findViewById(R.id.rl_user);
        rl_want.setOnClickListener(this);
        rl_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_about:
                startActivity(new Intent(getActivity(), StateMentActivity.class));
                break;
            case R.id.rl_want:
                startActivity(new Intent(getActivity(), MovieWantActivity.class));
                break;
            case R.id.rl_user:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

}
