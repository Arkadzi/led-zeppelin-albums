package me.arkadii.gumenniy.ledzeppelinalbums.domain.usecase;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import me.arkadii.gumenniy.ledzeppelinalbums.domain.SessionRepository;
import me.arkadii.gumenniy.ledzeppelinalbums.domain.schedulers.ObserveOn;
import me.arkadii.gumenniy.ledzeppelinalbums.domain.schedulers.SubscribeOn;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.model.CoverArt;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by sebastian on 17.09.16.
 */
public class GetAllCoverArtsUseCase extends UseCase<List<CoverArt>> {

    private SessionRepository sessionRepository;
    private String term;
    @Nullable
    private List<CoverArt> cache;

    @Inject
    public GetAllCoverArtsUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
                                  SessionRepository sessionRepository) {
        super(subscribeOn, observeOn);
        this.sessionRepository = sessionRepository;
    }


    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    protected Observable<List<CoverArt>> getUseCaseObservable() {
        if (cache == null) {
            return sessionRepository.getAll(term).doOnNext(new Action1<List<CoverArt>>() {
                @Override
                public void call(List<CoverArt> coverArts) {
                    cache = coverArts;
                }
            });
        } else {
            return Observable.just(cache);
        }
    }

    @Nullable
    public List<CoverArt> getCache() {
        return cache;
    }
}
