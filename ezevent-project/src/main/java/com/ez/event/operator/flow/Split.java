package com.ez.event.operator.flow;

import com.ez.event.EzFlow;
import com.ez.event.internal.flow.AbstractConsumer;
import com.ez.event.internal.flow.AbstractFlow;
import com.ez.event.internal.flow.Consumer;
import com.ez.event.internal.flow.RunnableNode;
import com.ez.event.internal.util.ConsumerUtil;

/**
 * @author shy
 * @date 2018/11/18
 */
public class Split extends AbstractFlow {

    private String splitCharset;

    public Split(EzFlow source, String splitCharset) {
        super(source);
        this.splitCharset = splitCharset;
    }

    @Override
    protected void subscribeActual(Consumer consumer) {
        source.subscribe(new SplitConsumer(consumer, splitCharset));
    }

    final class SplitConsumer extends AbstractConsumer<RunnableNode<String>> {

        private Consumer consumer;
        private String splitCharset;

        public SplitConsumer(Consumer consumer, String splitCharset) {
            this.consumer = consumer;
            this.splitCharset = splitCharset;
        }

        @Override
        public void accept(RunnableNode<String> value) {
            String[] array = value.getObj().split(splitCharset);
            for (String arrValue : array) {
                if (!ConsumerUtil.haveParent(consumer)) {
                    consumer.accept(arrValue);
                } else {
                    consumer.accept(new RunnableNode(arrValue));
                }
            }
        }
    }
}
