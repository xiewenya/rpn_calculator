package com.airwallex.rpn.core.action.command;

import com.airwallex.rpn.client.SimpleUndoCache;
import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.action.number.BigDecimalNumberAction;
import com.airwallex.rpn.core.buffer.SimpleLinkedListStack;
import com.airwallex.rpn.core.buffer.UndoCache;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testClearCommandAction extends BaseTest {

    @Test
    public void run() {
        UndoCache undoCache = new SimpleUndoCache();
        CommandAction commandAction = CommandAction.Factory.getFactory(undoCache).of("clear").get();

        SimpleLinkedListStack stack = new SimpleLinkedListStack();
        stack.push(BigDecimalNumberAction.Builder.of("1").get());
        stack.push(BigDecimalNumberAction.Builder.of("2").get());
        stack.push(BigDecimalNumberAction.Builder.of("3").get());
        commandAction.run("1234", stack);

        assertEquals(1, undoCache.size("default"));
        assertEquals(0, stack.size());

    }

    @Test
    public void ofAction() {
        UndoCache undoCache = new UndoCache();
        Optional<CommandAction> commandAction = CommandAction.Factory.getFactory(undoCache).of("clear");
        assertEquals("clear", commandAction.get().ofAction());
    }
}