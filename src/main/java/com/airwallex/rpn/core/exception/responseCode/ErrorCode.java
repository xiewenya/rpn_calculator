package com.airwallex.rpn.core.exception.responseCode;

import com.airwallex.rpn.core.exception.RpnRuntimeException;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public enum ErrorCode implements ResponseCode {
    OK("0000", "success"),
    BAD_REQUEST("4000", "bad request"),
    CURRENT_NOT_SUPPORT("4001", "current not supported"),
    NUMBER_FORMAT_EXCEPTION("4002", "number format incorrect"),
    ACTION_NOT_SUPPORT_EXCEPTION("4003", "action not supported"),
    INSUFFICIENT_PARAMETERS("4004", "insufficient parameters"),
    METHOD_NOT_ALLOWED("4005", "method not supported"),
    IMPLEMENT_REQUIRED("4006", "implementation required"),
    INTERNAL_SERVER_ERROR("5000", "internal error");

    private final String code;

    private final String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    public static ErrorCode of(String value) {
        for (ErrorCode code : ErrorCode.values()) {
            if (code.code.equalsIgnoreCase(value)) {
                return code;
            }
        }

        throw new RpnRuntimeException(ErrorCode.BAD_REQUEST, "error code not found");
    }
}
