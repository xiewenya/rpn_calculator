package com.airwallex.rpn.client;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.action.ActionType;
import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.Stack;
import com.airwallex.rpn.core.exception.RpnRuntimeException;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class WithPositionActionWrapper implements Action {
    private final Action action;

    private final int position;

    public WithPositionActionWrapper(int position, Action action) {
        this.position = position;
        this.action = action;
    }

    @Override
    public <T> void run(T identity, Stack<T, NumberAction> stack) {
        try{
            action.run(identity, stack);
        } catch (RpnRuntimeException e) {
            throw new WithPositionRunTimeExceptionWrapper(e, this);
        } catch (RuntimeException e){
            throw new WithPositionRunTimeExceptionWrapper(e, this);
        } catch (Exception e){
            throw new WithPositionRunTimeExceptionWrapper(e, this);
        }
    }

    public int getPosition() {
        return position;
    }

    @Override
    public ActionType ofType() {
        return action.ofType();
    }

    @Override
    public String ofAction() {
        return action.ofAction();
    }
}
