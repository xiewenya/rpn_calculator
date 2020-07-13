package com.airwallex.rpn.core.calculation;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.buffer.RpnStack;
import com.airwallex.rpn.core.out.BasePrinter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.StringJoiner;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public abstract class AbstractCalculator<T> implements Calculator<T> {

    private final RpnStack<T> stack;

    private final T identity;

    public AbstractCalculator(RpnStack<T> stack) {
        this.stack = stack;
        this.identity = genIdentity();
    }

    @SuppressWarnings("unchecked")
    public <M> void print(BasePrinter<M> printer) {
        printer.toPrint((M) stack.toString(identity));
    }

    abstract protected T genIdentity();

    @Override
    @NotNull public T getIdentity() {
        return identity;
    }
    @Override
    public void eval(@NotNull final List<Action> actions) {
        actions.forEach(action -> action.run(identity, stack));
    }

    @Override
    public String toString() {
        return new StringJoiner(" ")
                .add(stack.toString(identity))
                .toString();
    }
}
