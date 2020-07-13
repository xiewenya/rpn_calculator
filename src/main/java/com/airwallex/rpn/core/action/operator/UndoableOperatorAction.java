package com.airwallex.rpn.core.action.operator;

import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.UndoCache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/12
 * @content:
 */
public class UndoableOperatorAction extends OperatorAction {

    private final UndoCache undoCache;

    private final OperatorAction operatorAction;

    private UndoableOperatorAction(UndoCache undoCache, OperatorAction operatorAction) {
        this.undoCache = undoCache;
        this.operatorAction = operatorAction;
    }

    @Override
    public int numberRequired() {
        return operatorAction.numberRequired();
    }

    @NotNull private <K extends NumberAction> UndoCache.CacheLog<K> createCacheLog() {
        UndoCache.CacheLog<K> cacheLog = new UndoCache.CacheLog<>();
        cacheLog.setAction(operatorAction.ofAction());
        cacheLog.setOperatorType(operatorAction.ofType());
        return cacheLog;
    }

    @Override
    public  <T> @NotNull NumberAction doRun(@NotNull T identity, @NotNull final NumberAction num) {
        UndoCache.CacheLog<NumberAction> cacheLog = createCacheLog();
        cacheLog.setPoppedValue(Collections.singletonList(num));

        NumberAction ret = operatorAction.doRun(identity, num);

        cacheLog.setPushedValue(Collections.singletonList(ret));
        undoCache.push(identity, cacheLog);
        return ret;
    }

    @Override
    public  <T> @NotNull NumberAction doRun(@NotNull T identity, @NotNull final NumberAction num1, @NotNull final NumberAction num2) {
        UndoCache.CacheLog<NumberAction> cacheLog = createCacheLog();
        cacheLog.setPoppedValue(Arrays.asList(num1, num2));

        NumberAction ret = operatorAction.doRun(identity, num1, num2);

        cacheLog.setPushedValue(Collections.singletonList(ret));
        undoCache.push(identity, cacheLog);
        return ret;
    }

    @Override
    public String ofAction() {
        return operatorAction.ofAction();
    }


    public static class Factory extends OperatorAction.Factory{
        private volatile static Factory factory;

        private UndoCache undoCache;

        private Factory() {
        }

        public static Factory getFactory(UndoCache undoCache){
            if (factory == null){
                synchronized(Factory.class) {
                    if (factory == null){
                        factory = new Factory();
                        factory.undoCache = undoCache;
                        factory.init();
                    }
                }
            }

            return factory;
        }

        @Override
        public Set<Class<? extends OperatorAction>> findAllSubClass() {
            //get all subclass of OperatorAction;
            Reflections reflections = new Reflections("com.airwallex.rpn.core.action.operator");
            Set<Class<? extends OperatorAction>> subTypes = reflections.getSubTypesOf(OperatorAction.class);

            subTypes.remove(UndoableOperatorAction.class);

            return subTypes;
        }

        @Override
        public OperatorAction getInstance(Class<? extends OperatorAction> clz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
            Constructor<? extends OperatorAction> constructor = clz.getDeclaredConstructor();
            constructor.setAccessible(true);
            OperatorAction operatorAction = constructor.newInstance();
            return new UndoableOperatorAction(undoCache, operatorAction);
        }

        public Optional<OperatorAction> of(@Nullable final String str) {
            if (!typeMap.containsKey(str)){
                return Optional.empty();
            }

            return Optional.of(typeMap.get(str));
        }
    }
}
