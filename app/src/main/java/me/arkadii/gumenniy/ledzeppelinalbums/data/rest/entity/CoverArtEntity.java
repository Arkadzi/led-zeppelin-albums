package me.arkadii.gumenniy.ledzeppelinalbums.data.rest.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sebastian on 16.09.16.
 */
public class CoverArtEntity {

    private String collectionName;
    @SerializedName("artworkUrl100")
    private String artworkUrl;

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
