package com.airwallex.rpn.core.in;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.exception.ActionNotSupportException;
import com.airwallex.rpn.core.in.channel.InputChannel;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public abstract class CommonReader<T> implements BaseReader {
    //how to get the date
    protected InputChannel inputChannel;

    // how to process the data
    protected T inputProcessor;

    public CommonReader(InputChannel inputChannel, T inputProcessor) {
        this.inputChannel = inputChannel;
        this.inputProcessor = inputProcessor;
    }

    abstract List<Action> process(String commands) throws ActionNotSupportException;

    abstract String read();

    @Override
    public List<Action> process() throws ActionNotSupportException {
        String commandLine = read();
        return process(commandLine);
    }
}
