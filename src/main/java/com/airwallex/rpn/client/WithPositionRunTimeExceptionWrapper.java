package com.airwallex.rpn.client;

import com.airwallex.rpn.core.exception.RpnException;
import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import lombok.Getter;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
@Getter
public class WithPositionRunTimeExceptionWrapper extends RpnRuntimeException {

    private final WithPositionActionWrapper action;

    public WithPositionRunTimeExceptionWrapper(RpnRuntimeException exception, WithPositionActionWrapper action) {
        super(ErrorCode.of(exception.getCode()));
        this.action = action;
    }

    public WithPositionRunTimeExceptionWrapper(RpnException exception, WithPositionActionWrapper action) {
        super(ErrorCode.of(exception.getCode()));
        this.action = action;
    }

    public WithPositionRunTimeExceptionWrapper(RuntimeException exception, WithPositionActionWrapper action) {
        super(ErrorCode.BAD_REQUEST, exception.getMessage());
        this.action = action;
    }

    public WithPositionRunTimeExceptionWrapper(Exception exception, WithPositionActionWrapper action) {
        super(ErrorCode.BAD_REQUEST, exception.getMessage());
        this.action = action;
    }

    @Override
    public String getCode() {
        return super.getCode();
    }

    @Override
    public String getMsg() {
        return "operator " + action.ofAction() + " (position: " + action.getPosition() + "):" + super.getMsg();
    }
}
