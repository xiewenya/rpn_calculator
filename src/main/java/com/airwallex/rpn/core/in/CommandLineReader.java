package com.airwallex.rpn.core.in;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.exception.ActionNotSupportException;
import com.airwallex.rpn.core.in.channel.InputChannel;
import com.airwallex.rpn.core.in.processor.InputProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
@Slf4j
public class CommandLineReader extends CommonReader<InputProcessor<String>> {

    public CommandLineReader(InputChannel inputChannel, InputProcessor<String> inputProcessor) {
        super(inputChannel, inputProcessor);
    }

    public List<Action> process(String commands) throws ActionNotSupportException {
        return inputProcessor.process(commands);
    }

    @Override
    public String read() {
        return inputChannel.readLine();
    }
}
