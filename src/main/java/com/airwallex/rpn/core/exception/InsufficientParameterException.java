package com.airwallex.rpn.core.exception;

import com.airwallex.rpn.core.exception.responseCode.ErrorCode;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public class InsufficientParameterException extends RpnRuntimeException{

    public InsufficientParameterException() {
        super(ErrorCode.INSUFFICIENT_PARAMETERS);
    }
}
