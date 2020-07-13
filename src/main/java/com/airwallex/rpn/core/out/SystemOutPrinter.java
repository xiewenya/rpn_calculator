package com.airwallex.rpn.core.out;

import org.jetbrains.annotations.NotNull;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public class SystemOutPrinter<T> extends ToStringBasePrinter<T> {
    @Override
    protected void doPrint(@NotNull final String context) {
        System.out.println(context);
    }
}
