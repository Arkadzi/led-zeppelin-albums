package me.arkadii.gumenniy.ledzeppelinalbums.data.rest;

import java.util.List;

import me.arkadii.gumenniy.ledzeppelinalbums.data.rest.entity.CoverArtEntity;
import me.arkadii.gumenniy.ledzeppelinalbums.data.rest.entity.GetResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sebastian on 16.09.16.
 */
public interface RetrofitApi {
    @GET("search")
    Observable<GetResult<CoverArtEntity>> getAll(@Query("term") String term);
}
