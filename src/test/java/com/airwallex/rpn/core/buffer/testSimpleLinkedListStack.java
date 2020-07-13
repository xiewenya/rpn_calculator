package com.airwallex.rpn.core.buffer;

import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.action.number.BigDecimalNumberAction;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testSimpleLinkedListStack extends BaseTest {
    BigDecimalNumberAction numberAction1 = BigDecimalNumberAction.Builder.of("1").get();
    BigDecimalNumberAction numberAction2 = BigDecimalNumberAction.Builder.of("2").get();


    @Test
    public void test_SimpleStack() {
        SimpleLinkedListStack stack = new SimpleLinkedListStack();
        stack.push(numberAction1);
        stack.push(numberAction2);
        stack.pop();
        stack.clean();
        stack.push(numberAction1);
        stack.push(numberAction2);
        stack.clear();
        assertTrue(stack.isEmpty());
        stack.push(numberAction1);
        assertEquals("1", stack.toString());
    }

    @Test
    public void test_pop_empty_stack() {
        SimpleLinkedListStack stack = new SimpleLinkedListStack();

        assertNull(stack.pop());

        stack.clear();

        assertEquals("", stack.toString());
    }
}