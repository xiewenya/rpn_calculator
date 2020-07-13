package com.airwallex.rpn.core.identity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content: a identity is required for multi user task. for a web calculator, a
 */
public interface IdentityGenerator {
    @NotNull String genIdentity();

    @NotNull String genIdentity(@Nullable String prefix);
}
