package com.airwallex.rpn.core.action;

import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.Stack;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public interface Action {

    <T> void run(T identity, Stack<T, NumberAction> stack);

    ActionType ofType();

    String ofAction();

}
