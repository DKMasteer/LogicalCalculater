package com.dkmasteer.logicalcalculator.model.math.operation.operations;

import com.dkmasteer.logicalcalculator.model.math.operation.Operation;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 15.08.2025 16:15
 */

public class Divide extends Operation {
    public Divide(double firstValue, double secondValue) {
        super(firstValue, secondValue);
    }

    public Divide() {
        super(BASE_VALUE, BASE_VALUE);
    }

    @Override
    public double calc() {
        if (secondValue == 0)
            return Double.MAX_VALUE;

        return firstValue / secondValue;
    }
    @Override
    public String toString() {
        return "  /";
    }
}
