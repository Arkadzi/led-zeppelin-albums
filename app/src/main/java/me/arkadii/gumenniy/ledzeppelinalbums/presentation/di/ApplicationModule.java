package me.arkadii.gumenniy.ledzeppelinalbums.presentation.di;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.arkadii.gumenniy.ledzeppelinalbums.data.di.DataModule;
import me.arkadii.gumenniy.ledzeppelinalbums.domain.schedulers.ObserveOn;
import me.arkadii.gumenniy.ledzeppelinalbums.domain.schedulers.SubscribeOn;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.LZApplication;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module(includes = {DataModule.class})
public class ApplicationModule {
    private final LZApplication application;

    public ApplicationModule(Context application) {
        this.application = (LZApplication) application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    SubscribeOn provideSubscribeOn() {
        return new SubscribeOn() {
            @Override
            public Scheduler getScheduler() {
                return Schedulers.newThread();
            }
        };
    }

    @Singleton
    @Provides
    ObserveOn provideObserveOn() {
        return new ObserveOn() {
            @Override
            public Scheduler getScheduler() {
                return AndroidSchedulers.mainThread();
            }
        };
    }
}
