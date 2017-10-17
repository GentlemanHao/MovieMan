package com.goldou.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.activity.CinemaDetailActivity;
import com.goldou.movie.bean.CinemaInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class CinemaAdapter extends RecyclerView.Adapter {

    private List<CinemaInfo.Data> list;
    private Context context;

    public CinemaAdapter(Context context, List<CinemaInfo.Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cinema, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).cm_name.setText(list.get(position).getNm());
            ((ViewHolder) holder).cm_price.setText(list.get(position).getSellPrice() + "");
            ((ViewHolder) holder).cm_address.setText(list.get(position).getAddr());
            ((ViewHolder) holder).cm_site.setVisibility(list.get(position).isSell() ? View.VISIBLE : View.GONE);
            ((ViewHolder) holder).cm_imax.setVisibility(list.get(position).getImax() == 1 ? View.VISIBLE : View.GONE);
            int distance = list.get(position).getDistance();
            if (distance > 0) {
                if (distance < 1000) {
                    ((ViewHolder) holder).cm_distance.setText(distance + "m");
                } else {
                    float f = ((float) (distance / 100)) / 10;
                    ((ViewHolder) holder).cm_distance.setText(f + "km");
                }
            }
            final int cinemaId = list.get(position).getId();
            ((ViewHolder) holder).rl_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CinemaDetailActivity.class);
                    intent.putExtra("cinemaId", cinemaId);
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

        private TextView cm_name, cm_price, cm_address, cm_site, cm_imax, cm_distance;
        private RelativeLayout rl_item;

        public ViewHolder(View itemView) {
            super(itemView);
            cm_name = (TextView) itemView.findViewById(R.id.cm_name);
            cm_price = (TextView) itemView.findViewById(R.id.cm_price);
            cm_address = (TextView) itemView.findViewById(R.id.cm_address);
            cm_site = (TextView) itemView.findViewById(R.id.cm_site);
            cm_imax = (TextView) itemView.findViewById(R.id.cm_imax);
            cm_distance = (TextView) itemView.findViewById(R.id.cm_distance);
            rl_item = (RelativeLayout) itemView.findViewById(R.id.rl_item);
        }
    }
}
