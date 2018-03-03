package com.goldou.movie.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.goldou.movie.R;
import com.goldou.movie.utils.BitmapUtil;
import com.goldou.movie.utils.BottomChoseDialog;
import com.goldou.movie.utils.QRCodeUtil;
import com.google.zxing.Result;
import com.google.zxing.client.android.AutoScannerView;
import com.google.zxing.client.android.BaseCaptureActivity;

/**
 * 模仿微信的扫描界面
 */
public class WeChatCaptureActivity extends BaseCaptureActivity implements View.OnClickListener {

    private SurfaceView surfaceView;
    private AutoScannerView autoScannerView;
    private boolean isScan = true;
    private ImageView iv_code;
    private RelativeLayout rl_scan;
    private RelativeLayout rl_code;
    private ImageView iv_result;
    private EditText et_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_capture);
        surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        autoScannerView = (AutoScannerView) findViewById(R.id.autoscanner_view);

        initView();
    }

    private void initView() {
        rl_scan = (RelativeLayout) findViewById(R.id.rl_scan);
        rl_code = (RelativeLayout) findViewById(R.id.rl_code);
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_code = (ImageView) findViewById(R.id.iv_code);
        iv_back.setOnClickListener(this);
        iv_code.setOnClickListener(this);
        iv_result = (ImageView) findViewById(R.id.iv_result);
        et_code = (EditText) findViewById(R.id.et_code);
        Button bt_code = (Button) findViewById(R.id.bt_code);
        bt_code.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        autoScannerView.setCameraManager(cameraManager);
    }

    @Override
    public SurfaceView getSurfaceView() {
        return (surfaceView == null) ? (SurfaceView) findViewById(R.id.preview_view) : surfaceView;
    }


    @Override
    public void dealDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        playBeepSoundAndVibrate(true, false);
        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_LONG).show();
//        对此次扫描结果不满意可以调用
//        reScan();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_code:
                isScan = !isScan;
                iv_code.setImageResource(isScan ? R.drawable.icon_qrcode : R.drawable.icon_scan_big);
                rl_scan.setVisibility(isScan ? View.VISIBLE : View.GONE);
                rl_code.setVisibility(isScan ? View.GONE : View.VISIBLE);
                if (isScan) {
                    reScan();
                } else {
                    iv_result.setImageResource(R.drawable.icon_qrcode_black);
                    et_code.setText("");
                }
                break;
            case R.id.bt_code:
                createCodeImage();
                break;
        }
    }

    private void createCodeImage() {
        final String content = et_code.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            final Bitmap bitmap = QRCodeUtil.encodeAsBitmap(content);
            iv_result.setImageBitmap(bitmap);
            iv_result.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showSaveImg(content, bitmap);
                    return true;
                }
            });
        }
    }

    private void showSaveImg(final String content, final Bitmap bitmap) {
        BottomChoseDialog.show(WeChatCaptureActivity.this, new BottomChoseDialog.OnItemClickListener() {
            @Override
            public void onItemClick(int item) {
                BitmapUtil.saveBitmap2Image(content, bitmap);
            }
        }, "保存图片");
    }
}
