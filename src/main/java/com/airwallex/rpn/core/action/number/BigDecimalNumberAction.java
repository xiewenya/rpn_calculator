package com.airwallex.rpn.core.action.number;

import com.airwallex.rpn.core.buffer.Stack;
import com.airwallex.rpn.core.exception.CurrentNotSupportException;
import com.airwallex.rpn.core.exception.NumberFormatException;
import com.airwallex.rpn.core.exception.RpnRuntimeException;
import com.airwallex.rpn.core.exception.responseCode.ErrorCode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/12
 * @content:
 */
public class BigDecimalNumberAction extends NumberAction {

    private final String originalNumber;

    private static final int DEFAULT_PRECISION = 15;

    public BigDecimalNumberAction(final String number, final String numberType) {
        super(new BigDecimal(number).setScale(DEFAULT_PRECISION, RoundingMode.HALF_DOWN), numberType);
        originalNumber = number;
    }

    public BigDecimalNumberAction(final BigDecimal number, final String numberType) {
        super(number.setScale(DEFAULT_PRECISION, RoundingMode.HALF_DOWN), numberType);
        originalNumber = null;
    }

    private <M> void checkClass(final M num) {
        if (! (num instanceof BigDecimalNumberAction) && ! (num instanceof UndoableNumberAction) ){
            throw new CurrentNotSupportException();
        }
    }

    public String getOriginalNumber() {
        return originalNumber;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NumberAction add(final NumberAction num) {
        checkClass(num);
        return add(getBigDecimal(num));
    }

    @Override
    @SuppressWarnings("unchecked")
    public NumberAction subtract(final NumberAction num) {
        checkClass(num);
        return subtract(getBigDecimal(num));
    }

    @Override
    @SuppressWarnings("unchecked")
    public NumberAction multi(final NumberAction num) {
        checkClass(num);
        return multi(getBigDecimal(num));
    }

    @Override
    @SuppressWarnings("unchecked")
    public NumberAction divide(final NumberAction num) {
        checkClass(num);
        return divide(getBigDecimal(num));
    }

    @Override
    @SuppressWarnings("unchecked")
    public NumberAction sqrt() {
        BigDecimal ret = (getBigDecimal()).sqrt(new MathContext(DEFAULT_PRECISION, RoundingMode.HALF_DOWN));
        return getResultFromOptional(ret);
    }

    @Override
    public String ofAction() {
        return this.toString();
    }

    @NotNull private BigDecimalNumberAction getResultFromOptional(BigDecimal ret) {
        Optional<BigDecimalNumberAction> action = Builder.of(ret);
        if (action.isPresent()){
            return action.get();
        }

        throw new RpnRuntimeException(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private BigDecimal getBigDecimal() {
        return (BigDecimal) this.getNumber();
    }

    private BigDecimal getBigDecimal(NumberAction num) {
        return (BigDecimal) num.getNumber();
    }

    private BigDecimalNumberAction add(BigDecimal num) {
        BigDecimal ret = getBigDecimal().add(num);
        return getResultFromOptional(ret);
    }

    private BigDecimalNumberAction subtract(BigDecimal num) {
        BigDecimal ret = getBigDecimal().subtract(num);
        return getResultFromOptional(ret);
    }

    private BigDecimalNumberAction multi(BigDecimal num) {
        BigDecimal ret = getBigDecimal().multiply(num);
        ret = ret.setScale(DEFAULT_PRECISION, RoundingMode.HALF_DOWN);
        return getResultFromOptional(ret);
    }

    private BigDecimalNumberAction divide(BigDecimal num) {
        BigDecimal ret = getBigDecimal().divide(num, DEFAULT_PRECISION, RoundingMode.HALF_DOWN);
        return getResultFromOptional(ret);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void run(T identity, Stack<T, NumberAction> stack) {
        stack.push(identity, this);
    }

    @Override
    public String toString() {
        if (originalNumber != null){
            return originalNumber;
        }

        BigDecimal toDisplay = getBigDecimal().setScale(10, RoundingMode.HALF_DOWN);
        return String.valueOf(toDisplay.stripTrailingZeros().toPlainString());
    }

    public static class Builder{

        private static BigDecimalNumberAction getInstance(String number, String numberType) {
            return new BigDecimalNumberAction(number, numberType);
        }

        private static BigDecimalNumberAction getInstance(BigDecimal number, String numberType) {
            return new BigDecimalNumberAction(number, numberType);
        }

        public static Optional<BigDecimalNumberAction> of(@Nullable String num){
            try {
                NumberType type = NumberType.ofType(num);
                return Optional.of(getInstance(num, type.getValue()));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        public static Optional<BigDecimalNumberAction> of(@Nullable BigDecimal num){
            if (num == null) {
                return Optional.empty();
            }

            try {
                NumberType type = NumberType.ofType(num.stripTrailingZeros().toPlainString());
                return Optional.of(getInstance(num, type.getValue()));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

    }
}
