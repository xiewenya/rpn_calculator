package com.airwallex.rpn.core.buffer;

import com.airwallex.rpn.core.action.number.NumberAction;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content: a stack to support multi-tenancy
 */
public class RpnStack<T> extends AbstractStack<T, NumberAction> {

    public RpnStack(HashMap<T, Deque<NumberAction>> dequeMap) {
        super(dequeMap);
    }

    public RpnStack(int length) {
        super(length);
    }

    Deque<NumberAction> newDeque() {
        return new LinkedList<>();
    }

}
