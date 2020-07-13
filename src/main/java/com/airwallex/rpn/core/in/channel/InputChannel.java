package com.airwallex.rpn.core.in.channel;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content: how to get the data, e.g from input stream or from nio
 */
public interface InputChannel {

    Character read();

    String readLine();
}
