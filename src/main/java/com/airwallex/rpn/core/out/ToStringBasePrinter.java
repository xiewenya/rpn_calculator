package com.airwallex.rpn.core.out;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content: this printer uses toString function of the object
 */
public abstract class ToStringBasePrinter<T> implements BasePrinter<T> {
    @Override
    public void toPrint(@Nullable final Object object) {
        String context = convertToString(object);

        doPrint(context);
    }

    protected abstract void doPrint(@NotNull final String context);

    private String convertToString(@Nullable final Object object){
        if (object == null){
            return "";
        }

        if (object instanceof String){
            return (String) object;
        }

        return object.toString();
    }
}
