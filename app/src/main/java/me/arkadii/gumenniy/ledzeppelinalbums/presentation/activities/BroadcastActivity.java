package me.arkadii.gumenniy.ledzeppelinalbums.presentation.activities;

import android.content.IntentFilter;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

import me.arkadii.gumenniy.ledzeppelinalbums.presentation.dialogs.DateDialogFragment;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.service.TimeDialogBroadcastReceiver;

/**
 * Created by sebastian on 18.09.16.
 */
public class BroadcastActivity extends AppCompatActivity
        implements TimeDialogBroadcastReceiver.BroadcastListener {

    private final TimeDialogBroadcastReceiver timeDialogBroadcastReceiver = new TimeDialogBroadcastReceiver();

    @Override
    protected void onStart() {
        super.onStart();
        timeDialogBroadcastReceiver.setListener(this);
        IntentFilter intentFilter = new IntentFilter(TimeDialogBroadcastReceiver.ACTION);
        registerReceiver(timeDialogBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        timeDialogBroadcastReceiver.setListener(null);
        unregisterReceiver(timeDialogBroadcastReceiver);
        super.onStop();
    }

    @Override
    public void onBroadcastReceive(Date date) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        DialogFragment fragment = (DialogFragment) supportFragmentManager
                .findFragmentByTag(DateDialogFragment.TAG);
        if (fragment != null) {
            fragment.dismiss();
        }
        DateDialogFragment dateDialogFragment = DateDialogFragment.newInstance(date);
        dateDialogFragment.show(supportFragmentManager, DateDialogFragment.TAG);
    }

}
