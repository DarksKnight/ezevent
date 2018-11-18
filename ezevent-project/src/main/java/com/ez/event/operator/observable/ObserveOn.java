package com.ez.event.operator.observable;

import com.ez.event.EzObservable;
import com.ez.event.internal.observable.AbstractObservable;
import com.ez.event.internal.observable.CreateObserverEventEmitter;
import com.ez.event.internal.observable.Observer;
import com.ez.event.internal.thread.ThreadToken;

/**
 * @author shy
 */
public class ObserveOn extends AbstractObservable {

    private ThreadToken threadToken;

    public ObserveOn(EzObservable source, ThreadToken threadToken) {
        super(source);
        this.threadToken = threadToken;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        source.subscribe(new ObserveOnRunnable(observer, threadToken));
    }

    final class ObserveOnRunnable<T> implements Observer<T> {

        private Observer observer;
        private ThreadToken threadToken;

        public ObserveOnRunnable(Observer observer, ThreadToken threadToken) {
            this.observer = observer;
            this.threadToken = threadToken;
        }

        @Override
        public void onNext(T value) {
            new CreateObserverEventEmitter<T>(observer)
                    .threadToken(threadToken)
                    .onNext(value);
        }

        @Override
        public void onError(Throwable throwable) {
            new CreateObserverEventEmitter<T>(observer)
                    .threadToken(threadToken)
                    .onError(throwable);
        }

        @Override
        public void onComplete() {
            new CreateObserverEventEmitter<T>(observer)
                    .threadToken(threadToken)
                    .onComplete();
        }
    }
}
