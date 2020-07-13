package com.airwallex.rpn.client;

import com.airwallex.rpn.core.in.CommandLineReader;
import com.airwallex.rpn.core.in.channel.CommandLineChannel;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/12
 * @content:
 */
public class SimpleCommandLineReader extends CommandLineReader {

    public SimpleCommandLineReader() {
        super(new CommandLineChannel(), new UndoableStringInputProcessor(new SimpleUndoCache()));
    }
}
