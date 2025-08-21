package com.dkmasteer.logicalcalculator.model.node.cells.logical_elems;

import com.dkmasteer.logicalcalculator.model.math.Number;
import com.dkmasteer.logicalcalculator.model.node.ShapePaint;
import com.dkmasteer.logicalcalculator.model.node.cells.LogicalElement;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * Класс END-point-а
 *
 * @author daniilkrasnov
 * @date 17.08.2025 14:59
 */

public class EndPoint extends LogicalElement {
    // Числовое значение на входе, изначально 0.0
    private Number input_num = new Number();

    // Занят ли вход? Изначально нет
    private boolean inputValueSetted = false;

    // Кружок входа
    private Circle inputCircle;

    public EndPoint(double x, double y) {
        super(x, y);

        // Создаём интерактивные элементы calc-ячейки
        createInteractiveElements(x, y, "  END");

        // Создаём результат
        createResultText(1);

        // Создаём вход
        this.inputCircle = ShapePaint.createInputCircle(rectangle.getWidth(), x, y);

        // Добавляем к ним визуальный эффект выбора
        ShapePaint.addUIFunc(this, rectangle, contentText, outputCircle, inputCircle);
    }

    // Отрисовка элементов
    @Override
    public void paintComponents(AnchorPane pane) {
        pane.getChildren().addAll(contentText, resultText, rectangle, outputCircle, inputCircle);
    }

    // Результат END-point-а - значение на входе
    @Override
    public void calculate() {
        if (inputValueSetted) {
            super.resultText.setText(formatAnswer(input_num));
        } else {
            System.out.println("Not setted inputValue EndPoint.java");
        }
    }

    // Getters и Setters
    public boolean isInputValueSetted() {
        return inputValueSetted;
    }

    public void setInputValueSetted(boolean inputValueSetted) {
        this.inputValueSetted = inputValueSetted;
    }

    public Circle getInputCircle() {
        return inputCircle;
    }

    public void setInputCircle(Circle inputCircle) {
        this.inputCircle = inputCircle;
    }

    public double getInputValue() {
        return input_num.getValue();
    }

    public void setInput(Number inputValue) {
        this.input_num = inputValue;

        // Ячейка теперь занята
        inputValueSetted = true;
    }
}
