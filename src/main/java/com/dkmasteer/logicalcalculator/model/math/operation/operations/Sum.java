package com.dkmasteer.logicalcalculator.model.math.operation.operations;

import com.dkmasteer.logicalcalculator.model.math.operation.Operation;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 15.08.2025 16:11
 */

public class Sum extends Operation {
    public Sum(double firstValue, double secondValue) {
        super(firstValue, secondValue);
    }

    public Sum() {
        super(BASE_VALUE, BASE_VALUE);
    }

    @Override
    public double calc() {
        return firstValue + secondValue;
    }

    @Override
    public String toString() {
        return "  +";
    }
}