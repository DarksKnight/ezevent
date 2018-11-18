package com.ez.event;

import com.ez.event.internal.observable.ObservableCreate;
import com.ez.event.internal.observable.ObservableSource;
import com.ez.event.internal.observable.Observer;
import com.ez.event.internal.thread.ThreadToken;
import com.ez.event.operator.observable.ObserveOn;
import com.ez.event.operator.observable.SubscribeOn;

/**
 * @author shy
 */
public abstract class EzObservable {

    public static EzObservable create(ObservableSource source) {
        return new ObservableCreate(source);
    }

    public EzObservable subscribeOn(ThreadToken threadToken) {
        return new SubscribeOn(this, threadToken);
    }

    public EzObservable observeOn(ThreadToken threadToken) {
        return new ObserveOn(this, threadToken);
    }

    public void subscribe(Observer observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer observer);
}
