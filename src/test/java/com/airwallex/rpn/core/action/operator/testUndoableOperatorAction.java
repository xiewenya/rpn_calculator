package com.airwallex.rpn.core.action.operator;

import com.airwallex.rpn.client.SimpleUndoCache;
import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.buffer.UndoCache;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testUndoableOperatorAction extends BaseTest {

    @Test
    public void test_factory_build_command_action(){
        UndoCache undoCache = new SimpleUndoCache();
        Optional<OperatorAction> operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("*");
        assertEquals("*", operatorAction.get().ofAction());

        operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("+");
        assertEquals("+", operatorAction.get().ofAction());

        operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("-");
        assertEquals("-", operatorAction.get().ofAction());

        operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("/");
        assertEquals("/", operatorAction.get().ofAction());

        operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("sqrt");
        assertEquals("sqrt", operatorAction.get().ofAction());

        operatorAction = UndoableOperatorAction.Factory.getFactory(undoCache).of("balabala");
        assertFalse(operatorAction.isPresent());

    }
}