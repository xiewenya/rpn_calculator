package com.airwallex.rpn.core.action.command;

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
public class testCommandAction extends BaseTest {

    @Test
    public void test_factory_build_command_action(){
        UndoCache undoCache = new SimpleUndoCache();
        Optional<CommandAction> commandAction = CommandAction.Factory.getFactory(undoCache).of("clear");
        assertEquals(ClearCommandAction.class, commandAction.get().getClass());

        commandAction = CommandAction.Factory.getFactory(undoCache).of("undo");
        assertEquals(UndoCommandAction.class, commandAction.get().getClass());

        commandAction = CommandAction.Factory.getFactory(undoCache).of("balabala");
        assertFalse(commandAction.isPresent());
    }

}