package com.dkmasteer.logicalcalculator.model.math.function.functions.trigonometry;

import com.dkmasteer.logicalcalculator.model.math.function.TrigonometryFunction;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
* @author daniilkrasnov
* @date 15.08.2025 15:53
*/

public class Tg extends TrigonometryFunction {
    public Tg(double inputValue, boolean inRadian) {
        super(inputValue, inRadian);
    }

    public Tg(boolean inRadian) {
        this(BASE_VALUE, inRadian);
    }

    public Tg() {
        this(BASE_VALUE, false);
    }

    public Tg(double inputValue) {
        this(inputValue, false);
    }

    @Override
    public double calc() {
        double value = inRadian ? inputValue : TrigonometryFunction.convertToRadian(inputValue);
        return Math.tan(value);
    }

    @Override
    public String toString() {
        return "tg";
    }
}