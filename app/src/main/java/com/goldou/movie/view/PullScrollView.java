package com.goldou.movie.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * Created by Administrator on 2017/10/20 0020.
 */

public class PullScrollView extends NestedScrollView {

    private View inner;
    private float startY;
    private OnRefreshListener onRefreshListener;
    private Rect normal = new Rect();
    private int distance;

    public PullScrollView(Context context) {
        super(context);
    }

    public PullScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startY = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float moveY = ev.getY();
                    distance = (int) (startY - moveY) / 3;
                    System.out.println("distance" + distance);
                    System.out.println("startY" + startY);
                    System.out.println("moveY" + moveY);
                    if (distance > 0) {
                        break;
                    }
                    /*if (distance > -10) {
                        returnAnimation();
                    }*/
                    startY = moveY;
                    if (normal.isEmpty()) {
                        normal.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getBottom());
                        break;
                    }
                    //这里移动布局
                    inner.layout(inner.getLeft(), inner.getTop() - distance, inner.getRight(), inner.getBottom() - distance);
                    break;
                case MotionEvent.ACTION_UP:
                    if (onRefreshListener != null && distance <= 0) {
                        onRefreshListener.OnRefresh();
                    }
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    public void returnAnimation() {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop() - normal.top, 0);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // 设置回到正常的布局位置
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();
    }

    public interface OnRefreshListener {
        void OnRefresh();
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void refreshComplete() {
        if (inner != null) {
            returnAnimation();
        }
    }
}
