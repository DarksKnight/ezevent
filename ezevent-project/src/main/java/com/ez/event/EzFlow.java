package com.ez.event;

import com.ez.event.internal.flow.Consumer;
import com.ez.event.internal.flow.FlowCreate;
import com.ez.event.internal.flow.Function;
import com.ez.event.internal.flow.Predicate;
import com.ez.event.internal.thread.ThreadToken;
import com.ez.event.operator.flow.ConsumeOn;
import com.ez.event.operator.flow.Delay;
import com.ez.event.operator.flow.Filter;
import com.ez.event.operator.flow.Map;
import com.ez.event.operator.flow.Split;

/**
 * @author shy
 * @date 2018/11/18
 */
public abstract class EzFlow {

    public static EzFlow just() {
        return new FlowCreate();
    }

    public static EzFlow just(Object... values) {
        return new FlowCreate(values);
    }

    public EzFlow split(String splitCharset) {
        return new Split(this, splitCharset);
    }

    public EzFlow delay(int delay) {
        return new Delay(this, delay);
    }

    public EzFlow filter(Predicate predicate) {
        return new Filter(this, predicate);
    }

    public EzFlow map(Function function) {
        return new Map(this, function);
    }

    public EzFlow consumeOn(ThreadToken threadToken) {
        return new ConsumeOn(this, threadToken);
    }

    public void subscribe(Consumer consumer) {
        subscribeActual(consumer);
    }

    protected abstract void subscribeActual(Consumer consumer);
}
