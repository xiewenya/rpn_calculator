package com.airwallex.rpn.client;

import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.UndoCache;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testSimpleUndoCache extends BaseTest {

    @Test
    public void test_SimpleStack() {
        UndoCache.CacheLog<NumberAction> cacheLog1 = new UndoCache.CacheLog<>();
        UndoCache.CacheLog<NumberAction> cacheLog2 = new UndoCache.CacheLog<>();
        SimpleUndoCache stack = new SimpleUndoCache();
        stack.push(cacheLog1);
        stack.push(cacheLog2);
        assertEquals(2, stack.size());
        assertEquals(2, stack.size("default"));
        stack.pop();
        stack.clean();
        stack.push(cacheLog1);
        stack.push(cacheLog2);
        stack.clear();
        stack.clear("default");
        assertTrue(stack.isEmpty());
        stack.push(cacheLog1);
        assertEquals("", stack.toString());
    }

    @Test
    public void test_pop_empty_stack() {
        SimpleUndoCache stack = new SimpleUndoCache();

        assertNull(stack.pop());

        stack.clear();

        assertEquals("", stack.toString());
    }
}