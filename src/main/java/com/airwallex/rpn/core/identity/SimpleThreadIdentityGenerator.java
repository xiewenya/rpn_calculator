package com.airwallex.rpn.core.identity;

import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
@Slf4j
public class SimpleThreadIdentityGenerator extends StringIdentityGenerator {

    @Override
    protected @NotNull String doGenIdentity(){
        String name = Thread.currentThread().getName();

        if (name == null){
            log.error("identity generator error");
            throw new RpnRuntimeException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return name;
    }
}
