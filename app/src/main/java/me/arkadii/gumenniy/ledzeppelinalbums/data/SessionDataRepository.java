package me.arkadii.gumenniy.ledzeppelinalbums.data;

import java.util.List;

import me.arkadii.gumenniy.ledzeppelinalbums.data.mapper.CoverMapper;
import me.arkadii.gumenniy.ledzeppelinalbums.data.rest.RestApi;
import me.arkadii.gumenniy.ledzeppelinalbums.data.rest.entity.CoverArtEntity;
import me.arkadii.gumenniy.ledzeppelinalbums.data.rest.entity.GetResult;
import me.arkadii.gumenniy.ledzeppelinalbums.domain.SessionRepository;
import me.arkadii.gumenniy.ledzeppelinalbums.presentation.model.CoverArt;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by sebastian on 16.09.16.
 */
public class SessionDataRepository implements SessionRepository {
    private final CoverMapper coverMapper;
    private RestApi restApi;

    public SessionDataRepository(RestApi restApi) {
        this.restApi = restApi;
        coverMapper = new CoverMapper();
    }

    @Override
    public Observable<List<CoverArt>> getAll(String term) {
        return restApi.getAll(term).map(new Func1<GetResult<CoverArtEntity>, List<CoverArtEntity>>() {
            @Override
            public List<CoverArtEntity> call(GetResult<CoverArtEntity> coverArtEntityGetResult) {
                return coverArtEntityGetResult.getResults();
            }
        }).flatMapIterable(new Func1<List<CoverArtEntity>, List<CoverArtEntity>>() {
            @Override
            public List<CoverArtEntity> call(List<CoverArtEntity> coverArtEntities) {
                return coverArtEntities;
            }
        }).map(new Func1<CoverArtEntity, CoverArt>() {
            @Override
            public CoverArt call(CoverArtEntity coverArtEntity) {
                return coverMapper.transform(coverArtEntity);
            }
        }).distinct().toList();
    }
}
