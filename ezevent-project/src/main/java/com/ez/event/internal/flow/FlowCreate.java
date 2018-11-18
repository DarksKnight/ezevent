package com.ez.event.internal.flow;

import com.ez.event.EzFlow;
import com.ez.event.internal.util.ConsumerUtil;

/**
 * @author shy
 * @date 2018/11/18
 */
public class FlowCreate extends EzFlow {

    private Object[] listObjs;

    public FlowCreate() {
        listObjs = new Object[1];
    }

    public FlowCreate(Object... listObjs) {
        this.listObjs = listObjs;
    }

    @Override
    protected void subscribeActual(Consumer consumer) {
        for (Object value : listObjs) {
            if (value instanceof RunnableNode) {
                new CreateConsumerEventEmitter(consumer)
                        .threadToken(((RunnableNode) value).getThreadToken())
                        .delay(((RunnableNode) value).getDelay())
                        .accept(((RunnableNode) value).getObj());
            } else {
                if(!ConsumerUtil.haveParent(consumer)) {
                    consumer.accept(value);
                } else {
                    consumer.accept(new RunnableNode(value));
                }
            }
        }
    }
}
