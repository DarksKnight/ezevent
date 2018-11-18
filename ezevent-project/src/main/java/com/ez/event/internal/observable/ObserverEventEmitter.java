package com.ez.event.internal.observable;

/**
 * @author shy
 */
public interface ObserverEventEmitter<T> {

    void onNext(T value);

    void onError(Throwable throwable);

    void onComplete();
}
