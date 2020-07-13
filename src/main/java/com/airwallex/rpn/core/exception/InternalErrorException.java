package com.airwallex.rpn.core.exception;

import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import com.airwallex.rpn.core.exception.responseCode.ResponseCode;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public class InternalErrorException extends RpnException{

    public InternalErrorException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    public InternalErrorException(ResponseCode response, String msg) {
        super(response, msg);
    }
}
