package com.dkmasteer.logicalcalculator.model.data_storage;

/**
 * @author daniilkrasnov
 * @date 18.08.2025 15:08
 */

/**
 * buttons' id:
 * plus_btn
 * minus_btn
 * mult_btn
 * devide_btn
 * mod_btn
 * cqrt_btn
 * sqrd_btn
 * ln_btn
 * log_btn
 * sin_btn
 * cos_btn
 * tg_btn
 * ctg_btn
 * e_btn
 * pi_btn
 * numb_btn
 * */
public enum Action {
    NOT_SELECTED, DELETE, LOOKING_FOR_INPUT,
    PLUS, MINUS, MULTIPLY, DIVIDE,
    MOD, CBRT, SQRT, LN,
    LOG, SIN, COS, TG,
    CTG, E, PI, NUMBER,
    END, POW, BIGGER, SMALLER,
    ABS
}
