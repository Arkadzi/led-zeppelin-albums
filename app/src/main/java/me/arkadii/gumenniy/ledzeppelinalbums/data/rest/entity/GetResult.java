package me.arkadii.gumenniy.ledzeppelinalbums.data.rest.entity;

import java.util.List;

/**
 * Created by sebastian on 17.09.16.
 */
public class GetResult<T> {
    int resultsCount;
    List<T> results;

    public int getResultsCount() {
        return resultsCount;
    }

    public List<T> getResults() {
        return results;
    }
}
