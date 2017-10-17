package com.goldou.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.activity.MovieDetailActivity;
import com.goldou.movie.bean.MovieInfo;
import com.goldou.movie.utils.PicassoUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MovieAdapter extends RecyclerView.Adapter {

    private List<MovieInfo.Data.Movies> list;
    private Context context;

    public MovieAdapter(Context context, List<MovieInfo.Data.Movies> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            PicassoUtil.loadRound(context, list.get(position).getImg(), ((ViewHolder) holder).mv_icon, 8);
            ((ViewHolder) holder).mv_name.setText(list.get(position).getNm());
            ((ViewHolder) holder).mv_word.setText(list.get(position).getScm());
            ((ViewHolder) holder).mv_star.setText("主演：" + list.get(position).getStar());
            if (list.get(position).getPreSale() == 0) {
                ((ViewHolder) holder).mv_score.setText(Html.fromHtml("观众评 " + "<font color='#EEAA00'><normal>" + list.get(position).getSc() + "</small></font>"));
                ((ViewHolder) holder).mv_ticket.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).mv_presale.setVisibility(View.GONE);
            } else {
                ((ViewHolder) holder).mv_score.setText(Html.fromHtml("<font color='#EEAA00'><normal>" + list.get(position).getWish() + "</small></font>人想看"));
                ((ViewHolder) holder).mv_ticket.setVisibility(View.GONE);
                ((ViewHolder) holder).mv_presale.setVisibility(View.VISIBLE);
            }

            if (list.get(position).isImax()) {
                ((ViewHolder) holder).mv_maxicon.setVisibility(View.VISIBLE);
                if (list.get(position).is_3d()) {
                    ((ViewHolder) holder).mv_maxicon.setImageResource(R.drawable.threedmax);
                } else {
                    ((ViewHolder) holder).mv_maxicon.setImageResource(R.drawable.twodmax);
                }
            } else {
                ((ViewHolder) holder).mv_maxicon.setVisibility(View.GONE);
            }
            if (list.get(position).is_3d()) {
                ((ViewHolder) holder).mv_ddd.setVisibility(View.VISIBLE);
                ((ViewHolder) holder).mv_ddd.setImageResource(R.drawable.ddd);
            } else {
                ((ViewHolder) holder).mv_ddd.setVisibility(View.GONE);
            }

            ((ViewHolder) holder).rl_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("movieId", list.get(position).getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mv_icon, mv_maxicon, mv_ddd;
        private TextView mv_name, mv_score, mv_word, mv_star;
        private Button mv_ticket, mv_presale;
        private RelativeLayout rl_item;

        public ViewHolder(View itemView) {
            super(itemView);
            mv_icon = (ImageView) itemView.findViewById(R.id.mv_icon);
            mv_maxicon = (ImageView) itemView.findViewById(R.id.mv_maxicon);
            mv_ddd = (ImageView) itemView.findViewById(R.id.mv_ddd);
            mv_name = (TextView) itemView.findViewById(R.id.mv_name);
            mv_score = (TextView) itemView.findViewById(R.id.mv_score);
            mv_word = (TextView) itemView.findViewById(R.id.mv_word);
            mv_star = (TextView) itemView.findViewById(R.id.mv_star);
            mv_ticket = (Button) itemView.findViewById(R.id.mv_ticket);
            mv_presale = (Button) itemView.findViewById(R.id.mv_presale);
            rl_item = (RelativeLayout) itemView.findViewById(R.id.rl_item);
        }
    }
}
