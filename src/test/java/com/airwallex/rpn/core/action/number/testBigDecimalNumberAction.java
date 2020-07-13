package com.airwallex.rpn.core.action.number;

import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.action.ActionType;
import com.airwallex.rpn.core.buffer.Stack;
import com.airwallex.rpn.core.exception.CurrentNotSupportException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testBigDecimalNumberAction extends BaseTest {
    BigDecimalNumberAction action0 = BigDecimalNumberAction.Builder.of("0").get();
    BigDecimalNumberAction action1 = BigDecimalNumberAction.Builder.of("1").get();
    BigDecimalNumberAction action2 = BigDecimalNumberAction.Builder.of("2").get();

    @Test
    public void test_build_big_decimal_number_action_with_string_number(){
        Optional<BigDecimalNumberAction> action = BigDecimalNumberAction.Builder.of("1.01");
        assertTrue(action.isPresent());
        assertEquals(action.get().getOriginalNumber(), "1.01");
        assertEquals(action.get().toString(), "1.01");
    }

    @Test
    public void test_build_big_decimal_number_action_with_big_decimal(){
        Optional<BigDecimalNumberAction> action = BigDecimalNumberAction.Builder.of(new BigDecimal("1.01"));
        assertTrue(action.isPresent());
        assertNull(action.get().getOriginalNumber());
        assertEquals("1.01", action.get().toString());
    }

    @Test(expected = CurrentNotSupportException.class)
    public void test_input_not_big_decimal(){
        NumberAction numberAction = new forTestNumberAction(1, "test");
        action1.add(numberAction);
    }

    @Test
    public void test_ofAction() {
        assertEquals("1", action1.ofAction());
    }

    @Test
    public void test_getNumberType() {
        assertEquals("decimal", action1.getNumberType());
    }

    @Test
    public void test_ofType() {
        assertEquals(ActionType.NUMBER, action1.ofType());
    }

    @Test
    public void testToString() {
        NumberAction action = action1.divide(action2);
        assertEquals("0.5", action.toString());

        BigDecimalNumberAction action00 = BigDecimalNumberAction.Builder.of(new BigDecimal("0.123456789123456789")).get();

        assertEquals("0.1234567891", action00.toString());
        assertEquals(new BigDecimal("0.123456789123457"), action00.getNumber());


        action = BigDecimalNumberAction.Builder.of(new BigDecimal("10000000000")).get();
        assertEquals("10000000000", action.toString());
    }


    private class forTestNumberAction extends NumberAction{

        protected forTestNumberAction(Object number, String numberType) {
            super(number, numberType);
        }

        @Override
        public NumberAction add(NumberAction num) {
            return null;
        }

        @Override
        public NumberAction subtract(NumberAction num) {
            return null;
        }

        @Override
        public NumberAction multi(NumberAction num) {
            return null;
        }

        @Override
        public NumberAction divide(NumberAction num) {
            return null;
        }

        @Override
        public NumberAction sqrt() {
            return null;
        }


        @Override
        public <T> void run(T identity, Stack<T, NumberAction> stack) {

        }
    }
}