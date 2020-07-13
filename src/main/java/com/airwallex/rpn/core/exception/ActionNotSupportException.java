package com.airwallex.rpn.core.exception;

import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import com.airwallex.rpn.core.exception.responseCode.ResponseCode;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public class ActionNotSupportException extends RpnException{

    public ActionNotSupportException() {
        super(ErrorCode.NUMBER_FORMAT_EXCEPTION);
    }

    public ActionNotSupportException(ResponseCode response, String msg) {
        super(response, msg);
    }
}
