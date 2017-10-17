package com.goldou.movie.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.widget.ImageView;

import com.goldou.movie.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class PicassoUtil {

    public static void load(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url)
                .placeholder(R.drawable.icon_normal)
                .error(R.drawable.icon_error)
                .into(imageView);
    }

    public static void loadCircle(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url)
                .placeholder(R.drawable.icon_normal)
                .error(R.drawable.icon_error)
                .transform(new CircleTransform())
                .into(imageView);
    }

    public static void loadRound(Context context, String url, ImageView imageView, int radius) {
        Picasso.with(context).load(url)
                .placeholder(R.drawable.icon_normal)
                .error(R.drawable.icon_error)
                .transform(new RoundTransform(radius))
                .into(imageView);
    }

    /**
     * 显示圆形图片-Picasso
     */
    static class CircleTransform implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig() != null
                    ? source.getConfig() : Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

    /**
     * 圆角显示图片-Picasso
     */
    public static class RoundTransform implements Transformation {
        private int radius;//圆角值

        public RoundTransform(int radius) {
            this.radius = radius;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int width = source.getWidth();
            int height = source.getHeight();
            //画板
            Bitmap bitmap = Bitmap.createBitmap(width, height, source.getConfig());
            Paint paint = new Paint();
            Canvas canvas = new Canvas(bitmap);//创建同尺寸的画布
            paint.setAntiAlias(true);//画笔抗锯齿
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            //画圆角背景
            RectF rectF = new RectF(new Rect(0, 0, width, height));//赋值
            canvas.drawRoundRect(rectF, radius, radius, paint);//画圆角矩形
            //
            paint.setFilterBitmap(true);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(source, 0, 0, paint);
            source.recycle();//释放

            return bitmap;
        }

        @Override
        public String key() {
            return "round : radius = " + radius;
        }
    }

    public static class BlurTransformation implements Transformation {

        RenderScript rs;

        public BlurTransformation(Context context) {
            super();
            rs = RenderScript.create(context);
        }

        @SuppressLint("NewApi")
        @Override
        public Bitmap transform(Bitmap bitmap) {
            // 创建一个Bitmap作为最后处理的效果Bitmap
            Bitmap blurredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            // 分配内存
            Allocation input = Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SHARED);
            Allocation output = Allocation.createTyped(rs, input.getType());
            // 根据我们想使用的配置加载一个实例
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setInput(input);
            // 设置模糊半径
            script.setRadius(25);
            //开始操作
            script.forEach(output);
            // 将结果copy到blurredBitmap中
            output.copyTo(blurredBitmap);
            //释放资源
            bitmap.recycle();
            return blurredBitmap;
        }

        @Override
        public String key() {
            return "blur";
        }
    }
}
