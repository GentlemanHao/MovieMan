package com.goldou.movie.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.adapter.HallAdapter;
import com.goldou.movie.bean.CinemaDetailInfo;
import com.goldou.movie.utils.OkHttpUtil;
import com.goldou.movie.utils.PicassoUtil;
import com.goldou.movie.view.AutoLinearLayoutManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CinemaDetailActivity extends AppCompatActivity {

    private CinemaDetailInfo cinemaDetailInfo;
    private int cinemaId;
    private TextView tv_title, tv_name, tv_address;
    private LinearLayout ll_tag;
    private RelativeLayout rl_viewPager;
    private ViewPager viewPager;
    private List<CinemaDetailInfo.DataBean.MoviesBean> movies;
    private TextView mv_name, mv_score, mv_var;
    private RadioGroup rg_date;
    private RadioButton rb_1, rb_2, rb_3, rb_4;
    private List<RadioButton> radioButtons;
    private RecyclerView rl_hall;
    private Map<String, List<MovieShow>> dateShows;
    private HallAdapter hallAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showData();
                    break;
                case 2:
                    for (int i = 0; i < movies.size(); i++) {
                        if (cinemaDetailInfo.getData().getCurrentMovie().getId() == movies.get(i).getId()) {
                            showSelectMovie(i);
                        }
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);

        initView();

        initData("");
    }

    private void initView() {
        cinemaId = getIntent().getIntExtra("cinemaId", 0);
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title_name);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_address = (TextView) findViewById(R.id.tv_address);
        ll_tag = (LinearLayout) findViewById(R.id.ll_tag);

        rl_viewPager = (RelativeLayout) findViewById(R.id.rl_viewPager);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setPageMargin(50);
        viewPager.setOffscreenPageLimit(3);
        rl_viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });

        mv_name = (TextView) findViewById(R.id.mv_name);
        mv_score = (TextView) findViewById(R.id.mv_score);
        mv_var = (TextView) findViewById(R.id.mv_var);

        rg_date = (RadioGroup) findViewById(R.id.rg_date);
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_3 = (RadioButton) findViewById(R.id.rb_3);
        rb_4 = (RadioButton) findViewById(R.id.rb_4);
        radioButtons = new ArrayList<>();
        radioButtons.add(rb_1);
        radioButtons.add(rb_2);
        radioButtons.add(rb_3);
        radioButtons.add(rb_4);

        rl_hall = (RecyclerView) findViewById(R.id.rl_hall);
        AutoLinearLayoutManager layoutManager = new AutoLinearLayoutManager(this);
        rl_hall.setNestedScrollingEnabled(false);
        rl_hall.setLayoutManager(layoutManager);
    }

    private void initData(final String movieid) {
        OkHttpUtil.doGet("http://m.maoyan.com/showtime/wrap.json?cinemaid=" + cinemaId + "&movieid=" + movieid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    if (!TextUtils.isEmpty(json)) {
                        Gson gson = new Gson();
                        cinemaDetailInfo = gson.fromJson(json, CinemaDetailInfo.class);
                        getDateShow(json);
                        handler.sendEmptyMessage(TextUtils.isEmpty(movieid) ? 1 : 2);
                    }
                }
            }
        });
    }

    private void getDateShow(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject dateShow = data.getJSONObject("DateShow");
            dateShows = new HashMap<>();
            for (int i = 0; i < cinemaDetailInfo.getData().getDates().size(); i++) {
                JSONArray show = dateShow.getJSONArray(cinemaDetailInfo.getData().getDates().get(i).getSlug());
                List<MovieShow> shows = new ArrayList<>();
                MovieShow movieShow;
                for (int j = 0; j < show.length(); j++) {
                    movieShow = new MovieShow();
                    movieShow.setGrayDesc(show.getJSONObject(j).getString("grayDesc"));
                    movieShow.setSeatStatus(show.getJSONObject(j).getInt("seatStatus"));
                    movieShow.setIsMorrow(show.getJSONObject(j).getBoolean("isMorrow"));
                    movieShow.setTh(show.getJSONObject(j).getString("th"));
                    movieShow.setSellPrStr(show.getJSONObject(j).getString("sellPrStr"));
                    movieShow.setPrStr(show.getJSONObject(j).getString("prStr"));
                    movieShow.setSell(show.getJSONObject(j).getBoolean("sell"));
                    movieShow.setEmbed(show.getJSONObject(j).getInt("embed"));
                    movieShow.setPreferential(show.getJSONObject(j).getInt("preferential"));
                    movieShow.setTm(show.getJSONObject(j).getString("tm"));
                    movieShow.setTp(show.getJSONObject(j).getString("tp"));
                    movieShow.setLang(show.getJSONObject(j).getString("lang"));
                    movieShow.setEnd(show.getJSONObject(j).getString("end"));
                    movieShow.setShowId(show.getJSONObject(j).getInt("showId"));
                    movieShow.setShowDate(show.getJSONObject(j).getString("showDate"));
                    movieShow.setDt(show.getJSONObject(j).getString("dt"));
                    shows.add(movieShow);
                }
                dateShows.put(cinemaDetailInfo.getData().getDates().get(i).getSlug(), shows);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showData() {
        CinemaDetailInfo.DataBean.CinemaDetailModelBean cinemaDetailModel = cinemaDetailInfo.getData().getCinemaDetailModel();
        tv_title.setText(cinemaDetailModel.getNm());
        tv_name.setText(cinemaDetailModel.getNm());
        tv_address.setText(cinemaDetailModel.getAddr());
        for (int i = 0; i < cinemaDetailModel.getFeatureTags().size(); i++) {
            View view = View.inflate(getApplicationContext(), R.layout.item_tag, null);
            TextView tv_tag = (TextView) view.findViewById(R.id.tv_tag);
            tv_tag.setText(cinemaDetailModel.getFeatureTags().get(i).getTag());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 0, 0, 0);
            tv_tag.setLayoutParams(layoutParams);
            ll_tag.addView(tv_tag);
        }

        movies = cinemaDetailInfo.getData().getMovies();
        if (movies.size() > 0) {
            viewPager.setAdapter(new MyPagerAdapter());
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    initData(movies.get(position).getId() + "");
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            showSelectMovie(0);
        }
    }

    private void showSelectMovie(int position) {
        mv_name.setText(movies.get(position).getNm());
        mv_score.setText(movies.get(position).getSc() + "");
        mv_var.setText(movies.get(position).getVer());

        for (int i = 0; i < radioButtons.size(); i++) {
            if (i < cinemaDetailInfo.getData().getDates().size()) {
                radioButtons.get(i).setVisibility(View.VISIBLE);
                radioButtons.get(i).setText(cinemaDetailInfo.getData().getDates().get(i).getText());
            } else {
                radioButtons.get(i).setVisibility(View.GONE);
            }
        }

        rg_date.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        checkedId = 0;
                        break;
                    case R.id.rb_2:
                        checkedId = 1;
                        break;
                    case R.id.rb_3:
                        checkedId = 2;
                        break;
                    case R.id.rb_4:
                        checkedId = 3;
                        break;
                }
                showSelectHall(checkedId);
            }
        });
        showSelectHall(0);
    }

    private void showSelectHall(int checkedId) {
        hallAdapter = new HallAdapter(CinemaDetailActivity.this, dateShows.get(cinemaDetailInfo.getData().getDates().get(checkedId).getSlug()));
        rl_hall.setAdapter(hallAdapter);
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return movies.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(getApplicationContext(), R.layout.item_img_movie, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            PicassoUtil.load(CinemaDetailActivity.this, movies.get(position).getImg(), imageView);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public class MovieShow {

        /**
         * grayDesc :
         * seatStatus : 1
         * isMorrow : false
         * th : 三号激光厅
         * sellPrStr : 91360
         * prStr : 69902
         * sell : true
         * embed : 0
         * preferential : 0
         * tm : 09:50
         * tp : 3D
         * lang : 英语
         * end : 12:03
         * showId : 242824
         * showDate : 2017-09-08
         * dt : 2017-09-08
         */

        private String grayDesc;
        private int seatStatus;
        private boolean isMorrow;
        private String th;
        private String sellPrStr;
        private String prStr;
        private boolean sell;
        private int embed;
        private int preferential;
        private String tm;
        private String tp;
        private String lang;
        private String end;
        private int showId;
        private String showDate;
        private String dt;

        public String getGrayDesc() {
            return grayDesc;
        }

        public void setGrayDesc(String grayDesc) {
            this.grayDesc = grayDesc;
        }

        public int getSeatStatus() {
            return seatStatus;
        }

        public void setSeatStatus(int seatStatus) {
            this.seatStatus = seatStatus;
        }

        public boolean isIsMorrow() {
            return isMorrow;
        }

        public void setIsMorrow(boolean isMorrow) {
            this.isMorrow = isMorrow;
        }

        public String getTh() {
            return th;
        }

        public void setTh(String th) {
            this.th = th;
        }

        public String getSellPrStr() {
            return sellPrStr;
        }

        public void setSellPrStr(String sellPrStr) {
            this.sellPrStr = sellPrStr;
        }

        public String getPrStr() {
            return prStr;
        }

        public void setPrStr(String prStr) {
            this.prStr = prStr;
        }

        public boolean isSell() {
            return sell;
        }

        public void setSell(boolean sell) {
            this.sell = sell;
        }

        public int getEmbed() {
            return embed;
        }

        public void setEmbed(int embed) {
            this.embed = embed;
        }

        public int getPreferential() {
            return preferential;
        }

        public void setPreferential(int preferential) {
            this.preferential = preferential;
        }

        public String getTm() {
            return tm;
        }

        public void setTm(String tm) {
            this.tm = tm;
        }

        public String getTp() {
            return tp;
        }

        public void setTp(String tp) {
            this.tp = tp;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public int getShowId() {
            return showId;
        }

        public void setShowId(int showId) {
            this.showId = showId;
        }

        public String getShowDate() {
            return showDate;
        }

        public void setShowDate(String showDate) {
            this.showDate = showDate;
        }

        public String getDt() {
            return dt;
        }

        public void setDt(String dt) {
            this.dt = dt;
        }
    }

    //设置切换动画
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MAX_SCALE = 1.2f;
        private static final float MIN_SCALE = 1.0f;//0.85f

        @Override
        public void transformPage(View page, float position) {
            if (position <= 1) {
                float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);
                page.setScaleX(scaleFactor);
                if (position > 0) {
                    page.setTranslationX(-scaleFactor * 2);
                } else if (position < 0) {
                    page.setTranslationX(scaleFactor * 2);
                }
                page.setScaleY(scaleFactor);
            } else {
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
