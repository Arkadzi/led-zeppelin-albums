package me.arkadii.gumenniy.ledzeppelinalbums.presentation.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.arkadii.gumenniy.ledzeppelinalbums.R;
import me.arkadii.gumenniy.ledzeppelinalbums.data.Constants;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.custom.SquareImageView;

/**
 * Created by sebastian on 18.09.16.
 */
public class DateDialogFragment extends DialogFragment {

    public static final String TAG = "date_dialog";
    private static final String DATE = "date";

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    private Date date;

    public static DateDialogFragment newInstance(@NonNull Date date) {
        DateDialogFragment fragment = new DateDialogFragment();

        Bundle args = new Bundle();
        args.putSerializable(DATE, date);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date = (Date) getArguments().getSerializable(DATE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String formattedDate = simpleDateFormat.format(date);

        SquareImageView imageView = new SquareImageView(getActivity());
        imageView.setMaxWidth(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(params);
        Picasso.with(getActivity()).load(Constants.DIALOG_PICTURE_URL).into(imageView);

        String title = String.format("%s - %s", getString(R.string.currentTime), formattedDate);
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setView(imageView)
                .create();
    }
}
