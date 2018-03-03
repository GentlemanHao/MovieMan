package com.goldou.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.goldou.movie.R;
import com.goldou.movie.utils.PicassoUtil;

/**
 * Created by Administrator on 2018/3/3 0003.
 */

public class PosterAdapter extends RecyclerView.Adapter {

    private Context context;
    private String[] images;

    public PosterAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewGroup.LayoutParams layoutParams = ((ViewHolder) holder).iv_poster.getLayoutParams();

            PicassoUtil.loadRound(context, images[position], ((ViewHolder) holder).iv_poster, 12);
        }
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_poster;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_poster = (ImageView) itemView.findViewById(R.id.iv_poster);
        }
    }
}
