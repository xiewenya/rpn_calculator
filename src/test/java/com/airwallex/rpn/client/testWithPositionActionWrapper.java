package com.airwallex.rpn.client;

import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.action.ActionType;
import com.airwallex.rpn.core.action.number.BigDecimalNumberAction;
import com.airwallex.rpn.core.buffer.SimpleLinkedListStack;
import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testWithPositionActionWrapper extends BaseTest {

    WithPositionActionWrapper wrapper;

    Action action;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        action = mock(Action.class);
        wrapper = new WithPositionActionWrapper(1, action);
    }

    @Test(expected = WithPositionRunTimeExceptionWrapper.class)
    public void test_run_throw_rpn_runtime_exceptions(){
        doThrow(new RpnRuntimeException(ErrorCode.BAD_REQUEST)).when(action).run(any(), any());
        wrapper.run("1", new SimpleLinkedListStack());
    }

    @Test(expected = WithPositionRunTimeExceptionWrapper.class)
    public void test_run_throw_runtime_exceptions(){
        doThrow(new RuntimeException()).when(action).run(any(), any());
        wrapper.run("1", new SimpleLinkedListStack());
    }

    @Test
    public void test_get_position(){
        assertEquals(1, wrapper.getPosition());
    }

    @Test
    public void test_get_Type(){
        Action action = BigDecimalNumberAction.Builder.of("1").get();
        WithPositionActionWrapper actionWrapper = new WithPositionActionWrapper(1, action);
        assertEquals(ActionType.NUMBER, actionWrapper.ofType());
    }

    @Test
    public void test_get_action(){
        Action action = BigDecimalNumberAction.Builder.of("1").get();
        WithPositionActionWrapper actionWrapper = new WithPositionActionWrapper(1, action);
        assertEquals("1", actionWrapper.ofAction());
    }

}