package com.goldou.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldou.movie.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class StarsAdapter extends RecyclerView.Adapter {

    private List<String> dir;
    private List<String> list;
    private Context context;

    public StarsAdapter(Context context, List<String> list, List<String> dir) {
        this.context = context;
        this.list = list;
        this.dir = dir;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_star, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            if (position < dir.size()) {
                ((ViewHolder) holder).star_name.setText(dir.get(position));
                ((ViewHolder) holder).star_role.setText("导演");
            } else {
                ((ViewHolder) holder).star_name.setText(list.get(position - dir.size()));
                ((ViewHolder) holder).star_role.setText("饰：XXX");
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + +dir.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView star_icon;
        private TextView star_name, star_role;

        public ViewHolder(View itemView) {
            super(itemView);
            star_icon = (ImageView) itemView.findViewById(R.id.star_icon);
            star_name = (TextView) itemView.findViewById(R.id.star_name);
            star_role = (TextView) itemView.findViewById(R.id.star_role);
        }
    }
}
