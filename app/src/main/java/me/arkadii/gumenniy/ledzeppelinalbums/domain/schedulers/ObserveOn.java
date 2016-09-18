package me.arkadii.gumenniy.ledzeppelinalbums.domain.schedulers;

import rx.Scheduler;

public interface ObserveOn {
    Scheduler getScheduler();
}
