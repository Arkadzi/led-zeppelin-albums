package me.arkadii.gumenniy.ledzeppelinalbums.domain;


import java.util.List;

import me.arkadii.gumenniy.ledzeppelinalbums.presentation.model.CoverArt;
import rx.Observable;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public interface SessionRepository {
    Observable<List<CoverArt>> getAll(String term);
}
