package com.airwallex.rpn.core.in.channel;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
@Slf4j
public class CommandLineChannel implements InputChannel {

    private final BufferedReader bufferedReader;

    public CommandLineChannel() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public CommandLineChannel(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public @Nullable Character read() {
        try {
            int value = bufferedReader.read();
            if (value == -1){
                return null;
            }
            return (char) value;
        } catch (IOException e) {
            log.error("failed to read char from bufferedReader");
            return null;
        }
    }

    @Override
    public @Nullable String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            log.error("failed to read line from bufferedReader");
            return null;
        }
    }

}
