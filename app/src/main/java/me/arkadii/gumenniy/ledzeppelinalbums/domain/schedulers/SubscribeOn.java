package me.arkadii.gumenniy.ledzeppelinalbums.domain.schedulers;

import rx.Scheduler;

public interface SubscribeOn {
    Scheduler getScheduler();
}
