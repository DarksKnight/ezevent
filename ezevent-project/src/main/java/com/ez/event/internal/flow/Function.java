package com.ez.event.internal.flow;

/**
 * @author shy
 * @date 2018/11/18
 */
public interface Function<T, K> {

    T apply (K value);
}
