package com.airwallex.rpn.core.buffer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public interface Stack<T, K>{

    void push(final T identity, final K value);

    @Nullable K pop(final T identity);

    /**
     * clear the data inside the stack, more efficient if the stack is still needed for following calculations
     * @param identity
     */
    void clear(@NotNull final T identity);

    /**
     * clear the data inside the stack, more efficient if the stack is still needed for following calculations
     * @param identity
     */
    @NotNull List<K> clearWithReturn(@NotNull final T identity);

    /**
     * remove the stack entirely, more efficient for deletion
     * @param identity
     */
    void clean(final T identity);

    int size(final T identity);

    boolean isEmpty(final T identity);

    String toString(final T identity);
}
