package com.goldou.movie.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {

	private static SharedPreferences sp;

	public static void putBoolean(Context context, String key, boolean value) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		}
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String key, boolean defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		}
		return sp.getBoolean(key, defValue);
	}
	
	public static void putString(Context context, String key, String value) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		}
		sp.edit().putString(key, value).commit();
	}

	public static String getString(Context context, String key, String defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		}
		return sp.getString(key, defValue);
	}

	public static void putInt(Context context, String key, int value) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		}
		sp.edit().putInt(key, value).commit();
	}

	public static int getInt(Context context, String key, int defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		}
		return sp.getInt(key, defValue);
	}
	
	public static void delete(Context context, String key) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		}
		sp.edit().remove(key).commit();
	}

}
