package me.arkadii.gumenniy.ledzeppelinalbums.presentation.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.arkadii.gumenniy.ledzeppelinalbums.R;

public class TimeDialogBroadcastReceiver extends BroadcastReceiver {
    public static final String DATE = "me.arkadii.gumenniy.extra.date";
    public static String ACTION = "me.arkadii.gumenniy.action.start";
    @Nullable
    private BroadcastListener listener;

    public TimeDialogBroadcastReceiver() {
    }

    public void setListener(@Nullable BroadcastListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION.equals(intent.getAction())) {
            Date date = (Date) intent.getSerializableExtra(DATE);
            if (date != null && listener != null) {
                listener.onBroadcastReceive(date);
            }
        }
    }

    public static void sendBroadcast(Context context, Date date) {
        Intent broadcastIntent = new Intent(TimeDialogBroadcastReceiver.ACTION);
        broadcastIntent.putExtra(TimeDialogBroadcastReceiver.DATE, date);
        context.sendBroadcast(broadcastIntent);
    }

    public interface BroadcastListener {
        void onBroadcastReceive(Date date);
    }
}
