package com.example.hardik.automateddriverdrowsinesscontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Show a notification or perform any action when the alarm triggers
        Toast.makeText(context, "Time to take a break! grab some tea", Toast.LENGTH_SHORT).show();
    }
}

