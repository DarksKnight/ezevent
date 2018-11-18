package com.ez.event.operator.flow;

import com.ez.event.EzFlow;
import com.ez.event.internal.flow.AbstractConsumer;
import com.ez.event.internal.flow.AbstractFlow;
import com.ez.event.internal.flow.Consumer;
import com.ez.event.internal.flow.CreateConsumerEventEmitter;
import com.ez.event.internal.flow.Predicate;
import com.ez.event.internal.flow.RunnableNode;
import com.ez.event.internal.util.ConsumerUtil;

/**
 * @author shy
 * @date 2018/11/18
 */
public class Filter extends AbstractFlow {

    private Predicate predicate;

    public Filter(EzFlow source, Predicate predicate) {
        super(source);
        this.predicate = predicate;
    }

    @Override
    protected void subscribeActual(Consumer consumer) {
        source.subscribe(new FilterRunnable(consumer, predicate));
    }

    final class FilterRunnable extends AbstractConsumer<RunnableNode> {

        private Consumer consumer;
        private Predicate predicate;

        public FilterRunnable(Consumer consumer, Predicate predicate) {
            this.consumer = consumer;
            this.predicate = predicate;
        }

        @Override
        public void accept(RunnableNode value) {
            if (predicate.apply(value.getObj())) {
                if (!ConsumerUtil.haveParent(consumer)) {
                    new CreateConsumerEventEmitter(consumer)
                            .threadToken(value.getThreadToken())
                            .delay(value.getDelay())
                            .accept(value.getObj());
                } else {
                    consumer.accept(value);
                }
            }
        }
    }
}
