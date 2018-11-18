package com.ez.event.internal.flow;

import com.ez.event.EzFlow;

/**
 * @author shy
 * @date 2018/11/18
 */
public abstract class AbstractFlow extends EzFlow {

    protected EzFlow source;

    public AbstractFlow(EzFlow source) {
        this.source = source;
    }
}
