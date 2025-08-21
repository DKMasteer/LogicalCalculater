package com.dkmasteer.logicalcalculator.model.math.function.functions.arithmetic;

import com.dkmasteer.logicalcalculator.model.math.function.Function;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 15.08.2025 15:53
 */

public class Ln extends Function {
    public Ln(double inputValue) {
        this.inputValue = inputValue;
    }

    public Ln() {
        this(BASE_VALUE);
    }

    @Override
    public double calc() {
        return Math.log(inputValue);
    }

    @Override
    public String toString() {
        return " LN";
    }
}
