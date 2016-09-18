package me.arkadii.gumenniy.ledzeppelinalbums.presentation.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.arkadii.gumenniy.ledzeppelinalbums.R;
import me.arkadii.gumenniy.ledzeppelinalbums.databinding.CoverArtItemBinding;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.model.CoverArt;

/**
 * Created by sebastian on 16.09.16.
 */
public class CoverArtAdapter extends RecyclerView.Adapter<CoverArtAdapter.ViewHolder> {

    private final List<CoverArt> data = new ArrayList<>();
    @Nullable
    private OnItemClickListener onItemClickListener;

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CoverArtItemBinding binding = CoverArtItemBinding.inflate(inflater, parent, false);
                DataBindingUtil.inflate(inflater, R.layout.cover_art_item, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CoverArt coverArt = data.get(position);
        holder.bind.setCoverArt(coverArt);
        holder.bind.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder, coverArt);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<CoverArt> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void removeFrom(int adapterPosition) {
        data.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder, CoverArt coverArt);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CoverArtItemBinding bind;

        public ViewHolder(View itemView) {
            super(itemView);
            bind = DataBindingUtil.bind(itemView);
        }

        public CoverArtItemBinding getBind() {
            return bind;
        }
    }
}
