package com.airwallex.rpn.core.action.command;

import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.Stack;
import com.airwallex.rpn.core.buffer.UndoCache;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/11
 * @content:
 */
public class UndoCommandAction extends CommandAction {
    private UndoCache undoCache;

    private UndoCommandAction(UndoCache undoCache) {
        this.undoCache = undoCache;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void run(T identity, Stack<T, NumberAction> stack) {

        UndoCache.CacheLog<NumberAction> cacheLog = undoCache.pop(identity);

        if (cacheLog == null){
            return;
        }

        List<NumberAction> pushed = cacheLog.getPushedValue();
        for (int i = 0; i < pushed.size(); i++){
            stack.pop(identity);
        }

        List<NumberAction> popped = cacheLog.getPoppedValue();

        for (int i = 0; i < popped.size(); i++){
            stack.push(identity, popped.get(i));
        }
    }

    @Override
    public String ofAction() {
        return "undo";
    }
}
