package com.goldou.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldou.movie.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tv_title = (TextView) findViewById(R.id.tv_title_name);

        String videoUrl = getIntent().getStringExtra("videoUrl");
        String name = getIntent().getStringExtra("name");
        tv_title.setText(name + "预告片");

        JCVideoPlayerStandard videoPlayer = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        videoPlayer.setUp(videoUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, name);
        videoPlayer.thumbImageView.setImageResource(R.drawable.icon_normal);
        videoPlayer.startButton.performClick();

    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
