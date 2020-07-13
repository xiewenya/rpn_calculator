package com.airwallex.rpn.client;

import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.UndoCache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public class SimpleUndoCache extends UndoCache {
    private static final String DEFAULT_KEY = "default";

    public SimpleUndoCache() {
        super(1);
    }

    public void push(UndoCache.CacheLog<NumberAction> value) {
        super.push(DEFAULT_KEY, value);
    }

    @Nullable public UndoCache.CacheLog<NumberAction> pop() {
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
    public void push(Object identity, CacheLog<NumberAction> value) {
        super.push(DEFAULT_KEY, value);
    }

    @Nullable
    @Override
    public CacheLog<NumberAction> pop(Object identity) {
        return super.pop(DEFAULT_KEY);
    }

    @Override
    public void clear(@NotNull Object identity) {
        super.clear(DEFAULT_KEY);
    }

    @Override
    public @NotNull List<CacheLog<NumberAction>> clearWithReturn(@NotNull Object identity) {
        return super.clearWithReturn(DEFAULT_KEY);
    }

    @Override
    public void clean(Object identity) {
        super.clean(DEFAULT_KEY);
    }

    @Override
    public int size(Object identity) {
        return super.size(DEFAULT_KEY);
    }

    @Override
    public boolean isEmpty(Object identity) {
        return super.isEmpty(DEFAULT_KEY);
    }

    @Override
    public String toString(Object identity) {
        return super.toString(DEFAULT_KEY);
    }

    @Override
    public String toString() {
        return toString(DEFAULT_KEY);
    }
}
