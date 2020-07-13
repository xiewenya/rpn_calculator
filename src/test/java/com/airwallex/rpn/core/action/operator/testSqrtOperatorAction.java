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
public class testSqrtOperatorAction extends BaseTest {
    UndoCache undoCache = new SimpleUndoCache();
    OperatorAction operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("sqrt").get();

    BigDecimalNumberAction numberAction1 = BigDecimalNumberAction.Builder.of("4").get();
    BigDecimalNumberAction numberAction2 = BigDecimalNumberAction.Builder.of("2").get();

    @Test
    public void test_numberRequired() {
        assertEquals(1, operatorAction.numberRequired());
    }

    @Test
    public void test_doRun_single_parameter() {
        NumberAction numberAction = operatorAction.doRun("default", numberAction1);
        assertEquals("2", numberAction.toString());
    }

    @Test(expected = MethodNotAllowedException.class)
    public void test_doRun_multi_parameters() {
        operatorAction.doRun("default", numberAction1, numberAction2);
    }
}