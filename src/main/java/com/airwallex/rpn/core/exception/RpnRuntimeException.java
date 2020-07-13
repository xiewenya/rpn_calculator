package com.airwallex.rpn.core.exception;

import com.airwallex.rpn.core.exception.responseCode.ResponseCode;
import lombok.Getter;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
@Getter
public class RpnRuntimeException extends RuntimeException {

    private final String code;
    private final String msg;

    public RpnRuntimeException(final ResponseCode response) {
        super(response.getMsg());
        this.code = response.getCode();
        this.msg = response.getMsg();
    }


    public RpnRuntimeException(final ResponseCode response, final String msg) {
        super(response.getMsg());
        this.code = response.getCode();
        this.msg = msg;
    }
}
