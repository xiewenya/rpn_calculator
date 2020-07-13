package com.airwallex.rpn.core.action.number;

import com.airwallex.rpn.core.buffer.Stack;
import com.airwallex.rpn.core.buffer.UndoCache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Optional;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/12
 * @content:
 */
public class UndoableNumberAction extends NumberAction {
    private final UndoCache undoCache;

    private final NumberAction numberAction;

    public UndoableNumberAction(UndoCache undoCache, NumberAction numberAction) {
        super(numberAction.getNumber(), numberAction.getNumberType());
        this.undoCache = undoCache;
        this.numberAction = numberAction;
    }

    public UndoCache getUndoCache() {
        return undoCache;
    }

    @Override
    public NumberAction add(NumberAction num) {
        return numberAction.add(num);
    }

    @Override
    public NumberAction subtract(NumberAction num) {
        return numberAction.subtract(num);
    }

    @Override
    public NumberAction multi(NumberAction num) {
        return numberAction.multi(num);
    }

    @Override
    public NumberAction divide(NumberAction num) {
        return numberAction.divide(num);
    }

    @Override
    public NumberAction sqrt() {
        return numberAction.sqrt();
    }

    @NotNull private <K extends NumberAction> UndoCache.CacheLog<K> createCacheLog() {
        UndoCache.CacheLog<K> cacheLog = new UndoCache.CacheLog<>();
        cacheLog.setAction(numberAction.ofAction());
        cacheLog.setOperatorType(numberAction.ofType());
        return cacheLog;
    }

    @Override
    public <T> void run(T identity, Stack<T, NumberAction> stack) {
        UndoCache.CacheLog<NumberAction> cacheLog = createCacheLog();

        numberAction.run(identity, stack);

        cacheLog.setPushedValue(Collections.singletonList(numberAction));
        undoCache.push(identity, cacheLog);
    }

    public static class Builder{
        public static Optional<NumberAction> of(@Nullable final NumberAction action, @NotNull final UndoCache undoCache){
            if (action == null){
                return Optional.empty();
            }

            return Optional.of(new UndoableNumberAction(undoCache, action));
        }
    }
}
