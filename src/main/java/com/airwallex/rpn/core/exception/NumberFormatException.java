package com.airwallex.rpn.core.exception;

import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import com.airwallex.rpn.core.exception.responseCode.ResponseCode;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public class NumberFormatException extends RpnException{

    public NumberFormatException() {
        super(ErrorCode.NUMBER_FORMAT_EXCEPTION);
    }

    public NumberFormatException(ResponseCode response, String msg) {
        super(response, msg);
    }
}
