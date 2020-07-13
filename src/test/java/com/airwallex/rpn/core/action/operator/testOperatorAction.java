package com.airwallex.rpn.core.action.operator;

import com.airwallex.rpn.client.SimpleUndoCache;
import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.action.number.BigDecimalNumberAction;
import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.SimpleLinkedListStack;
import com.airwallex.rpn.core.buffer.UndoCache;
import com.airwallex.rpn.core.exception.InsufficientParameterException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testOperatorAction extends BaseTest {
    BigDecimalNumberAction numberAction1 = BigDecimalNumberAction.Builder.of("1").get();
    BigDecimalNumberAction numberAction2 = BigDecimalNumberAction.Builder.of("2").get();

    @Test
    public void test_run_with_two_parameters() {
        UndoCache undoCache = new SimpleUndoCache();
        OperatorAction operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("+").get();

        SimpleLinkedListStack stack = new SimpleLinkedListStack();
        stack.push(numberAction1);
        stack.push(numberAction2);
        operatorAction.run("default", stack);

        NumberAction action = stack.pop();

        assertEquals("3", action.toString());
    }

    @Test
    public void test_run_with_single_parameter() {
        UndoCache undoCache = new SimpleUndoCache();
        OperatorAction operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("sqrt").get();

        SimpleLinkedListStack stack = new SimpleLinkedListStack();
        stack.push(numberAction2);
        stack.push(numberAction1);
        operatorAction.run("default", stack);

        NumberAction action = stack.pop();

        assertEquals("1", action.toString());
    }

    @Test(expected = InsufficientParameterException.class)
    public void test_run_insufficient_parameters() {
        UndoCache undoCache = new SimpleUndoCache();
        OperatorAction operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("+").get();

        SimpleLinkedListStack stack = new SimpleLinkedListStack();
        operatorAction.run("default", stack);
    }
}