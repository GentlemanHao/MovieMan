package com.goldou.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.bean.MovieWant;
import com.goldou.movie.utils.PicassoUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class MovieWantAdapter extends MyBaseAdapter {

    private Context context;
    private List<MovieWant> list;
    private OnDeleteListener onDeleteListener;

    public MovieWantAdapter(Context context, List<MovieWant> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_moviewant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            PicassoUtil.loadRound(context, list.get(position).getImg(), ((ViewHolder) holder).mv_icon, 8);
            ((ViewHolder) holder).mv_name.setText(list.get(position).getNm());
            ((ViewHolder) holder).mv_score.setText(Html.fromHtml("评分 " + "<font color='#EEAA00'><normal>" + list.get(position).getSc() + "</small></font>"));
            ((ViewHolder) holder).mv_word.setText(list.get(position).getSrc() + "   " + list.get(position).getCat());
            ((ViewHolder) holder).mv_star.setText(list.get(position).getRt());
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int layoutPosition = holder.getLayoutPosition();
                        onItemClickListener.OnItemClick(holder.itemView, layoutPosition);
                    }
                });
            }
            if (onDeleteListener != null) {
                ((ViewHolder) holder).mv_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int layoutPosition = holder.getLayoutPosition();
                        onDeleteListener.OnDeleteListener(holder.itemView, layoutPosition);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mv_icon, mv_del;
        private TextView mv_name, mv_score, mv_word, mv_star;

        public ViewHolder(View itemView) {
            super(itemView);
            mv_icon = (ImageView) itemView.findViewById(R.id.mv_icon);
            mv_del = (ImageView) itemView.findViewById(R.id.mv_del);
            mv_name = (TextView) itemView.findViewById(R.id.mv_name);
            mv_score = (TextView) itemView.findViewById(R.id.mv_score);
            mv_word = (TextView) itemView.findViewById(R.id.mv_word);
            mv_star = (TextView) itemView.findViewById(R.id.mv_star);
        }
    }

    public interface OnDeleteListener {
        void OnDeleteListener(View itemView, int layoutPosition);
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }
}
