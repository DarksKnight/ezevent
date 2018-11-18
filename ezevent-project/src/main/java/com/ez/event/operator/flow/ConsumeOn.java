package com.ez.event.operator.flow;

import com.ez.event.EzFlow;
import com.ez.event.internal.flow.AbstractConsumer;
import com.ez.event.internal.flow.AbstractFlow;
import com.ez.event.internal.flow.Consumer;
import com.ez.event.internal.flow.CreateConsumerEventEmitter;
import com.ez.event.internal.flow.RunnableNode;
import com.ez.event.internal.thread.ThreadToken;
import com.ez.event.internal.util.ConsumerUtil;

/**
 * @author shy
 * @date 2018/11/18
 */
public class ConsumeOn extends AbstractFlow {

    private ThreadToken threadToken;

    public ConsumeOn(EzFlow source, ThreadToken threadToken) {
        super(source);
        this.threadToken = threadToken;
    }

    @Override
    protected void subscribeActual(Consumer consumer) {
        source.subscribe(new ConsumeOnConsumer(consumer, threadToken));
    }

    final class ConsumeOnConsumer extends AbstractConsumer<RunnableNode> {

        private Consumer consumer;
        private ThreadToken threadToken;

        public ConsumeOnConsumer(Consumer consumer, ThreadToken threadToken) {
            this.consumer = consumer;
            this.threadToken = threadToken;
        }

        @Override
        public void accept(RunnableNode value) {
            if (!ConsumerUtil.haveParent(consumer)) {
                new CreateConsumerEventEmitter(consumer)
                        .threadToken(threadToken)
                        .delay(value.getDelay())
                        .accept(value.getObj());
            } else {
                value.setThreadToken(threadToken);
                consumer.accept(value);
            }
        }
    }
}
