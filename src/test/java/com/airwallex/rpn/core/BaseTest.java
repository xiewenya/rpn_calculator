package com.airwallex.rpn.core;

import com.airwallex.rpn.core.action.command.CommandAction;
import com.airwallex.rpn.core.action.operator.UndoableOperatorAction;
import org.junit.Before;

import java.lang.reflect.Field;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class BaseTest {
    @Before
    public void setUp() throws Exception {
        //clear the singleton
        clearFactory();
        return;
    }

    private void clearFactory() throws NoSuchFieldException, IllegalAccessException {
        Field factory = CommandAction.Factory.class.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(null, null);

        factory = UndoableOperatorAction.Factory.class.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(null, null);
    }
}
