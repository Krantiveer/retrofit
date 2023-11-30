package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PreferenceUtils {
    public static final String TAG = "PreferenceUtils";
    private static final PreferenceUtils INSTANCE = new PreferenceUtils();
    private static final String current_video_quality = "current_video_quality";
    private static final String windowHeight = "window_height";
    private static final String current_sub_title = "current_sub_title";
    private static final String shared_preferences = "shared_preferences";
    public static final String country_code = "country_code";
    public static final String country_name = "country_name";
    public static final String uvtv_state_name = "uvtv_state_name";
    public static final String access_token = "access_token";
    public static final String access_coupon = "access_coupon";
    public static final String state_name = "state_name";
    public static final Integer watch_list = 0;
    public static final String PairID="";
    public static final String watermarklogourl = "watermarklogourl";
    public static final String watermarkEnable = "";
    public static final String websiteUrl = "";
    public static final String PAIR_CODE = "PAIR_CODE";
    public static final String Login = "false";


    // public static final Boolean focusFromWatchNow = "";

    public static PreferenceUtils getInstance() {
        return INSTANCE;
    }



    public static long getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
        String currentDateAndTime = sdf.format(new Date());

        Date date = null;
        try {
            date = sdf.parse(currentDateAndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getTimeInMillis();
    }

    public static long getExpireTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
        String currentDateAndTime = sdf.format(new Date());

        Date date = null;
        try {
            date = sdf.parse(currentDateAndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 2);

        return calendar.getTimeInMillis();
    }



    public void setWindowHeightPref(Context context, String hight) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(current_video_quality, hight);
        editor.apply();
    }

    public String getWindowHeightPref(Context context) {

        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getString(windowHeight, "true");
    }

    public void setAccessCouponPref(Context context, String accessToken) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(access_coupon, accessToken);
        editor.apply();
    }

    public String getAccessCouponPref(Context context) {
        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getString(access_coupon, "");
    }

    public void setStateNamePref(Context context, String name) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(state_name, name);
        editor.apply();
    }

    public String getStateNamePref(Context context) {
        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getString(state_name, "");
    }

    public void setWatchListPref(Context context, Integer name) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(String.valueOf(watch_list), name);
        editor.apply();
    }

    public Integer getWatchListPref(Context context) {
        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getInt(String.valueOf(watch_list), 0);
    }

    public void setWatermarkEnablePref(Context context, String str) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(watermarkEnable, str);
        editor.apply();
    }

    public String getWatermarkEnablePref(Context context) {
        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getString(watermarkEnable, "1");
    }

    public void setWebsiteUrlPref(Context context, String str) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(websiteUrl, str);
        editor.apply();
    }

    public String getwebsiteUrlPref(Context context) {
        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getString(websiteUrl, "");
    }

    public void setWatermarkLogoUrlPref(Context context, String str) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(watermarklogourl, str);
        editor.apply();
    }

    public String getWatermarkLogoUrlPref(Context context) {
        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getString(watermarklogourl, "data");
    }

    public void setPairIDPref(Context context, String accessToken) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PairID, accessToken);
        editor.apply();
    }

    public String getPairIDPref(Context context) {
        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getString(PairID, "");
    }


    public void setPAIR_CODEPref(Context context, String str) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PAIR_CODE, str);
        editor.apply();
    }

    public String getPAIR_CODEPref(Context context) {
        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getString(PAIR_CODE, "PAIR_CODE");
    }

    public void setAccessTokenNPref(Context context, String accessToken) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(access_token, accessToken);
        editor.apply();
    }

    public String getAccessTokenPref(Context context) {
        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getString(access_token, "");
    }
    public void setLoginPref(Context context, String login) {
        SharedPreferences prefs = context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Login, login);
        editor.apply();
    }

    public String getLoginPref(Context context) {
        return context.getSharedPreferences(shared_preferences, Context.MODE_PRIVATE)
                .getString(Login, "");
    }

}
