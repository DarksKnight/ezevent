package com.ez.event.internal.flow;

/**
 * @author shy
 * @date 2018/11/18
 */
public interface ConsumerEventEmitter<T> {

    void accept(T value);
}
