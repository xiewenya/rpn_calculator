package com.airwallex.rpn.core.identity;

import com.airwallex.rpn.core.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testSimpleThreadIdentityGenerator extends BaseTest {

    @Test
    public void doGenIdentity() {
        SimpleThreadIdentityGenerator generator = new SimpleThreadIdentityGenerator();
        assertEquals(Thread.currentThread().getName(), generator.doGenIdentity());
    }
}