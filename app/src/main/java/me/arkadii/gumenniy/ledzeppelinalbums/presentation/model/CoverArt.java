package me.arkadii.gumenniy.ledzeppelinalbums.presentation.model;

import android.databinding.Bindable;

import java.io.Serializable;

/**
 * Created by sebastian on 16.09.16.
 */
public class CoverArt implements Serializable {
    private String url;
    private String thumbnailUrl;
    private String name;

    public CoverArt(String url, String thumbnailUrl, String name) {
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoverArt coverArt = (CoverArt) o;

        return name.equals(coverArt.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
