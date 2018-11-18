package com.ez.event.internal.observable;

import com.ez.event.EzObservable;

/**
 * @author shy
 */
public class ObservableCreate<T> extends EzObservable {

    private ObservableSource source;

    public ObservableCreate(ObservableSource source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        source.subscribe(new CreateObserverEventEmitter<T>(observer));
    }
}
