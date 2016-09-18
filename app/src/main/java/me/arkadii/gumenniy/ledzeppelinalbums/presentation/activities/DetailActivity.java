package me.arkadii.gumenniy.ledzeppelinalbums.presentation.activities;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.arkadii.gumenniy.ledzeppelinalbums.R;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.model.CoverArt;

public class DetailActivity extends BroadcastActivity {

    public static final String COVER = "cover";
    @BindView(R.id.album_cover)
    ImageView imageView;
    @BindView(R.id.album_title)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        CoverArt coverArt = (CoverArt) getIntent().getSerializableExtra(COVER);
        Picasso.with(this).load(coverArt.getUrl()).into(imageView);
        textView.setText(coverArt.getName());

    }



    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
