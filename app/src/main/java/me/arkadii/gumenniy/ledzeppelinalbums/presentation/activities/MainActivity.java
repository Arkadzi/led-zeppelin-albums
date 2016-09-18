package me.arkadii.gumenniy.ledzeppelinalbums.presentation.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.arkadii.gumenniy.ledzeppelinalbums.R;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.LZApplication;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.adapters.CoverArtAdapter;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.custom.listeners.RecyclerViewSwipeListener;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.model.CoverArt;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.presenter.CoverArtListPresenter;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.view.ListView;

public class MainActivity extends BroadcastActivity implements ListView<CoverArt>, CoverArtAdapter.OnItemClickListener {
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @Inject
    CoverArtListPresenter presenter;
    private CoverArtAdapter adapter;
    private ImageView lastPressedItemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LZApplication.getApp(this).getComponent().inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(
                new RecyclerViewSwipeListener() {
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int adapterPosition = viewHolder.getAdapterPosition();
                        presenter.onItemSwiped(adapterPosition);
                    }
                });
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView);
        adapter = new CoverArtAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        presenter.bind(this);

    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setData(List<CoverArt> data) {
        adapter.setData(data);
    }

    @Override
    public void navigateToDetails(CoverArt coverArt) {
        if (lastPressedItemImage != null) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.COVER, coverArt);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(this, lastPressedItemImage, getString(R.string.imageTransition));
                ActivityCompat.startActivity(this, intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }

    @Override
    public void removeItem(int position) {
        adapter.removeFrom(position);
    }

    @Override
    public void showToastMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(CoverArtAdapter.ViewHolder holder, CoverArt coverArt) {
        lastPressedItemImage = holder.getBind().albumCover;
        presenter.onItemClick(coverArt);
    }
}
