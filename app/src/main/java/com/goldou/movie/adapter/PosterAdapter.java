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
 * Created by Administrator on 2018/3/3 0003.
 */

public class PosterAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<NewsInfo.Data> list;

    public PosterAdapter(Context context, List<NewsInfo.Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            PicassoUtil.loadRound(context, list.get(position).getArticle_image(), ((ViewHolder) holder).iv_poster, 12);
            ((ViewHolder) holder).tv_content.setText(list.get(position).getArticle_title());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_poster;
        private TextView tv_content;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_poster = (ImageView) itemView.findViewById(R.id.iv_poster);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
