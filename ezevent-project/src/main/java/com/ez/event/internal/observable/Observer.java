package com.ez.event.internal.observable;

/**
 * @author shy
 */
public interface Observer<T> {

    void onNext(T value);

    void onError(Throwable throwable);

    void onComplete();
}
