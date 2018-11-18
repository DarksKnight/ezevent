package com.ez.event.internal.flow;

/**
 * @author shy
 * @date 2018/11/18
 */
public interface Predicate<T> {

    boolean apply(T value);
}
