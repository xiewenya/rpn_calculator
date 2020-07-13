package com.airwallex.rpn.core.action.operator;

import com.airwallex.rpn.client.SimpleUndoCache;
import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.action.number.BigDecimalNumberAction;
import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.UndoCache;
import com.airwallex.rpn.core.exception.MethodNotAllowedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testMultiplicationOperatorAction extends BaseTest {
    UndoCache undoCache = new SimpleUndoCache();
    OperatorAction operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("*").get();

    BigDecimalNumberAction numberAction1 = BigDecimalNumberAction.Builder.of("1").get();
    BigDecimalNumberAction numberAction2 = BigDecimalNumberAction.Builder.of("2").get();

    @Test
    public void test_numberRequired() {
        assertEquals(2, operatorAction.numberRequired());
    }

    @Test(expected = MethodNotAllowedException.class)
    public void test_doRun_single_parameter() {
        operatorAction.doRun("default", numberAction1);
    }

    @Test
    public void test_doRun_multi_parameters() {
        NumberAction numberAction = operatorAction.doRun("default", numberAction1, numberAction2);
        assertEquals("2", numberAction.toString());
    }
}