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
public class RpnException extends Exception {

    private final String code;
    private final String msg;

    public RpnException(final ResponseCode response) {
        super(response.getMsg());
        this.code = response.getCode();
        this.msg = response.getMsg();
    }


    public RpnException(final ResponseCode response, final String msg) {
        super(response.getMsg());
        this.code = response.getCode();
        this.msg = msg;
    }
}
