package com.ez.event.internal.observable;

import com.ez.event.EzObservable;

/**
 * @author shy
 */
public abstract class AbstractObservable extends EzObservable {

    protected EzObservable source;

    public AbstractObservable(EzObservable source) {
        this.source = source;
    }
}
