package com.example.myapplication.Actvity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootOnStartActivity extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent intent2 = new Intent(context, SplashScreenActivityTv.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        }
    }
}
