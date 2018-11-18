package com.ez.event.operator.observable;

import com.ez.event.EzObservable;
import com.ez.event.internal.observable.AbstractObservable;
import com.ez.event.internal.observable.Observer;
import com.ez.event.internal.thread.ExecRunnable;
import com.ez.event.internal.thread.ThreadExecutors;
import com.ez.event.internal.thread.ThreadToken;

/**
 * @author shy
 */
public class SubscribeOn extends AbstractObservable {

    private ThreadToken threadToken;

    public SubscribeOn(EzObservable source, ThreadToken threadToken) {
        super(source);
        this.threadToken = threadToken;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        ThreadExecutors.run(new SubscribeRunnable(source, observer), threadToken, 0);
    }

    final class SubscribeRunnable extends ExecRunnable {

        private EzObservable observable;
        private Observer observer;

        public SubscribeRunnable(EzObservable observable, Observer observer) {
            this.observable = observable;
            this.observer = observer;
        }

        @Override
        public void execute() {
            observable.subscribe(observer);
        }
    }
}
