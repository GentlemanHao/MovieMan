package com.goldou.movie.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.bean.SeatInfo;
import com.goldou.movie.utils.OkHttpUtil;
import com.goldou.movie.view.SeatTable;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChoseSeatActivity extends AppCompatActivity {

    private SeatInfo seatInfo;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showSeatData();
                    break;
            }
        }
    };

    private TextView tv_title;
    private TextView tv_cinema;
    private TextView tv_info;
    private SeatTable seatTable;
    private List<SeatInfo.Sections.SeatRows> seatRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_seat);

        int showId = getIntent().getIntExtra("showId", 0);
        String showDate = getIntent().getStringExtra("showDate");

        initView();
        initData(showId, showDate);
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title_name);
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoseSeatActivity.this.finish();
            }
        });
        tv_cinema = (TextView) findViewById(R.id.tv_cinema);
        tv_info = (TextView) findViewById(R.id.tv_info);
        seatTable = (SeatTable) findViewById(R.id.seatView);
    }

    private void initData(int showId, String showDate) {
        OkHttpUtil.doGet("http://m.maoyan.com/show/seats?showId=" + showId + "&showDate=" + showDate, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    if (!TextUtils.isEmpty(json)) {
                        Gson gson = new Gson();
                        seatInfo = gson.fromJson(json, SeatInfo.class);
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        });
    }

    private void showSeatData() {
        SeatInfo.ShowInfo showInfo = seatInfo.getShowInfo();
        if (showInfo != null) {
            tv_title.setText(showInfo.getMovieName());
            tv_cinema.setText(showInfo.getCinemaName());
            tv_info.setText(showInfo.getShowTime() + "  " + showInfo.getLang() + showInfo.getTp());
            seatTable.setScreenName(showInfo.getHallName());
            seatTable.setMaxSelected(showInfo.getBuyNumLimit());
        }
        List<SeatInfo.Sections> sections = seatInfo.getSections();
        seatRows = sections.get(0).getSeatRows();
        if (sections.size() > 0) {
            seatTable.setSeatChecker(new SeatTable.SeatChecker() {

                @Override
                public boolean isValidSeat(int row, int column) {
                    if (column == 2) {
                        return false;
                    }
                    return true;
                }

                @Override
                public boolean isSold(int row, int column) {
                    if (seatRows.get(row).getSeats().get(column).getType().equals("LK")) {
                        return true;
                    } else {
                        return false;
                    }
                }

                @Override
                public void checked(int row, int column) {

                }

                @Override
                public void unCheck(int row, int column) {

                }

                @Override
                public String[] checkedSeatTxt(int row, int column) {
                    return null;
                }

            });
            seatTable.setData(sections.get(0).getRows(), sections.get(0).getColumns());
        }
    }
}
