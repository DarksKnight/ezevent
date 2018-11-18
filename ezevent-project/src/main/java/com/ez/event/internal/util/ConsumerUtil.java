package com.ez.event.internal.util;

import com.ez.event.internal.flow.AbstractConsumer;
import com.ez.event.internal.flow.Consumer;

/**
 * @author shy
 * @date 2018/11/18
 */
public class ConsumerUtil {

    public synchronized static boolean haveParent(Consumer consumer) {
        if (consumer instanceof AbstractConsumer) {
            return true;
        } else {
            return false;
        }
    }
}
