package com.dkmasteer.logicalcalculator.model.math;

import java.io.Serializable;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 20.08.2025 16:58
 */

public class Number {
    private double value;

    public Number(double value) {
        this.value = value;
    }

    public Number() {
        this(BASE_VALUE);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value).replace(".", ",");
    }
}
