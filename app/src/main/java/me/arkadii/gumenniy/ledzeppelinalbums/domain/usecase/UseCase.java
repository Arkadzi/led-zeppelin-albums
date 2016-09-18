package me.arkadii.gumenniy.ledzeppelinalbums.domain.usecase;

import android.util.Log;

import me.arkadii.gumenniy.ledzeppelinalbums.domain.schedulers.ObserveOn;
import me.arkadii.gumenniy.ledzeppelinalbums.domain.schedulers.SubscribeOn;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public abstract class UseCase<T> {
    private SubscribeOn subscribeOn;
    private ObserveOn observeOn;
    private Subscription subscription = Subscriptions.empty();
    private Observable<T> observable;

    public UseCase(SubscribeOn subscribeOn, ObserveOn observeOn) {
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
    }

    public void execute(Subscriber<T> subscriber) {
        Log.e("subscription", "subscribe " + observable);
        if (observable == null)
            observable = getUseCaseObservable()
                    .subscribeOn(subscribeOn.getScheduler())
                    .observeOn(observeOn.getScheduler())
                    .cache()
                    .doOnError(new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            observable = null;
                        }
                    })
                    .doOnCompleted(new Action0() {
                        @Override
                        public void call() {
                            observable = null;
                        }
                    });
        subscription = observable.subscribe(subscriber);
    }

    protected abstract Observable<T> getUseCaseObservable();

    public boolean isWorking() {
        return observable != null;
    }

    public void unsubscribe() {
        Log.e("subscription", "unsubscribe");
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
