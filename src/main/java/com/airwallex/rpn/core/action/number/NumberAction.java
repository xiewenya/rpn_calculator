package com.airwallex.rpn.core.action.number;

import com.airwallex.rpn.core.action.Action;
import com.airwallex.rpn.core.action.ActionType;
import com.airwallex.rpn.core.exception.CurrentNotSupportException;
import com.airwallex.rpn.core.exception.NumberFormatException;
import lombok.Getter;
import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/9
 * @content:
 */
public abstract class NumberAction implements Action {
    private final Object number;

    private final String numberType;

    protected NumberAction(Object number, String numberType) {
        this.number = number;
        this.numberType = numberType;
    }

    public Object getNumber(){
        return number;
    }

    public String getNumberType() {
        return numberType;
    }

    @Override
    public ActionType ofType() {
        return ActionType.NUMBER;
    }

    @Override
    public String ofAction() {
        return String.valueOf(number);
    }

    public abstract NumberAction add(NumberAction num);

    public abstract NumberAction subtract(NumberAction num);

    public abstract NumberAction multi(NumberAction num);

    public abstract NumberAction divide(NumberAction num);

    public abstract NumberAction sqrt();

    @Getter
    public enum NumberType{
        DECIMAL("decimal"),
        HEXADECIMAL("hexadecimal"),
        OCTAL("octal"),
        SCIENTIFIC_NOTATION("scientificNotation"),
        NUMBER_WITH_TYPE("numberWithType");

        private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        private final String value;

        NumberType(String value) {
            this.value = value;
        }

        private static boolean isNumeric(String strNum) {
            if (strNum == null) {
                return false;
            }
            return pattern.matcher(strNum).matches();
        }

        static @NotNull NumberType ofType(@Nullable String str) throws NumberFormatException {
            if (!NumberUtils.isCreatable(str)){
                throw new NumberFormatException();
            }

            if (isNumeric(str)){
                return NumberType.DECIMAL;
            }

            throw new CurrentNotSupportException();
        }
    }
}
