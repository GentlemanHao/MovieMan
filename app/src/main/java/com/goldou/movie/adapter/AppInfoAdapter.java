package com.goldou.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldou.movie.R;
import com.goldou.movie.bean.AppInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<AppInfo> mAppList;
    private OnItemClickListener mOnItemClickListener;

    public AppInfoAdapter(Context mContext, ArrayList<AppInfo> date) {
        this.mAppList = date;
        this.context = mContext;
    }

    @Override
    public AppInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appinfo_item, viewGroup, false);
        return new AppInfoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {
        holder.tv.setText(mAppList.get(i).getAppName());
        holder.img.setBackground(mAppList.get(i).getAppIcon());

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;

        ViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.text_list_item);
            img = (ImageView) view.findViewById(R.id.img_list_item);
        }
    }
}
