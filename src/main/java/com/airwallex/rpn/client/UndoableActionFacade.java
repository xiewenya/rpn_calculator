package com.airwallex.rpn.client;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.action.ActionType;
import com.airwallex.rpn.core.action.command.CommandAction;
import com.airwallex.rpn.core.action.number.BigDecimalNumberAction;
import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.action.number.UndoableNumberAction;
import com.airwallex.rpn.core.action.operator.UndoableOperatorAction;
import com.airwallex.rpn.core.buffer.Stack;
import com.airwallex.rpn.core.buffer.UndoCache;
import com.airwallex.rpn.core.exception.ActionNotSupportException;
import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/10
 * @content: a facade to wrap all the actions
 */
@Slf4j
public class UndoableActionFacade implements Action {
    private Action action;

    private UndoableActionFacade() {
    }

    @Override
    public <T> void run(T identity, Stack<T, NumberAction> stack) {
        if (this.action == null){
            throw new RpnRuntimeException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        this.action.run(identity, stack);
    }

    @Override
    public ActionType ofType() {
        if (this.action == null){
            throw new RpnRuntimeException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return this.action.ofType();
    }

    @Override
    public String ofAction() {
        if (this.action == null){
            throw new RpnRuntimeException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return this.action.ofAction();
    }

    //it should be a good practice to cache Operator and Command Facade, since they are stateless
    public static UndoableActionFacade build(String str, UndoCache undoCache) throws ActionNotSupportException {
        UndoableActionFacade undoableActionFacade = new UndoableActionFacade();

        of(str, undoCache).map(action -> undoableActionFacade.action = action)
                .orElseThrow(ActionNotSupportException::new);

        return undoableActionFacade;
    }

    //there should be only one optional<Action> which is present;
    public static Optional<? extends Action> of(String str, UndoCache undoCache) {

        Optional<BigDecimalNumberAction> numberAction = BigDecimalNumberAction.Builder.of(str);

        return Stream.of(
                UndoableNumberAction.Builder.of(numberAction.orElse(null), undoCache),
                UndoableOperatorAction.Factory.getFactory(undoCache).of(str),
                CommandAction.Factory.getFactory(undoCache).of(str))
                .filter(Optional::isPresent).map(Optional::get).findFirst();
    }
}
