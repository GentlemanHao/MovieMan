package com.goldou.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.bean.NewsInfo;
import com.goldou.movie.utils.PicassoUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

public class NewsAdapter extends MyBaseAdapter {
    private List<NewsInfo.Data> list;
    private Context context;

    public NewsAdapter(Context context, List<NewsInfo.Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            PicassoUtil.load(context, list.get(position).getArticle_image(), ((ViewHolder) holder).imageView);
            ((ViewHolder) holder).tv_title.setText(list.get(position).getArticle_title());
            ((ViewHolder) holder).tv_from.setText(list.get(position).getArticle_from());
            ((ViewHolder) holder).tv_time.setText(list.get(position).getArticle_time());
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int layoutPosition = holder.getLayoutPosition();
                        onItemClickListener.OnItemClick(holder.itemView, layoutPosition);
                    }
                });
            }
            if (onItemLongClickListener != null) {
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int layoutPosition = holder.getLayoutPosition();
                        onItemLongClickListener.OnItemLongClick(holder.itemView, layoutPosition);
                        return false;
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

        private ImageView imageView;
        private TextView tv_title, tv_from, tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.news_icon);
            tv_title = (TextView) itemView.findViewById(R.id.news_title);
            tv_from = (TextView) itemView.findViewById(R.id.news_from);
            tv_time = (TextView) itemView.findViewById(R.id.news_time);
        }
    }
}
