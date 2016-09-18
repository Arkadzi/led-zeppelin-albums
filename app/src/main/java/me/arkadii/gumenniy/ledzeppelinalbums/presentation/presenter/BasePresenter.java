package me.arkadii.gumenniy.ledzeppelinalbums.presentation.presenter;

import android.support.annotation.Nullable;

import me.arkadii.gumenniy.ledzeppelinalbums.presentation.view.View;


public abstract class BasePresenter<V extends View> implements Presenter<V> {

    @Nullable
    private V view;

    @Override
    public void bind(V view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        this.view = null;
    }

    @Nullable
    public V getView() {
        return view;
    }
}
