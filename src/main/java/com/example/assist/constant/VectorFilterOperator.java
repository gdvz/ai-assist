package com.example.assist.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum VectorFilterOperator {

    EQ("eq","=="),
    GT("gt",">"),
    GE("ge",">="),
    LT("lt","<"),
    LE("le","<="),
    NE("ne","!=");

    private final String op;
    private final String value;

    VectorFilterOperator(String op, String value) {
        this.op = op;
        this.value = value;
    }

    public static VectorFilterOperator getByValue(String value){
        return Arrays.stream(VectorFilterOperator.values()).filter(operator -> operator.op.equals(value)).findFirst().orElse(EQ);
    }
}
