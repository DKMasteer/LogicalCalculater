package com.dkmasteer.logicalcalculator.model.math.function.functions.arithmetic;

import com.dkmasteer.logicalcalculator.model.math.function.Function;

/**
 * @author daniilkrasnov
 * @date 15.08.2025 15:53
 */

public class Log extends Function {
    @Override
    public double calc() {
        return Math.log10(inputValue);
    }

    @Override
    public String toString() {
        return "LOG";
    }
}
