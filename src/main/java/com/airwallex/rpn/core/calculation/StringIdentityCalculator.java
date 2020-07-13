package com.airwallex.rpn.core.calculation;

import com.airwallex.rpn.core.buffer.RpnStack;
import com.airwallex.rpn.core.identity.SimpleProcessIdentityGenerator;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/12
 * @content:
 */
public class StringIdentityCalculator extends AbstractCalculator<String> {
    public StringIdentityCalculator(RpnStack<String> stack) {
        super(stack);
    }

    @Override
    protected String genIdentity() {
        return new SimpleProcessIdentityGenerator().genIdentity();
    }

}
