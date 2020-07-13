package com.airwallex.rpn.core.action.operator;

import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.exception.MethodNotAllowedException;
import org.jetbrains.annotations.NotNull;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/11
 * @content:
 */
public class MultiplicationOperatorAction extends OperatorAction{

    private MultiplicationOperatorAction() {
    }

    @Override
    public int numberRequired() {
        return 2;
    }

    @Override
    public <T> @NotNull NumberAction doRun(@NotNull final T identity, @NotNull final NumberAction num) {
        throw new MethodNotAllowedException();
    }

    @Override
    public <T> @NotNull NumberAction doRun(@NotNull T identity, @NotNull NumberAction num1, @NotNull NumberAction num2) {
        return num1.multi(num2);
    }

    @Override
    public String ofAction() {
        return "*";
    }
}
