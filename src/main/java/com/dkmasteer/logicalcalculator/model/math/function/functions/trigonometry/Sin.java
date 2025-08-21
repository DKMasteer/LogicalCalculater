package com.dkmasteer.logicalcalculator.model.math.function.functions.trigonometry;

import com.dkmasteer.logicalcalculator.model.math.function.TrigonometryFunction;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 15.08.2025 15:52
 */

public class Sin extends TrigonometryFunction {
    public Sin(double inputValue, boolean inRadian) {
        super(inputValue, inRadian);
    }

    public Sin(boolean inRadian) {
        this(BASE_VALUE, inRadian);
    }

    public Sin() {
        this(BASE_VALUE, false);
    }

    public Sin(double inputValue) {
        this(inputValue, false);
    }

    @Override
    public double calc() {
        double value = inRadian ? inputValue : TrigonometryFunction.convertToRadian(inputValue);
        return Math.sin(value);
    }

    @Override
    public String toString() {
        return "sin";
    }
}