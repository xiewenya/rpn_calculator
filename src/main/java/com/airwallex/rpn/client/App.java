package com.airwallex.rpn.client;

import com.airwallex.rpn.core.buffer.SimpleLinkedListStack;
import com.airwallex.rpn.core.exception.RpnException;
import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.out.SystemOutPrinter;
import org.jetbrains.annotations.NotNull;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public class App {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        boolean running = true;
        //shared stack for all calculator
        SimpleLinkedListStack stack = new SimpleLinkedListStack();

        SimpleCommandLineCalculator calculator = initCalculator(stack);

        SystemOutPrinter<String> printer = new SystemOutPrinter<>();

        while(running){
            try {
                calculator.calculate();
            }catch (RpnRuntimeException e) {
                printer.toPrint(e.getMsg());
                running = false;
            }catch (RpnException e) {
                printer.toPrint(e.getMsg());
                running = false;
            }catch (Exception e){
                printer.toPrint(e.getMessage());
                running = false;
            }
        }
    }

    @NotNull
    private static SimpleCommandLineCalculator initCalculator(SimpleLinkedListStack stack) {
        return new SimpleCommandLineCalculator(
                stack, new SimpleCommandLineReader(), new SystemOutPrinter<>());
    }
}
