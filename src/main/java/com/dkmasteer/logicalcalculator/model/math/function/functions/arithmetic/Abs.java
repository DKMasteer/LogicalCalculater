package com.dkmasteer.logicalcalculator.model.math.function.functions.arithmetic;

import com.dkmasteer.logicalcalculator.model.math.function.Function;

/**
 * @author daniilkrasnov
 * @date 15.08.2025 15:53
 */

public class Abs extends Function {
    @Override
    public double calc() {
        return Math.abs(inputValue);
    }

    @Override
    public String toString() {
        return " |X|";
    }
}
