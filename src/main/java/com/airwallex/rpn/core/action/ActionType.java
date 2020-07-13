package com.airwallex.rpn.core.action;

/**
 * @version 1.0
 * @author:xuewenyao
 * @date:2020/7/11
 * @content:
 */
public enum ActionType {
    NUMBER("number"), OPERATOR("operator"), COMMAND("command");

    private String value;

    ActionType(String value) {
        this.value = value;
    }
}
