package me.arkadii.gumenniy.ledzeppelinalbums.presentation;

import android.app.Application;
import android.content.Context;

import me.arkadii.gumenniy.ledzeppelinalbums.presentation.di.ApplicationComponent;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.di.ApplicationModule;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.di.DaggerApplicationComponent;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.service.AlarmService;

/**
 * Created by sebastian on 16.09.16.
 */
public class LZApplication extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        buildAppComponent();
        AlarmService.launch(this);
    }


    public static LZApplication getApp(Context context) {
        return (LZApplication) context.getApplicationContext();
    }


    private void buildAppComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

}
