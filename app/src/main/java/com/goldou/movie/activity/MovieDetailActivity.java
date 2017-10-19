package com.goldou.movie.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.adapter.AppInfoAdapter;
import com.goldou.movie.adapter.CommentAdapter;
import com.goldou.movie.adapter.MyBaseAdapter;
import com.goldou.movie.adapter.StarsAdapter;
import com.goldou.movie.bean.AppInfo;
import com.goldou.movie.bean.MovieDetailInfo;
import com.goldou.movie.bean.MovieWant;
import com.goldou.movie.greendao.DaoMaster;
import com.goldou.movie.greendao.DaoSession;
import com.goldou.movie.greendao.MovieWantDao;
import com.goldou.movie.utils.OkHttpUtil;
import com.goldou.movie.utils.ShareUtil;
import com.goldou.movie.view.ObservableScrollView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private int movieId;
    private ImageView iv_bg;
    private MovieDetailInfo movieDetailInfo;
    private TextView tv_name;
    private TextView tv_type;
    private TextView tv_from;
    private TextView tv_score;
    private ProgressBar pb_score;
    private TextView tv_population;
    private TextView tv_audience;
    private RelativeLayout rl_title;
    private int height;
    private SwipeRefreshLayout srl_refresh;
    private TextView tv_abstract;
    private boolean isHide = true;
    private RelativeLayout rl_abstract;
    private ImageView iv_hide;
    private ImageView iv_icon;
    private RecyclerView rl_comment;
    private TextView tv_comment_num;
    private TextView tv_time;
    private TextView tv_title_name;
    private RecyclerView rl_stars;
    private ImageView iv_play;
    private BottomSheetDialog shareDialog;
    private ImageView iv_want;
    private MovieWantDao movieWantDao;
    private MovieDetailInfo.Data.MovieDetailModelBean model;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showMovieData();
                    srl_refresh.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_movie_detial);

        movieId = getIntent().getIntExtra("movieId", 0);

        initView();
        initData();
    }

    private void initView() {
        iv_bg = (ImageView) findViewById(R.id.iv_bg);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_from = (TextView) findViewById(R.id.tv_from);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_score = (TextView) findViewById(R.id.tv_score);
        pb_score = (ProgressBar) findViewById(R.id.pb_score);
        tv_population = (TextView) findViewById(R.id.tv_population);
        tv_audience = (TextView) findViewById(R.id.tv_audience);
        tv_abstract = (TextView) findViewById(R.id.tv_abstract);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);

        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        RelativeLayout rl_info = (RelativeLayout) findViewById(R.id.rl_info);
        ObservableScrollView ob_scrollView = (ObservableScrollView) findViewById(R.id.ob_scrollView);

        rl_title.setBackgroundColor(Color.argb(0, 220, 14, 60));
        height = rl_info.getLayoutParams().height - rl_title.getLayoutParams().height;
        ob_scrollView.setOnScollChangedListener(new ObservableScrollView.OnScollChangedListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                tv_title_name.setVisibility(View.GONE);
                if (y < 0)
                    return;
                if (y >= height / 2 && y <= height - height / 4) {
                    tv_title_name.setVisibility(View.VISIBLE);
                    float scale = (float) y / height;
                    float alpha = (255 * scale);
                    rl_title.setBackgroundColor(Color.argb((int) alpha, 220, 14, 60));
                } else if (y >= 0 && y < height / 2) {
                    rl_title.setBackgroundColor(Color.argb(0, 220, 14, 60));
                } else {
                    tv_title_name.setVisibility(View.VISIBLE);
                    rl_title.setBackgroundColor(Color.argb(255, 220, 14, 60));
                }
            }
        });

        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title_name = (TextView) findViewById(R.id.tv_title_name);

        srl_refresh = (SwipeRefreshLayout) findViewById(R.id.srl_refresh);
        srl_refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorBlue);
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        rl_abstract = (RelativeLayout) findViewById(R.id.rl_abstract);
        iv_hide = (ImageView) findViewById(R.id.iv_hide);

        rl_comment = (RecyclerView) findViewById(R.id.rl_comment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MovieDetailActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rl_comment.setLayoutManager(layoutManager);

        tv_comment_num = (TextView) findViewById(R.id.tv_comment_num);

        rl_stars = (RecyclerView) findViewById(R.id.rl_stars);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MovieDetailActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rl_stars.setLayoutManager(linearLayoutManager);

        iv_play = (ImageView) findViewById(R.id.iv_play);
        ImageView iv_share = (ImageView) findViewById(R.id.iv_share);
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareDialog();
            }
        });

        LinearLayout ll_want = (LinearLayout) findViewById(R.id.ll_want);
        ll_want.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToWantMovie();
            }
        });
        iv_want = (ImageView) findViewById(R.id.iv_want);
        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "MovieWant");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        movieWantDao = daoSession.getMovieWantDao();
    }

    private void initData() {
        OkHttpUtil.doGet("http://m.maoyan.com/movie/" + movieId + ".json", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    movieDetailInfo = gson.fromJson(response.body().string(), MovieDetailInfo.class);
                    handler.sendEmptyMessage(1);
                }
            }
        });
    }

    private void showMovieData() {
        model = movieDetailInfo.getData().getMovieDetailModel();
        Picasso.with(MovieDetailActivity.this).load(model.getImg()).into(iv_bg);
        tv_name.setText(model.getNm());
        tv_title_name.setText(model.getNm());
        tv_title_name.setVisibility(View.GONE);
        tv_type.setText(model.getCat());
        tv_from.setText(model.getSrc() + " / " + model.getDur() + "分钟");
        tv_time.setText(model.getRt());
        tv_score.setText(model.getSc() + "");

        if (model.isShowSnum()) {
            tv_audience.setVisibility(View.VISIBLE);
            tv_score.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            pb_score.setProgress((int) (model.getSc() * 10));
            if (model.getSnum() > 11000) {
                tv_population.setText((double) (model.getSnum() / 1000) / 10 + "万人");
            } else {
                tv_population.setText(model.getSnum() + "人");
            }
        } else {
            tv_audience.setVisibility(View.INVISIBLE);
            tv_score.setVisibility(View.INVISIBLE);
            pb_score.setVisibility(View.INVISIBLE);
            tv_population.setText(model.getWish() + "人想看");
            tv_population.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorYellow));
        }

        Picasso.with(MovieDetailActivity.this).load(model.getImg()).into(iv_icon);

        tv_abstract.setText(Html.fromHtml(model.getDra()));
        showAbstract();
        rl_abstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHide = !isHide;
                showAbstract();
            }
        });

        rl_comment.setAdapter(new CommentAdapter(MovieDetailActivity.this, movieDetailInfo.getData().getCommentResponseModel().getHcmts()));

        tv_comment_num.setText("查看全部" + movieDetailInfo.getData().getCommentResponseModel().getTotal() + "条短评 >");

        List<String> starList = Arrays.asList(model.getStar().split(" "));
        List<String> dirList = Arrays.asList(model.getDir().split(" "));

        rl_stars.setAdapter(new StarsAdapter(MovieDetailActivity.this, starList, dirList));

        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, VideoPlayActivity.class);
                intent.putExtra("videoUrl", model.getVd());
                intent.putExtra("name", model.getNm());
                startActivity(intent);
            }
        });

        showIfWant();
    }

    private boolean isWant;

    private void showIfWant() {
        QueryBuilder<MovieWant> builder = movieWantDao.queryBuilder();
        List<MovieWant> list = builder.where(MovieWantDao.Properties.Id.eq(model.getId())).list();
        if (list.size() > 0) {
            iv_want.setImageResource(R.drawable.icon_love_on);
            isWant = true;
        } else {
            iv_want.setImageResource(R.drawable.icon_love_off);
            isWant = false;
        }
    }

    private void addToWantMovie() {
        if (isWant) {
            movieWantDao.deleteByKey(Long.valueOf(model.getId()));
        } else {
            movieWantDao.insert(new MovieWant(model.getId(), model.getNm(), model.getSrc(), model.getCat(), model.getRt(), model.getImg(), model.getSc() + ""));
        }
        showIfWant();
    }

    private void showAbstract() {
        if (isHide) {
            tv_abstract.setEllipsize(TextUtils.TruncateAt.END);
            tv_abstract.setMaxLines(3);
            iv_hide.setImageResource(R.drawable.icon_show);
        } else {
            tv_abstract.setEllipsize(null);
            tv_abstract.setSingleLine(false);
            iv_hide.setImageResource(R.drawable.icon_hide);
        }
    }

    private void showShareDialog() {
        if (shareDialog == null) {
            shareDialog = new BottomSheetDialog(this);
            shareDialog.setContentView(R.layout.share_dialog);
            initShareContent();
        } else {
            shareDialog.show();
        }
    }

    private void initShareContent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "www.github.com");
        final ArrayList<AppInfo> appList = ShareUtil.getShareAppList(this, shareIntent);
        AppInfoAdapter appInfoAdapter = new AppInfoAdapter(this, appList);
        RecyclerView rl_share = (RecyclerView) shareDialog.findViewById(R.id.rl_share);
        rl_share.setLayoutManager(new GridLayoutManager(this, 3));
        rl_share.setAdapter(appInfoAdapter);
        appInfoAdapter.setOnItemClickListener(new MyBaseAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View itemView, int position) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setComponent(new ComponentName(appList.get(position).getPkgName(), appList.get(position).getLaunchClassName()));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "我在电影人看《" + tv_name.getText().toString() + "》的最新预告片、高清剧照，周边影院热映情况，快来加入我吧。");
                startActivity(intent);
            }
        });
        appInfoAdapter.setOnItemLongClickListener(new MyBaseAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(View itemView, int position) {
                // 打开应用信息界面
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + appList.get(position).getPkgName()));
                startActivity(intent);
            }
        });

        ImageView iv_meizu = (ImageView) shareDialog.findViewById(R.id.iv_meizu);
        int[] meizus = {R.drawable.meizu_ep51, R.drawable.meizu_have};
        iv_meizu.setImageResource(meizus[new Random().nextInt(2)]);
        shareDialog.show();
    }
}
