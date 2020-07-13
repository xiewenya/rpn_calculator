package com.airwallex.rpn.core.exception;

import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import com.airwallex.rpn.core.exception.responseCode.ResponseCode;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public class CurrentNotSupportException extends RpnRuntimeException{

    public CurrentNotSupportException() {
        super(ErrorCode.CURRENT_NOT_SUPPORT);
    }

    public CurrentNotSupportException(ResponseCode response, String msg) {
        super(response, msg);
    }
}
