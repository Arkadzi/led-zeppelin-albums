package me.arkadii.gumenniy.ledzeppelinalbums.presentation.di;

import javax.inject.Singleton;

import dagger.Component;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.activities.MainActivity;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
