package com.airwallex.rpn.core.action.command;

import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.Stack;
import com.airwallex.rpn.core.buffer.UndoCache;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/11
 * @content:
 */
public class ClearCommandAction extends CommandAction {
    private UndoCache undoCache;

    private ClearCommandAction(UndoCache undoCache) {
        this.undoCache = undoCache;
    }

    public UndoCache getUndoCache() {
        return undoCache;
    }

    @NotNull private UndoCache.CacheLog<NumberAction> createCacheLog() {
        UndoCache.CacheLog<NumberAction> cacheLog = new UndoCache.CacheLog<>();
        cacheLog.setAction(this.ofAction());
        cacheLog.setOperatorType(this.ofType());
        return cacheLog;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void run(@NotNull final T identity, @NotNull final Stack<T, NumberAction> stack) {
        UndoCache.CacheLog<NumberAction> cacheLog = createCacheLog();

        List<NumberAction> numberActionList = stack.clearWithReturn(identity);

        cacheLog.setPoppedValue(numberActionList);
        undoCache.push(identity, cacheLog);
    }

    @Override
    public String ofAction() {
        return "clear";
    }


}
