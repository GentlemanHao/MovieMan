package com.goldou.movie.utils;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.TextView;

import com.goldou.movie.R;

/**
 * Created by Administrator on 2018/1/24 0024.
 */

public class BottomChoseDialog {
    public static void show(Context context, final OnItemClickListener listener, String... contents) {
        final BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(R.layout.select_list);
        TextView item1 = (TextView) dialog.findViewById(R.id.item1);
        TextView item2 = (TextView) dialog.findViewById(R.id.item2);
        TextView item3 = (TextView) dialog.findViewById(R.id.item3);
        TextView[] textViews = {item1, item2};
        for (int i = 0; i < contents.length; i++) {
            textViews[i].setVisibility(View.VISIBLE);
            textViews[i].setText(contents[i]);
        }
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onItemClick(0);
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onItemClick(1);
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public interface OnItemClickListener {
        void onItemClick(int item);
    }
}
