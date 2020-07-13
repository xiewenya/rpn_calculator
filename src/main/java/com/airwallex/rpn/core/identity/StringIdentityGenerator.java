package com.airwallex.rpn.core.identity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public abstract class StringIdentityGenerator implements IdentityGenerator {
    public static final String PREFIX = "";

    @Override
    public @NotNull String genIdentity(){
        return genIdentity(null);
    }

    @Override
    public @NotNull String genIdentity(@Nullable final String prefix){
        String id;

        id = doGenIdentity();

        return prefix == null ? PREFIX + id : prefix + id;
    }

    abstract protected @NotNull String doGenIdentity();

}
