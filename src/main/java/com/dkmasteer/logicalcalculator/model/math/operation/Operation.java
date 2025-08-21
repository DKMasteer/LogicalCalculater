package com.dkmasteer.logicalcalculator.model.math.operation;

import com.dkmasteer.logicalcalculator.model.math.Mathematics;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 15.08.2025 15:49
 */

public abstract class Operation implements Mathematics {
    protected double firstValue;
    protected double secondValue;

    public Operation(double firstValue, double secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public Operation() {
        this(BASE_VALUE, BASE_VALUE);
    }

    public double getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(double firstValue) {
        this.firstValue = firstValue;
    }

    public double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(double secondValue) {
        this.secondValue = secondValue;
    }

    @Override
    public String toString() {
        return "OPR";
    }
}
