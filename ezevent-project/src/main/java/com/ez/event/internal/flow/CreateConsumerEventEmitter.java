package com.ez.event.internal.flow;

import com.ez.event.internal.thread.ExecRunnable;
import com.ez.event.internal.thread.ThreadExecutors;
import com.ez.event.internal.thread.ThreadToken;

/**
 * @author shy
 * @date 2018/11/18
 */
public class CreateConsumerEventEmitter implements ConsumerEventEmitter {

    private Consumer consumer;
    private ThreadToken threadToken = ThreadToken.DEFAULT;
    private int delay = 0;

    public CreateConsumerEventEmitter(Consumer consumer) {
        this.consumer = consumer;
    }

    public CreateConsumerEventEmitter threadToken(ThreadToken threadToken) {
        this.threadToken = threadToken;
        return this;
    }

    public CreateConsumerEventEmitter delay(int delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public void accept(Object value) {
        ThreadExecutors.run(new AcceptRunnable(value), threadToken, delay);
    }

    final class AcceptRunnable<T> extends ExecRunnable {

        private T value;

        public AcceptRunnable(T value) {
            this.value = value;
        }

        @Override
        public void execute() {
            consumer.accept(value);
        }
    }
}
