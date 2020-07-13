package com.airwallex.rpn.client;

import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.action.operator.UndoableOperatorAction;
import com.airwallex.rpn.core.buffer.UndoCache;
import com.airwallex.rpn.core.exception.RpnException;
import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testWithPositionRunTimeExceptionWrapper extends BaseTest {

    @Test
    public void test_generator_error_message(){
        UndoCache cache = new SimpleUndoCache();
        WithPositionActionWrapper action = new WithPositionActionWrapper(5, UndoableOperatorAction.Factory.getFactory(cache).of("*").get());
        WithPositionRunTimeExceptionWrapper wrapper = new WithPositionRunTimeExceptionWrapper(new RpnRuntimeException(ErrorCode.INSUFFICIENT_PARAMETERS), action);

        assertEquals("operator * (position: 5):insufficient parameters", wrapper.getMsg());

        assertEquals(ErrorCode.INSUFFICIENT_PARAMETERS.getCode(), wrapper.getCode());
        assertEquals("*", wrapper.getAction().ofAction());

        action = new WithPositionActionWrapper(5, UndoableOperatorAction.Factory.getFactory(cache).of("*").get());
        wrapper = new WithPositionRunTimeExceptionWrapper(new RpnException(ErrorCode.INSUFFICIENT_PARAMETERS), action);

        assertEquals("operator * (position: 5):insufficient parameters", wrapper.getMsg());

        action = new WithPositionActionWrapper(5, UndoableOperatorAction.Factory.getFactory(cache).of("*").get());
        wrapper = new WithPositionRunTimeExceptionWrapper(new RuntimeException(ErrorCode.INSUFFICIENT_PARAMETERS.getMsg()), action);

        assertEquals("operator * (position: 5):insufficient parameters", wrapper.getMsg());

        action = new WithPositionActionWrapper(5, UndoableOperatorAction.Factory.getFactory(cache).of("*").get());
        wrapper = new WithPositionRunTimeExceptionWrapper(new Exception(ErrorCode.INSUFFICIENT_PARAMETERS.getMsg()), action);

        assertEquals("operator * (position: 5):insufficient parameters", wrapper.getMsg());

    }
}