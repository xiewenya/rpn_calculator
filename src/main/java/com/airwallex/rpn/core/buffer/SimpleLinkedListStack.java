package com.airwallex.rpn.core.buffer;

import com.airwallex.rpn.core.action.number.NumberAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content: thread not safe
 */
public class SimpleLinkedListStack extends RpnStack<String> {
    public static final String DEFAULT_KEY = "default";

    public SimpleLinkedListStack() {
        super(1);
    }

    public void push(NumberAction value) {
        super.push(DEFAULT_KEY, value);
    }

    @Nullable public NumberAction pop() {
        return super.pop(DEFAULT_KEY);
    }

    public void clear() {
        super.clear(DEFAULT_KEY);
    }

    public void clean() {
        super.clean(DEFAULT_KEY);
    }

    public int size() {
        return super.size(DEFAULT_KEY);
    }

    public boolean isEmpty() {
        return super.isEmpty(DEFAULT_KEY);
    }

    @Override
    public void push(String identity, NumberAction value) {
        super.push(DEFAULT_KEY, value);
    }

    @Override
    @Nullable public NumberAction pop(String identity) {
        return super.pop(DEFAULT_KEY);
    }

    @Override
    public void clear(@NotNull String identity) {
        super.clear(DEFAULT_KEY);
    }

    @Override
    public @NotNull List<NumberAction> clearWithReturn(@NotNull String identity) {
        return super.clearWithReturn(DEFAULT_KEY);
    }

    @Override
    public void clean(String identity) {
        super.clean(DEFAULT_KEY);
    }

    @Override
    public int size(String identity) {
        return super.size(DEFAULT_KEY);
    }

    @Override
    public boolean isEmpty(String identity) {
        return super.isEmpty(DEFAULT_KEY);
    }

    @Override
    public String toString() {
        return toString(DEFAULT_KEY);
    }

    @Override
    public String toString(String identity) {
        return super.toString(DEFAULT_KEY);
    }
}
