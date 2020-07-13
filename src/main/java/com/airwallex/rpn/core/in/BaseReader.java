package com.airwallex.rpn.core.in;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.exception.ActionNotSupportException;

import java.util.List;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public interface BaseReader {
    List<Action> process() throws ActionNotSupportException;
}
