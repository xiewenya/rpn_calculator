package com.airwallex.rpn.core.action.command;

import com.airwallex.rpn.client.SimpleUndoCache;
import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.action.ActionType;
import com.airwallex.rpn.core.action.number.BigDecimalNumberAction;
import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.SimpleLinkedListStack;
import com.airwallex.rpn.core.buffer.UndoCache;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testUndoCommandAction extends BaseTest {


    @Test
    public void run() {
        UndoCache undoCache = new SimpleUndoCache();
        UndoCache.CacheLog<NumberAction> cacheLog = new UndoCache.CacheLog<>();
        cacheLog.setAction(ActionType.OPERATOR.name());
        cacheLog.setPushedValue(Collections.singletonList(BigDecimalNumberAction.Builder.of("1").get()));

        cacheLog.setPoppedValue(Arrays.asList(BigDecimalNumberAction.Builder.of("2.1").get(),
                BigDecimalNumberAction.Builder.of("3").get()));

        undoCache.push("12314", cacheLog);

        CommandAction commandAction = CommandAction.Factory.getFactory(undoCache).of("undo").get();

        SimpleLinkedListStack stack = new SimpleLinkedListStack();
        stack.push(BigDecimalNumberAction.Builder.of("1").get());

        commandAction.run("12314", stack);

        assertEquals(0, undoCache.size("default"));
        assertEquals(2, stack.size());
    }

    @Test
    public void ofAction() {
        UndoCache undoCache = new UndoCache();
        Optional<CommandAction> commandAction = CommandAction.Factory.getFactory(undoCache).of("clear");
        assertEquals("clear", commandAction.get().ofAction());
    }
}