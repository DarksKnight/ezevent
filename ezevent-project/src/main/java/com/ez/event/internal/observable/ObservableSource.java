package com.ez.event.internal.observable;

/**
 * @author shy
 */
public abstract class ObservableSource<T> {

    public abstract void subscribe(ObserverEventEmitter<T> emitter);
}
