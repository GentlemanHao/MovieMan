package com.goldou.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.activity.ChoseSeatActivity;
import com.goldou.movie.activity.CinemaDetailActivity;
import com.goldou.movie.bean.CinemaDetailInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class HallAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<CinemaDetailActivity.MovieShow> list;

    public HallAdapter(Context context, List<CinemaDetailActivity.MovieShow> movieShows) {
        this.context = context;
        this.list = movieShows;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hall, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).hl_time.setText(list.get(position).getTm());
            ((ViewHolder) holder).hl_end.setText(list.get(position).getEnd() + "散场");
            ((ViewHolder) holder).hl_type.setText(list.get(position).getLang() + list.get(position).getTp());
            ((ViewHolder) holder).hl_num.setText(list.get(position).getTh());
            ((ViewHolder) holder).hl_buy.setVisibility(list.get(position).isSell() ? View.VISIBLE : View.GONE);
            ((ViewHolder) holder).hl_stop.setVisibility(list.get(position).isSell() ? View.GONE : View.VISIBLE);
            if (list.get(position).isSell()) {
                ((ViewHolder) holder).hl_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ChoseSeatActivity.class);
                        intent.putExtra("showId", list.get(position).getShowId());
                        intent.putExtra("showDate", list.get(position).getShowDate());
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView hl_time, hl_end, hl_type, hl_num;
        private Button hl_buy, hl_stop;

        public ViewHolder(View itemView) {
            super(itemView);
            hl_time = (TextView) itemView.findViewById(R.id.hl_time);
            hl_end = (TextView) itemView.findViewById(R.id.hl_end);
            hl_type = (TextView) itemView.findViewById(R.id.hl_type);
            hl_num = (TextView) itemView.findViewById(R.id.hl_num);
            hl_buy = (Button) itemView.findViewById(R.id.hl_buy);
            hl_stop = (Button) itemView.findViewById(R.id.hl_stop);
        }
    }
}
