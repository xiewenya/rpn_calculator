package com.airwallex.rpn.client;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.action.ActionType;
import com.airwallex.rpn.core.action.number.NumberAction;
import com.airwallex.rpn.core.buffer.Stack;
import com.airwallex.rpn.core.buffer.UndoCache;
import com.airwallex.rpn.core.exception.ActionNotSupportException;
import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import com.airwallex.rpn.core.in.processor.InputProcessor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public class UndoableStringInputProcessor implements InputProcessor<String> {
    private static final String DEFAULT_CONFIRM_STRING = " ";
    private static final char DEFAULT_CONFIRM_CHAR = ' ';

    private final UndoCache undoCache;

    public UndoableStringInputProcessor(UndoCache undoCache) {
        this.undoCache = undoCache;
    }

    @Override
    public @NotNull List<Action> process(@NotNull final String commandLine) throws ActionNotSupportException {
        List<Action> actions = new LinkedList<>();

        if (StringUtils.isEmpty(commandLine)){
            return actions;
        }

        String[] commands = commandLine.trim().split(DEFAULT_CONFIRM_STRING);

        int position = countStartSpace(commandLine);

        for (int i = 0; i < commands.length; i++){
            position = nextPosition(commands, i, position);

            String command = commands[i];
            //since the position calculated is started from 0, so we need to add another one;
            try{
                actions.add(new WithPositionActionWrapper(position + 1, UndoableActionFacade.build(command, undoCache)));
            } catch (ActionNotSupportException e){
                throw new WithPositionRunTimeExceptionWrapper(e, new WithPositionActionWrapper(position + 1, new Action() {
                    @Override
                    public <T> void run(T identity, Stack<T, NumberAction> stack) {
                    }

                    @Override
                    public ActionType ofType() {
                        return null;
                    }

                    @Override
                    public String ofAction() {
                        return command;
                    }
                }));
            }

        }

        return actions;
    }
    
    private int countStartSpace(String str){
        int ret = 0;
        for (char ch : str.toCharArray()) {
            if (ch == DEFAULT_CONFIRM_CHAR){
                ret++;
            } else{
                return ret;
            }
        }

        return ret;
    }

    private int nextPosition(String[] commands, int idx, int position){
        //simplified from: idx - 1 > commands.length - 1
        if (idx > commands.length){
            throw new RpnRuntimeException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        if (idx == 0){
            return position;
        }

        String command = commands[idx - 1];

        return position + command.length() + 1;
    }
}
