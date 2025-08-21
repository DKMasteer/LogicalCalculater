package com.dkmasteer.logicalcalculator.model.node.cells.logical_elems.math_logical_elements;

import com.dkmasteer.logicalcalculator.model.math.operation.Operation;
import com.dkmasteer.logicalcalculator.model.node.ShapePaint;
import com.dkmasteer.logicalcalculator.model.node.cells.LogicalElement;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import com.dkmasteer.logicalcalculator.model.math.Number;

import java.io.Serializable;

/**
 * Класс операции:
 * - 2 входа
 * - Есть математика внутри
 *
 * @author daniilkrasnov
 * @date 17.08.2025 15:07
 */

public class OperationCell extends LogicalElement {
    // Операция
    private Operation operation;

    // Кружки входа
    private final Circle inputCircle1;
    private final Circle inputCircle2;

    // Значения на кружках входа, изначально 0.0
    private Number input1_num = new Number();
    private Number input2_num = new Number();

    // Заняты ли кружки? Изначально нет
    private boolean isInput1Setted = false;
    private boolean isInput2Setted = false;

    public OperationCell(double x, double y, Operation operation) {
        super(x, y);

        // Создаём базовые интерактивные элементы
        createInteractiveElements(x, y, operation.toString());

        // Создаём текст результата
        createResultText(2);

        // Задаём операцию
        this.operation = operation;

        // Создаём кружочки
        Circle[] circles = ShapePaint.createInputCircles(rectangle.getWidth(), x, y);
        this.inputCircle1 = circles[0];
        this.inputCircle2 = circles[1];

        // Добавляем интерактивность при наведении
        ShapePaint.addUIFunc(this, rectangle, contentText, outputCircle, inputCircle1, inputCircle2);
    }

    // Отрисовка элементов
    @Override
    public void paintComponents (AnchorPane pane) {
        pane.getChildren().addAll(contentText, rectangle, outputCircle, inputCircle1, inputCircle2, resultText);
    }

    // Расчитываем итоговое значение
    @Override
    public void calculate() {
        if (isInput1Setted && isInput2Setted) {
//            System.out.println(input1_num + " " + input2_num);

            operation.setFirstValue(input1_num.getValue());
            operation.setSecondValue(input2_num.getValue());

            result_num.setValue(operation.calc());

            super.resultText.setText(formatAnswer(result_num));
        } else {
            System.out.println("Not setted values: OperationCell.java");
        }
    }

    // Getters и Setters
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Number getInput1() {
        return input1_num;
    }

    public void setInput1(Number input1) {
        this.input1_num = input1;
        // 1 input теперь занят
        isInput1Setted = true;
    }

    public Number getInput2() {
        return input2_num;
    }

    public void setInput2(Number input2) {
        this.input2_num = input2;
        // 2 input теперь занят
        isInput2Setted = true;
    }

    public Circle getInputCircle1() {
        return inputCircle1;
    }

    public Circle getInputCircle2() {
        return inputCircle2;
    }

    public boolean isInput1Setted() {
        return isInput1Setted;
    }

    public boolean isInput2Setted() {
        return isInput2Setted;
    }

    public void setInput1Setted(boolean input1Setted) {
        this.isInput1Setted = input1Setted;
    }

    public void setInput2Setted(boolean input2Setted) {
        this.isInput2Setted = input2Setted;
    }
}