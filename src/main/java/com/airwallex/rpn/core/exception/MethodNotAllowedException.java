package com.airwallex.rpn.core.exception;

import com.airwallex.rpn.core.exception.responseCode.ErrorCode;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public class MethodNotAllowedException extends RpnRuntimeException{

    public MethodNotAllowedException() {
        super(ErrorCode.METHOD_NOT_ALLOWED);
    }
}
