package com.ez.event.operator.flow;

import com.ez.event.EzFlow;
import com.ez.event.internal.flow.AbstractConsumer;
import com.ez.event.internal.flow.AbstractFlow;
import com.ez.event.internal.flow.Consumer;
import com.ez.event.internal.flow.CreateConsumerEventEmitter;
import com.ez.event.internal.flow.RunnableNode;
import com.ez.event.internal.util.ConsumerUtil;

/**
 * @author shy
 * @date 2018/11/18
 */
public class Delay extends AbstractFlow {

    private int delay;

    public Delay(EzFlow source, int delay) {
        super(source);
        this.delay = delay;
    }

    @Override
    protected void subscribeActual(Consumer consumer) {
        source.subscribe(new DelayConsumer(consumer, delay));
    }

    final class DelayConsumer extends AbstractConsumer<RunnableNode> {

        private Consumer consumer;
        private int delay;

        public DelayConsumer(Consumer consumer, int delay) {
            this.consumer = consumer;
            this.delay = delay;
        }

        @Override
        public void accept(RunnableNode value) {
            if (!ConsumerUtil.haveParent(consumer)) {
                new CreateConsumerEventEmitter(consumer)
                        .threadToken(value.getThreadToken())
                        .delay(delay)
                        .accept(value.getObj());
            } else {
                value.setDelay(delay);
                consumer.accept(value);
            }
        }
    }
}
