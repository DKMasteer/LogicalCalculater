package com.dkmasteer.logicalcalculator.model.node.cells;

import com.dkmasteer.logicalcalculator.model.math.Number;
import com.dkmasteer.logicalcalculator.model.node.Cell;
import com.dkmasteer.logicalcalculator.model.node.ShapePaint;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.Serializable;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * Класс-родитель для всех ячеек, обладающих логикой результирования
 *
 * @author daniilkrasnov
 * @date 17.08.2025 14:57
 */

public abstract class LogicalElement extends Cell {
    // Текст результата сверху
    transient protected Text resultText;

    // Значение результата
    // Используется для дальнейшей передачи
    transient protected Number result_num = new Number();

    public LogicalElement(double x, double y) {
        super(x, y);
    }

    // Проверка на то, есть ли свободные input-ы
    public boolean hasFreeInputs() {
        return inputs > 0;
    }

    // Считаем результат и заносим в resulttext
    protected abstract void calculate();

    // Создание текста результата (сверху)
    protected void createResultText(int inputs) {
        resultText = ShapePaint.createCalcResultText(BASE_VALUE, rectangle.getWidth(), x, y, Color.RED);
        resultText.setOpacity(0);
        setInputs((byte) inputs);
    }

    // Отрисовка результата
    public void drawResult() {
        calculate();
        resultText.setOpacity(1);
    }

    // Getters и Setters
    public Text getResultText() {
        return resultText;
    }

    public void setResultText(Text resultText) {
        this.resultText = resultText;
    }

    public Number getResult() {
        return result_num;
    }

    public void setResult(double result) {
        this.result_num.setValue(result);
    }
}
