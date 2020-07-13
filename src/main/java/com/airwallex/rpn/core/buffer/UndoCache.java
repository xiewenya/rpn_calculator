package com.airwallex.rpn.core.buffer;

import com.airwallex.rpn.core.action.ActionType;
import com.airwallex.rpn.core.action.number.NumberAction;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/11
 * @content:
 */
public class UndoCache extends AbstractStack<Object, UndoCache.CacheLog<NumberAction>> {

    private static final Integer MAX_LENGTH = Integer.MAX_VALUE;

    public UndoCache() {
        super(MAX_LENGTH);
    }

    public UndoCache(int length) {
        super(length);
    }

    @Override
    Deque<CacheLog<NumberAction>> newDeque() {
        return new LinkedList<>();
    }

    @Data
    public static class CacheLog<K> {
        private ActionType operatorType;

        private String action;

        private List<K> poppedValue;

        private List<K> pushedValue;

        public @NotNull List<K> getPoppedValue() {
            return poppedValue == null ? new LinkedList<>() : poppedValue;
        }

        public @NotNull List<K> getPushedValue() {
            return pushedValue == null ? new LinkedList<>() : pushedValue;
        }

        @Override
        public String toString() {
            return StringUtils.isEmpty(action) ? "" : action;
        }
    }

}
