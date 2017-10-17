package com.goldou.movie.activity;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.goldou.movie.R;
import com.goldou.movie.utils.SpUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_code;
    private ImageView iv_clear;
    private Button bt_getCode;
    private Button bt_login;
    private CountDownTimer timer;
    private boolean isTiming = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        ImageView iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_code = (EditText) findViewById(R.id.et_code);
        iv_clear = (ImageView) findViewById(R.id.iv_clear);
        iv_clear.setOnClickListener(this);
        bt_getCode = (Button) findViewById(R.id.bt_getCode);
        bt_getCode.setOnClickListener(this);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(et_phone.getText().toString().trim())) {
                    iv_clear.setVisibility(View.VISIBLE);
                } else {
                    iv_clear.setVisibility(View.GONE);
                }
                if (et_phone.getText().toString().trim().length() == 11 && !isTiming) {
                    bt_getCode.setEnabled(true);
                } else {
                    bt_getCode.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_code.getText().toString().trim().length() == 6 && !TextUtils.isEmpty(et_phone.getText().toString().trim())) {
                    bt_login.setEnabled(true);
                } else {
                    bt_login.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        timer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                bt_getCode.setEnabled(false);
                bt_getCode.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                if (et_phone.getText().toString().trim().length() == 11) {
                    bt_getCode.setEnabled(true);
                }
                bt_getCode.setText("重发验证码");
                isTiming = false;
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_clear:
                et_phone.setText("");
                break;
            case R.id.bt_getCode:
                timer.start();
                isTiming = true;
                break;
            case R.id.bt_login:
                SpUtil.putBoolean(getApplicationContext(), "isLogin", true);
                finish();
                break;
        }
    }
}
