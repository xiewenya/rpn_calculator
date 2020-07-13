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
public class SqrtOperatorAction extends OperatorAction{

    private SqrtOperatorAction() {
    }

    @Override
    public int numberRequired() {
        return 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> @NotNull NumberAction doRun(@NotNull final T identity, @NotNull final NumberAction num) {
        return num.sqrt();
    }

    @Override
    public <T> @NotNull NumberAction doRun(@NotNull final T identity, @NotNull final NumberAction num1, @NotNull final NumberAction num2) {
        throw new MethodNotAllowedException();
    }

    @Override
    public String ofAction() {
        return "sqrt";
    }
}
