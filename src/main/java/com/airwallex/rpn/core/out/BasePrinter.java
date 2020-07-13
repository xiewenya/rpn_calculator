package com.airwallex.rpn.core.out;

import org.jetbrains.annotations.Nullable;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public interface BasePrinter<T> {
    /**
     * print the object;
     * @param object
     */
    void toPrint(@Nullable final T object);
}
