package me.arkadii.gumenniy.ledzeppelinalbums.data.rest;

import java.util.List;

import me.arkadii.gumenniy.ledzeppelinalbums.data.rest.entity.CoverArtEntity;
import me.arkadii.gumenniy.ledzeppelinalbums.data.rest.entity.GetResult;
import rx.Observable;

/**
 * Created by sebastian on 16.09.16.
 */
public class RestApi {
    private RetrofitApi api;

    public RestApi(RetrofitApi api) {

        this.api = api;
    }

    public Observable<GetResult<CoverArtEntity>> getAll(String term) {
        return api.getAll(term);
    }
}
