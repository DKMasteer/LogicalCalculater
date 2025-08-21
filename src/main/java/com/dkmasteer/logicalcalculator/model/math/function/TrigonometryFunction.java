package com.dkmasteer.logicalcalculator.model.math.function;

import com.dkmasteer.logicalcalculator.model.math.MathConstant;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 15.08.2025 15:56
 */

public abstract class TrigonometryFunction extends Function {
    protected boolean inRadian;

    public TrigonometryFunction(double inputValue, boolean inRadian) {
        super(inputValue);
        this.inRadian = inRadian;
    }

    public TrigonometryFunction(boolean inRadian) {
        this(BASE_VALUE, inRadian);
    }

    public TrigonometryFunction() {
        this(BASE_VALUE, false);
    }

    public TrigonometryFunction(double inputValue) {
        this(inputValue, false);
    }

    protected static double convertToRadian(double value) {
        return value * MathConstant.PI / 180.0;
    }

    @Override
    public String toString() {
        return "TGM";
    }
}
