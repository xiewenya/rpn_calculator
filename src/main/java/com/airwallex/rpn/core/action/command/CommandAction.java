package com.airwallex.rpn.core.action.command;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.action.ActionType;
import com.airwallex.rpn.core.buffer.UndoCache;
import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public abstract class CommandAction implements Action {

    protected CommandAction() {
    }

    @Override
    public ActionType ofType() {
        return ActionType.COMMAND;
    }

    public static class Factory {
        protected static Map<String, CommandAction> typeMap = new HashMap<>();

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

        public CommandAction getInstance(Class<? extends CommandAction> clz)
                throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException{
            Constructor<? extends CommandAction> constructor = clz.getDeclaredConstructor(UndoCache.class);
            constructor.setAccessible(true);
            return constructor.newInstance(undoCache);
        }

        private Set<Class<? extends CommandAction>> findAllSubClass() {
            Reflections reflections = new Reflections("com.airwallex.rpn.core.action.command");
            return reflections.getSubTypesOf(CommandAction.class);
        }

        private void init() {
            //get all subclass of CommandAction;
            Set<Class<? extends CommandAction>> subTypes = findAllSubClass();

            typeMap = new HashMap<>(subTypes.size());

            subTypes.forEach(subTypeClz -> {
                try {
                    CommandAction action = getInstance(subTypeClz);
                    typeMap.put(action.ofAction(), action);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new RpnRuntimeException(ErrorCode.INTERNAL_SERVER_ERROR, "commandActionBuilder init failed");
                }
            });
        }

        public Optional<CommandAction> of(@Nullable final String str) {
            if (!typeMap.containsKey(str)){
                return Optional.empty();
            }

            return Optional.of(typeMap.get(str));
        }
    }
}
