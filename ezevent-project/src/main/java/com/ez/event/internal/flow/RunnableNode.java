package com.ez.event.internal.flow;

import com.ez.event.internal.thread.ThreadToken;

/**
 * @author shy
 * @date 2018/11/18
 */
public class RunnableNode<T> {

    private T obj;
    private ThreadToken threadToken;
    private int delay;

    public RunnableNode(T obj) {
        this(obj, ThreadToken.DEFAULT, 0);
    }

    public RunnableNode(T obj, ThreadToken threadToken) {
        this(obj, threadToken, 0);
    }

    public RunnableNode(T obj, ThreadToken threadToken, int delay) {
        this.obj = obj;
        this.threadToken = threadToken;
        this.delay = delay;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public ThreadToken getThreadToken() {
        return threadToken;
    }

    public void setThreadToken(ThreadToken threadToken) {
        this.threadToken = threadToken;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
