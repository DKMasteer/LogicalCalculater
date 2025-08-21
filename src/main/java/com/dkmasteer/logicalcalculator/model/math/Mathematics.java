package com.dkmasteer.logicalcalculator.model.math;

import java.io.Serializable;

/**
 * @author daniilkrasnov
 * @date 10.08.2025 14:46
 */

public interface Mathematics extends Serializable {
    double calc();

    static boolean isNumeric(String str) {
        return str.matches("-?\\d+([.,]\\d+)?");
    }
}