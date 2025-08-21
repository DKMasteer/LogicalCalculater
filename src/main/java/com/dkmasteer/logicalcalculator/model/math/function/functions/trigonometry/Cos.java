package com.dkmasteer.logicalcalculator.model.math.function.functions.trigonometry;

import com.dkmasteer.logicalcalculator.model.math.function.TrigonometryFunction;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 15.08.2025 15:53
 */

public class Cos extends TrigonometryFunction {
    public Cos(double inputValue, boolean inRadian) {
        super(inputValue, inRadian);
    }

    public Cos(boolean inRadian) {
        this(BASE_VALUE, inRadian);
    }

    public Cos() {
        this(BASE_VALUE, false);
    }

    public Cos(double inputValue) {
        this(inputValue, false);
    }

    @Override
    public double calc() {
        double value = inRadian ? inputValue : TrigonometryFunction.convertToRadian(inputValue);
        return Math.cos(value);
    }

    @Override
    public String toString() {
        return "cos";
    }
}
