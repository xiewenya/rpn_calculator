package com.airwallex.rpn.core.action.operator;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.action.ActionType;
import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.Stack;
import com.airwallex.rpn.core.exception.CurrentNotSupportException;
import com.airwallex.rpn.core.exception.InsufficientParameterException;
import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
public abstract class OperatorAction implements Action {

    protected OperatorAction() {
    }

    abstract public int numberRequired();

    abstract public  @NotNull <T> NumberAction doRun(@NotNull final T identity, @NotNull final NumberAction num);

    abstract public  @NotNull <T> NumberAction doRun(@NotNull final T identity, @NotNull final NumberAction num1, @NotNull final NumberAction num2);

    @Override
    public <T> void run(T identity, Stack<T, NumberAction> stack) {
        NumberAction ret;

        if (stack.size(identity) < numberRequired()){
            throw new InsufficientParameterException();
        }

        if (numberRequired() == 1){
            NumberAction num = stack.pop(identity);

            ret = doRun(identity, num);
            stack.push(identity, ret);
            return;
        }

        if (numberRequired() == 2){
            NumberAction num1 = stack.pop(identity);
            NumberAction num2 = stack.pop(identity);

            ret = doRun(identity, num2, num1);
            stack.push(identity, ret);
            return;
        }

        throw new CurrentNotSupportException();
    }

    @Override
    public ActionType ofType() {
        return ActionType.OPERATOR;
    }

    public abstract static class Factory {
        protected static Map<String, OperatorAction> typeMap = null;

        protected Factory() {
        }

        abstract public Set<Class<? extends OperatorAction>> findAllSubClass();

        abstract public OperatorAction getInstance(Class<? extends OperatorAction> clz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

        protected void init() {
            Set<Class<? extends OperatorAction>> subTypes = findAllSubClass();

            typeMap = new HashMap<>(subTypes.size());

            subTypes.forEach(subTypeClz -> {
                try {
                    OperatorAction action = getInstance(subTypeClz);
                    typeMap.put(action.ofAction(), action);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new RpnRuntimeException(ErrorCode.INTERNAL_SERVER_ERROR, "operatorActionBuilder init failed");
                }
            });
        }

        public Optional<OperatorAction> of(@Nullable final String str) {
            if (!typeMap.containsKey(str)){
                return Optional.empty();
            }

            return Optional.of(typeMap.get(str));
        }
    }
}
