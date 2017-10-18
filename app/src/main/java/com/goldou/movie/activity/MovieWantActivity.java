package com.goldou.movie.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.goldou.movie.R;
import com.goldou.movie.adapter.MovieWantAdapter;
import com.goldou.movie.bean.MovieWant;
import com.goldou.movie.greendao.DaoMaster;
import com.goldou.movie.greendao.DaoSession;
import com.goldou.movie.greendao.MovieWantDao;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MovieWantActivity extends AppCompatActivity implements View.OnClickListener {

    private XRecyclerView rl_want;
    private MovieWantDao movieWantDao;
    private MovieWantAdapter movieWantAdapter;
    private List<MovieWant> list;
    private boolean isFirst = true;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    rl_want.setAdapter(movieWantAdapter);
                    rl_want.refreshComplete();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_want);

        initView();
        initDB();
        initDate();
    }

    private void initView() {
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        ImageView iv_share = (ImageView) findViewById(R.id.iv_share);
        iv_back.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        rl_want = (XRecyclerView) findViewById(R.id.rl_want);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rl_want.setLayoutManager(layoutManager);
        rl_want.setRefreshProgressStyle(ProgressStyle.SysProgress);
        rl_want.setLoadingMoreEnabled(false);
        rl_want.setArrowImageView(R.drawable.iconfont_downgrey);

        rl_want.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initDate();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    private void initDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "MovieWant");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        movieWantDao = daoSession.getMovieWantDao();
    }

    private void initDate() {
        QueryBuilder<MovieWant> builder = movieWantDao.queryBuilder();
        list = builder.list();
        movieWantAdapter = new MovieWantAdapter(this, list);
        movieWantAdapter.setOnItemClickListener(new MovieWantAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View itemView, int position) {
                Intent intent = new Intent(MovieWantActivity.this, MovieDetailActivity.class);
                intent.putExtra("movieId", Integer.valueOf(String.valueOf(list.get(position - 1).getId())));
                startActivity(intent);
            }
        });
        movieWantAdapter.setOnDeleteListener(new MovieWantAdapter.OnDeleteListener() {
            @Override
            public void OnDeleteListener(View itemView, int layoutPosition) {
                movieWantDao.deleteByKey(list.get(layoutPosition - 1).getId());
                list.remove(layoutPosition - 1);
                movieWantAdapter.notifyItemRemoved(layoutPosition);
            }
        });
        if (!isFirst) {
            isFirst = false;
            handler.sendEmptyMessageDelayed(1, 1000);
        } else {
            handler.sendEmptyMessage(1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
