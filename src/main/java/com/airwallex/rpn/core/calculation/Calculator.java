package com.airwallex.rpn.core.calculation;

import com.airwallex.rpn.core.action.Action;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public interface Calculator<T> {

    void eval(@NotNull final List<Action> actions);

    @NotNull T getIdentity();

}
