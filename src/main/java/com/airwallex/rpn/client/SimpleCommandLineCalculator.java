package com.airwallex.rpn.client;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.buffer.RpnStack;
import com.airwallex.rpn.core.buffer.SimpleLinkedListStack;
import com.airwallex.rpn.core.calculation.StringIdentityCalculator;
import com.airwallex.rpn.core.exception.ActionNotSupportException;
import com.airwallex.rpn.core.in.BaseReader;
import com.airwallex.rpn.core.out.SystemOutPrinter;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/12
 * @content:
 */
public class SimpleCommandLineCalculator extends StringIdentityCalculator {
    private final BaseReader reader;

    private final SystemOutPrinter<String> printer;

    SimpleCommandLineCalculator(RpnStack<String> stack, BaseReader reader, SystemOutPrinter<String> printer) {
        super(stack);
        this.reader = reader;
        this.printer = printer;
    }

    public void calculate() throws ActionNotSupportException {
        List<Action> actions = reader.process();

        if(actions.size() == 0){
            return;
        }

        try{
            this.eval(actions);
        }catch (WithPositionRunTimeExceptionWrapper e){
            this.printer.toPrint(e.getMsg());
        }

        this.print();
    }

    @Override
    protected String genIdentity() {
        return SimpleLinkedListStack.DEFAULT_KEY;
    }

    public void print(){
        this.print(printer);
    }

}
