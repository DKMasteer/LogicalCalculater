package com.dkmasteer.logicalcalculator.model.node.cells;

import com.dkmasteer.logicalcalculator.model.math.Number;
import com.dkmasteer.logicalcalculator.model.node.Cell;
import com.dkmasteer.logicalcalculator.model.node.ShapePaint;

import java.io.Serializable;

/**
 * Класс-ячейка переменной
 *
 * @author daniilkrasnov
 * @date 17.08.2025 14:47
 */

public class NumberCell extends Cell {
    // Какое значение хранит ячейка
    private Number value_num;

    public NumberCell(double x, double y, double value) {
        super(x, y);

        // Создаём интерактивные элементы
        createInteractiveElements(x, y, value);

        // Добавляем к ним визуальный эффект выбора
        ShapePaint.addUIFunc(this, rectangle, contentText, outputCircle);

        // Устанавливаем значение
        value_num = new Number(value);
    }

    // Getter и Setter
    public Number getValue() {
        return value_num;
    }

    public void setValue(Number value) {
        this.value_num = value;
        // Обновляем ячейку (она могла стать сильно меньше / больше, так что перерисовываем все)
        createInteractiveElements(x, y, value.getValue());
    }
}
