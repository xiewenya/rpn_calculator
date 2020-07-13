package com.airwallex.rpn.core.in.channel;

import com.airwallex.rpn.core.BaseTest;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testCommandLineChannel extends BaseTest {

    @Test
    public void test_read() throws IOException {
        InputStream stubInputStream =
                IOUtils.toInputStream("1 2", "UTF-8");
        CommandLineChannel channel = new CommandLineChannel(stubInputStream);
        assertEquals(channel.read().toString(), "1");
    }

    @Test
    public void test_readLine() throws IOException {
        InputStream stubInputStream =
                IOUtils.toInputStream("1 2", "UTF-8");
        CommandLineChannel channel = new CommandLineChannel(stubInputStream);
        assertEquals(channel.readLine(), "1 2");
    }
}