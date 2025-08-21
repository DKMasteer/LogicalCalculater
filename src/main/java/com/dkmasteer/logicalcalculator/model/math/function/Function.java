package com.dkmasteer.logicalcalculator.model.math.function;

import com.dkmasteer.logicalcalculator.model.math.Mathematics;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 17.08.2025 17:36
 */

public abstract class Function implements Mathematics {
    protected double inputValue;

    public Function(double inputValue) {
        this.inputValue = inputValue;
    }

    public Function() {
        this(BASE_VALUE);
    }

    public double getInputValue() {
        return inputValue;
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    @Override
    public String toString() {
        return "FUNC";
    }
}
