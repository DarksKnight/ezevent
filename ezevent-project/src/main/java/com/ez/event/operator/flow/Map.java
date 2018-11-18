package com.ez.event.operator.flow;

import com.ez.event.EzFlow;
import com.ez.event.internal.flow.AbstractConsumer;
import com.ez.event.internal.flow.AbstractFlow;
import com.ez.event.internal.flow.Consumer;
import com.ez.event.internal.flow.CreateConsumerEventEmitter;
import com.ez.event.internal.flow.Function;
import com.ez.event.internal.flow.RunnableNode;
import com.ez.event.internal.util.ConsumerUtil;

/**
 * @author shy
 * @date 2018/11/18
 */
public class Map extends AbstractFlow {

    private Function function;

    public Map(EzFlow source, Function function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Consumer consumer) {
        source.subscribe(new MapRunnable(consumer, function));
    }

    final class MapRunnable extends AbstractConsumer<RunnableNode> {

        private Consumer consumer;
        private Function function;

        public MapRunnable(Consumer consumer, Function function) {
            this.consumer = consumer;
            this.function = function;
        }

        @Override
        public void accept(RunnableNode value) {
            value.setObj(function.apply(value.getObj()));
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
