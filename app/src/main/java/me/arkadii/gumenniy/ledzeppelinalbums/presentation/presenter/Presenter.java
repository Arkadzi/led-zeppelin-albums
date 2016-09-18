package me.arkadii.gumenniy.ledzeppelinalbums.presentation.presenter;


import me.arkadii.gumenniy.ledzeppelinalbums.presentation.view.View;

/**
 * Created by sebastian on 28.06.16.
 */
public interface Presenter<V extends View> {
    void bind(V view);
    void unbind();
}
