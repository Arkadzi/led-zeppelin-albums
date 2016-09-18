package me.arkadii.gumenniy.ledzeppelinalbums.data.mapper;

import me.arkadii.gumenniy.ledzeppelinalbums.data.rest.entity.CoverArtEntity;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.model.CoverArt;

/**
 * Created by sebastian on 16.09.16.
 */
public class CoverMapper implements Mapper<CoverArtEntity, CoverArt> {

    @Override
    public CoverArt transform(CoverArtEntity from) {
        String thumbnail = from.getArtworkUrl();
        String url = thumbnail.replace("100x100bb.jpg", "400x400bb.jpg");
        String collectionName = from.getCollectionName();
        return new CoverArt(url, thumbnail, collectionName);
    }
}
