package com.dkmasteer.logicalcalculator.model.math.function.functions.trigonometry;

import com.dkmasteer.logicalcalculator.model.math.function.TrigonometryFunction;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 15.08.2025 15:53
 */

public class Ctg extends TrigonometryFunction {
    public Ctg(double inputValue, boolean inRadian) {
        super(inputValue, inRadian);
    }

    public Ctg(boolean inRadian) {
        this(BASE_VALUE, inRadian);
    }

    public Ctg() {
        this(BASE_VALUE, false);
    }

    public Ctg(double inputValue) {
        this(inputValue, false);
    }

    @Override
    public double calc() {
        double value = inRadian ? inputValue : TrigonometryFunction.convertToRadian(inputValue);
        return 1.0 / Math.tan(value);
    }

    @Override
    public String toString() {
        return "ctg";
    }
}