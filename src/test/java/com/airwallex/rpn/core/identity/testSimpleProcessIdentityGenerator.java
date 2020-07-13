package com.airwallex.rpn.core.identity;

import com.airwallex.rpn.core.BaseTest;
import org.junit.Test;

import java.lang.management.ManagementFactory;

import static org.junit.Assert.*;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testSimpleProcessIdentityGenerator extends BaseTest {

    @Test
    public void test_doGenIdentity() {
        SimpleProcessIdentityGenerator generator = new SimpleProcessIdentityGenerator();

        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];

        assertEquals("pid" + pid, generator.genIdentity("pid"));
    }
}