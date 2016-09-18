package me.arkadii.gumenniy.ledzeppelinalbums.presentation.presenter;

import android.widget.BaseAdapter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.arkadii.gumenniy.ledzeppelinalbums.data.Constants;
import me.arkadii.gumenniy.ledzeppelinalbums.data.ResponseErrorHandler;
import me.arkadii.gumenniy.ledzeppelinalbums.domain.subscribers.BaseProgressSubscriber;
import me.arkadii.gumenniy.ledzeppelinalbums.domain.usecase.GetAllCoverArtsUseCase;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.model.CoverArt;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.view.ListView;
import rx.Subscriber;

/**
 * Created by sebastian on 17.09.16.
 */
@Singleton
public class CoverArtListPresenter extends BasePresenter<ListView<CoverArt>> implements BaseProgressSubscriber.ProgressSubscriberListener {
    private GetAllCoverArtsUseCase useCase;
    private ResponseErrorHandler responseErrorHandler;

    @Inject
    public CoverArtListPresenter(GetAllCoverArtsUseCase useCase, ResponseErrorHandler responseErrorHandler) {
        this.useCase = useCase;
        this.responseErrorHandler = responseErrorHandler;
    }

    @Override
    public void bind(ListView<CoverArt> view) {
        super.bind(view);
//        if (!useCase.isWorking()) {
            useCase.setTerm(Constants.TERM);
            useCase.execute(getSubscriber());
//        }
    }

    @Override
    public void unbind() {
        useCase.unsubscribe();
        super.unbind();
    }

    private Subscriber<List<CoverArt>> getSubscriber() {
        return new BaseProgressSubscriber<List<CoverArt>>(this) {
            @Override
            public void onNext(List<CoverArt> response) {
                super.onNext(response);
                ListView<CoverArt> view = getView();
                if (view != null) {
                    view.setData(response);
                }
            }
        };
    }

    @Override
    public void onError(Throwable t) {
        String error = responseErrorHandler.handleError(t);
        ListView<CoverArt> view = getView();
        if (view != null) {
            view.hideProgress();
            view.showToastMessage(error);
        }
    }

    @Override
    public void onCompleted() {
        ListView<CoverArt> view = getView();
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void onStartLoading() {
        ListView<CoverArt> view = getView();
        if (view != null) {
            view.showProgress();
        }
    }

    public void onItemClick(CoverArt coverArt) {
        ListView<CoverArt> view = getView();
        if (view != null) {
            view.navigateToDetails(coverArt);
        }
    }

    public void onItemSwiped(int position) {
        List<CoverArt> cache = useCase.getCache();
        if (cache != null && cache.size() > position) {
            cache.remove(position);
        }
        ListView<CoverArt> view = getView();
        if (view != null) {
            view.removeItem(position);
        }
    }
}
