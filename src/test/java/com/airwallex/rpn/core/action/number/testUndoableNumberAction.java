package com.airwallex.rpn.core.action.number;

import com.airwallex.rpn.client.SimpleUndoCache;
import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.buffer.SimpleLinkedListStack;
import com.airwallex.rpn.core.buffer.UndoCache;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testUndoableNumberAction extends BaseTest {
    UndoCache undoCache = new SimpleUndoCache();

    UndoableNumberAction action1 = (UndoableNumberAction) UndoableNumberAction.Builder.of(BigDecimalNumberAction.Builder.of("1").get(), undoCache).get();
    UndoableNumberAction action2 = (UndoableNumberAction) UndoableNumberAction.Builder.of(BigDecimalNumberAction.Builder.of("2").get(), undoCache).get();
    UndoableNumberAction action3 = (UndoableNumberAction) UndoableNumberAction.Builder.of(BigDecimalNumberAction.Builder.of("3").get(), undoCache).get();
    UndoableNumberAction action4 = (UndoableNumberAction) UndoableNumberAction.Builder.of(BigDecimalNumberAction.Builder.of("4").get(), undoCache).get();
    UndoableNumberAction action5 = (UndoableNumberAction) UndoableNumberAction.Builder.of(BigDecimalNumberAction.Builder.of("-4").get(), undoCache).get();

    @Test
    public void test_add() {
        NumberAction action = action1.add(action2);
        assertEquals("3", action.toString());
    }

    @Test
    public void test_subtract() {
        NumberAction action = action1.subtract(action2);
        assertEquals("-1", action.toString());

        action = action2.subtract(action1);
        assertEquals("1", action.toString());
    }

    @Test
    public void test_multi() {
        NumberAction action = action1.multi(action2);
        assertEquals("2", action.toString());
    }

    @Test
    public void test_divide() {
        NumberAction action = action1.divide(action2);
        assertEquals(new BigDecimal("0.500000000000000"), action.getNumber());
    }

    @Test
    public void test_sqrt() {
        NumberAction action = action4.sqrt();
        assertEquals("2", action.toString());
    }

    @Test(expected = ArithmeticException.class)
    public void test_sqrt_negative(){
        action5.sqrt();
    }

    @Test
    public void run() {
        UndoCache undoCache = new SimpleUndoCache();
        UndoableNumberAction action = (UndoableNumberAction) UndoableNumberAction.Builder.of(BigDecimalNumberAction.Builder.of("0").get(), undoCache).get();

        action.run("1", new SimpleLinkedListStack());
        assertEquals(1, action.getUndoCache().size("1"));
    }
}