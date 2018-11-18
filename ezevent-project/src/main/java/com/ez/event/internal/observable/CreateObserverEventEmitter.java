package com.ez.event.internal.observable;

import com.ez.event.internal.thread.ExecRunnable;
import com.ez.event.internal.thread.ThreadExecutors;
import com.ez.event.internal.thread.ThreadToken;

/**
 * @author shy
 */
public class CreateObserverEventEmitter<T> implements ObserverEventEmitter<T> {

    private Observer observer;
    private ThreadToken threadToken = ThreadToken.DEFAULT;

    public CreateObserverEventEmitter(Observer observer) {
        this.observer = observer;
    }

    public CreateObserverEventEmitter threadToken(ThreadToken threadToken) {
        this.threadToken = threadToken;
        return this;
    }

    @Override
    public void onNext(T value) {
        ThreadExecutors.run(new OnNextRunnable(value), threadToken, 0);
    }

    @Override
    public void onError(Throwable throwable) {
        ThreadExecutors.run(new OnErrorRunnable(throwable), threadToken, 0);
    }

    @Override
    public void onComplete() {
        ThreadExecutors.run(new OnCompleteRunnable(), threadToken, 0);
    }

    final class OnNextRunnable extends ExecRunnable {

        private T value;

        public OnNextRunnable(T value) {
            this.value = value;
        }

        @Override
        public void execute() {
            observer.onNext(value);
        }
    }

    final class OnErrorRunnable extends ExecRunnable {

        private Throwable throwable;

        public OnErrorRunnable(Throwable throwable) {
            this.throwable = throwable;
        }

        @Override
        public void execute() {
            observer.onError(throwable);
        }
    }

    final class OnCompleteRunnable extends ExecRunnable {

        @Override
        public void execute() {
            observer.onComplete();
        }
    }
}
