package me.arkadii.gumenniy.ledzeppelinalbums.data.mapper;

/**
 * Created by sebastian on 16.09.16.
 */
public interface Mapper<F, T> {
    T transform(F from);
}
