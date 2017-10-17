package com.goldou.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.bean.MovieDetailInfo;
import com.goldou.movie.utils.PicassoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class CommentAdapter extends RecyclerView.Adapter {

    private List<MovieDetailInfo.Data.CommentResponseModelBean.HcmtsBean> list;
    private Context context;

    public CommentAdapter(Context context, List<MovieDetailInfo.Data.CommentResponseModelBean.HcmtsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            if (TextUtils.isEmpty(list.get(position).getAvatarurl())) {
                list.get(position).setAvatarurl("http://p0.meituan.net/movie/7dd82a16316ab32c8359debdb04396ef2897.png@100w_100h_1e_1c");
            }
            PicassoUtil.loadCircle(context, list.get(position).getAvatarurl(), ((ViewHolder) holder).ct_icon);
            ((ViewHolder) holder).ct_name.setText(list.get(position).getNickName());
            ((ViewHolder) holder).ct_comment.setText(list.get(position).getContent());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            Date date = null;
            try {
                date = sdf.parse(list.get(position).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (daysBetween(date, new Date()) < 1) {
                ((ViewHolder) holder).ct_time.setText(list.get(position).getTime().substring(11));
            } else if (daysBetween(date, new Date()) == 1) {
                ((ViewHolder) holder).ct_time.setText("昨天");
            } else if (daysBetween(date, new Date()) == 2) {
                ((ViewHolder) holder).ct_time.setText("前天");
            } else if (daysBetween(date, new Date()) > 2 && daysBetween(date, new Date()) <= 6) {
                ((ViewHolder) holder).ct_time.setText(daysBetween(date, new Date()) + "天前");
            } else {
                ((ViewHolder) holder).ct_time.setText(list.get(position).getTime().substring(5, 11));
            }

            ((ViewHolder) holder).ct_zan_num.setText(list.get(position).getApprove() + "");
            ((ViewHolder) holder).ct_comment_num.setText(list.get(position).getReply() + "");
            ((ViewHolder) holder).ct_star.setRating((float) list.get(position).getScore());
            ((ViewHolder) holder).ct_score.setText((int) (list.get(position).getScore() * 2.0) + "分");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ct_icon;
        private TextView ct_name, ct_comment, ct_time, ct_comment_num, ct_zan_num, ct_score;
        private RatingBar ct_star;

        public ViewHolder(View itemView) {
            super(itemView);
            ct_icon = (ImageView) itemView.findViewById(R.id.ct_icon);
            ct_name = (TextView) itemView.findViewById(R.id.ct_name);
            ct_comment = (TextView) itemView.findViewById(R.id.ct_comment);
            ct_time = (TextView) itemView.findViewById(R.id.ct_time);
            ct_comment_num = (TextView) itemView.findViewById(R.id.ct_comment_num);
            ct_zan_num = (TextView) itemView.findViewById(R.id.ct_zan_num);
            ct_star = (RatingBar) itemView.findViewById(R.id.ct_star);
            ct_score = (TextView) itemView.findViewById(R.id.ct_score);
        }
    }

    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
