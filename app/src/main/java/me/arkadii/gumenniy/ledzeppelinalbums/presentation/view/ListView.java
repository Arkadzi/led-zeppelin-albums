package me.arkadii.gumenniy.ledzeppelinalbums.presentation.view;


import java.util.List;

public interface ListView<T> extends View {
    void showProgress();

    void hideProgress();

    void setData(List<T> data);

    void navigateToDetails(T coverArt);

    void removeItem(int position);
}
