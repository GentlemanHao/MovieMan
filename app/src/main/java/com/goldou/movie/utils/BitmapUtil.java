package com.goldou.movie.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/3 0003.
 */

public class BitmapUtil {
    public static void saveBitmap2Image(String imgName, Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/MovieMan");//设置保存路径

        File file = new File(imagePath, imgName + ".jpg");//设置文件名称
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
