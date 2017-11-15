package com.goldou.movie.global;

import android.app.Application;
import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2017/9/9 0009.
 */

public class MovieApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(context);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    public static Context getContext() {
        return context;
    }

    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
}
