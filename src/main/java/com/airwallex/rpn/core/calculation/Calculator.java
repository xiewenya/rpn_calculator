package com.airwallex.rpn.core.calculation;

import com.airwallex.rpn.core.action.Action;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content: should be careful about the stack, do not let other object hold strong references to it
 */
public interface Calculator<T> {

    /**
     * run the command received
     * @param actions
     */
    void eval(@NotNull final List<Action> actions);

    @NotNull T getIdentity();

}
