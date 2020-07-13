package com.airwallex.rpn.client;

import com.airwallex.rpn.core.BaseTest;
import com.airwallex.rpn.core.buffer.SimpleLinkedListStack;
import com.airwallex.rpn.core.exception.ActionNotSupportException;
import com.airwallex.rpn.core.in.CommandLineReader;
import com.airwallex.rpn.core.in.channel.InputChannel;
import com.airwallex.rpn.core.out.SystemOutPrinter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/13
 * @content:
 */
public class testSimpleCommandLineCalculator extends BaseTest {
    SimpleCommandLineCalculator calculator;

    InputChannel inputChannel;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        SimpleLinkedListStack stack = new SimpleLinkedListStack();

        inputChannel = mock(InputChannel.class);
        CommandLineReader reader = new CommandLineReader(inputChannel, new UndoableStringInputProcessor(new SimpleUndoCache()));
        calculator = new SimpleCommandLineCalculator(
                stack, reader, new SystemOutPrinter<>());
    }

    @Test
    public void test_calculate() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("1 2 3");
        calculator.calculate();
        assertEquals("1 2 3", calculator.toString());
    }

    @Test
    public void test_calculation_example1() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("5 2");
        calculator.calculate();
        assertEquals("5 2", calculator.toString());
    }

    @Test
    public void test_calculation_example2() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("2 sqrt");
        calculator.calculate();
        assertEquals("1.4142135624", calculator.toString());

        when(inputChannel.readLine()).thenReturn("clear 9 sqrt");
        calculator.calculate();
        assertEquals("3", calculator.toString());
    }

    @Test
    public void test_calculation_example3() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("5 2 -");
        calculator.calculate();
        assertEquals("3", calculator.toString());

        when(inputChannel.readLine()).thenReturn("3 -");
        calculator.calculate();
        assertEquals("0", calculator.toString());

        when(inputChannel.readLine()).thenReturn("clear");
        calculator.calculate();
        assertEquals("", calculator.toString());
    }

    @Test
    public void test_calculation_example4() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("5 4 3 2");
        calculator.calculate();
        assertEquals("5 4 3 2", calculator.toString());

        when(inputChannel.readLine()).thenReturn("undo undo *");
        calculator.calculate();
        assertEquals("20", calculator.toString());

        when(inputChannel.readLine()).thenReturn("5 *");
        calculator.calculate();
        assertEquals("100", calculator.toString());

        when(inputChannel.readLine()).thenReturn("undo");
        calculator.calculate();
        assertEquals("20 5", calculator.toString());
    }

    @Test
    public void test_calculation_example5() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("7 12 2 /");
        calculator.calculate();
        assertEquals("7 6", calculator.toString());

        when(inputChannel.readLine()).thenReturn("*");
        calculator.calculate();
        assertEquals("42", calculator.toString());

        when(inputChannel.readLine()).thenReturn("4 /");
        calculator.calculate();
        assertEquals("10.5", calculator.toString());
    }

    @Test
    public void test_calculation_example6() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("1 2 3 4 5");
        calculator.calculate();
        assertEquals("1 2 3 4 5", calculator.toString());

        when(inputChannel.readLine()).thenReturn("*");
        calculator.calculate();
        assertEquals("1 2 3 20", calculator.toString());

        when(inputChannel.readLine()).thenReturn("clear 3 4 -");
        calculator.calculate();
        assertEquals("-1", calculator.toString());
    }

    @Test
    public void test_calculation_example7() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("1 2 3 4 5");
        calculator.calculate();
        assertEquals("1 2 3 4 5", calculator.toString());

        when(inputChannel.readLine()).thenReturn("* * * *");
        calculator.calculate();
        assertEquals("120", calculator.toString());
    }

    @Test
    public void test_calculation_example8() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("1 2 3 * 5 + * * 6 5");
        try{
            calculator.calculate();
        } catch (WithPositionRunTimeExceptionWrapper e){
            assertEquals(e.getMsg(), "operator * (position: 15):insufficient parameters");
        }
    }

    @Test
    public void test_calculation_empty_input() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("");
        calculator.calculate();
        assertEquals("", calculator.toString());
    }

    @Test
    public void test_calculation_error_with_blank_at_head_of_string() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("     1 2 3 * 5 + * * 6 5");
        try{
            calculator.calculate();
        } catch (WithPositionRunTimeExceptionWrapper e){
            assertEquals(e.getMsg(), "operator * (position: 20):insufficient parameters" + System.getProperties().getProperty("line.separator"));
            assertEquals(calculator.toString(), "11");
        }
    }

    @Test
    public void test_calculation_with_blank_at_head_of_string() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("     1 2 *     ");
        calculator.calculate();
        assertEquals("2", calculator.toString());
    }

    @Test
    public void test_calculation_arthritic_errors() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("1 0 /");
        try{
            calculator.calculate();
        } catch (WithPositionRunTimeExceptionWrapper e){
            assertEquals(e.getMsg(), "operator / (position: 5):BigInteger divide by zero");
        }
    }

    @Test
    public void test_calculation_with_invalid_input() throws ActionNotSupportException {
        when(inputChannel.readLine()).thenReturn("ww");
        try{
            calculator.calculate();
        } catch (WithPositionRunTimeExceptionWrapper e){
            assertEquals(e.getMsg(), "operator ww (position: 1):number format incorrect");
        }
    }

    @Test
    public void test_genIdentity() {
        assertEquals("default", calculator.genIdentity());
    }
}