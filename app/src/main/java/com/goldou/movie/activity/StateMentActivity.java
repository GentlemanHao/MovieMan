package com.goldou.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldou.movie.R;

public class StateMentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.tv_statement);
        textView.setText("   该APP为作者根据个人爱好开发，仅供交流学习，APP中所有接口均来自网络，如果侵犯了您的权益，" +
                "请联系作者删除，1136202398@qq.com，谢谢。");
    }
}
