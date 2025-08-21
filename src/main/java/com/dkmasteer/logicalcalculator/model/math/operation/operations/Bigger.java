package com.dkmasteer.logicalcalculator.model.math.operation.operations;

import com.dkmasteer.logicalcalculator.model.math.operation.Operation;

/**
 * @author daniilkrasnov
 * @date 20.08.2025 19:55
 */

public class Bigger extends Operation {
    @Override
    public double calc() {
        return Math.max(firstValue, secondValue);
    }

    @Override
    public String toString() {
        return "  >";
    }
}
