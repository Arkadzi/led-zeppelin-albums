package me.arkadii.gumenniy.ledzeppelinalbums.presentation.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class AlarmService extends Service {

    public static final int REQUEST_CODE = 1101;
    public static final int TRIGGER_AT_MILLIS = 2 * 60 * 1000;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        launch(this);
        Date date = Calendar.getInstance().getTime();
        TimeDialogBroadcastReceiver.sendBroadcast(this, date);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    public static void launch(Context context) {
        Intent serviceIntent = new Intent(context.getApplicationContext(), AlarmService.class);
        PendingIntent servicePendingIntent = PendingIntent.getService(
                context.getApplicationContext(), REQUEST_CODE, serviceIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.cancel(servicePendingIntent);

        long firstLaunch = System.currentTimeMillis() / 1000 / 60;
        firstLaunch *= (60 * 1000);
        firstLaunch += TRIGGER_AT_MILLIS;
        am.setRepeating(AlarmManager.RTC_WAKEUP, firstLaunch, TRIGGER_AT_MILLIS, servicePendingIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
