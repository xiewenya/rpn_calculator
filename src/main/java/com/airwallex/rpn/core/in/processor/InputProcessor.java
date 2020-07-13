package com.airwallex.rpn.core.in.processor;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.exception.ActionNotSupportException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content: for different strategy, button input mode, string line input mode
 */
public interface InputProcessor<T> {
    @NotNull List<Action> process(@NotNull final T commands) throws ActionNotSupportException;
}
