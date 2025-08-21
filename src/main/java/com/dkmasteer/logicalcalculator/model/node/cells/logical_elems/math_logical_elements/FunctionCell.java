package com.dkmasteer.logicalcalculator.model.node.cells.logical_elems.math_logical_elements;

import com.dkmasteer.logicalcalculator.model.math.Number;
import com.dkmasteer.logicalcalculator.model.math.function.Function;
import com.dkmasteer.logicalcalculator.model.node.ShapePaint;
import com.dkmasteer.logicalcalculator.model.node.cells.LogicalElement;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 17.08.2025 15:07
 */

public class FunctionCell extends LogicalElement {
    private Function function;
    private Number input_num = new Number();
    private boolean inputSetted = false;

    private Circle inputCircle;

    public FunctionCell(double x, double y, Function function) {
        super(x, y);
        this.function = function;
        super.rectangle = ShapePaint.createCalcRectangle(x, y);
        super.contentText = ShapePaint.createCalcText("  " + function.toString(), x, y, Color.AQUA);
        super.outputCircle = ShapePaint.createOutputCircle(rectangle.getWidth(), x, y);
        super.resultText = ShapePaint.createCalcResultText(BASE_VALUE, rectangle.getWidth(), x, y, Color.RED);
        super.resultText.setOpacity(0);
        this.inputCircle = ShapePaint.createInputCircle(rectangle.getWidth(), x, y);
        ShapePaint.addUIFunc(this, rectangle, contentText, outputCircle, inputCircle);
        setInputs((byte) 1);
    }

    @Override
    protected void calculate() {
        if (inputSetted) {
            System.out.println(input_num.getValue());
            function.setInputValue(input_num.getValue());
            result_num.setValue(function.calc());
            super.resultText.setText(formatAnswer(result_num));
        } else {
            System.out.println("Input wasn't set FunctionCell.java");
        }
    }

    @Override
    public void paintComponents (AnchorPane pane) {
        pane.getChildren().addAll(contentText, resultText, rectangle, outputCircle, inputCircle);
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public double getInput() {
        return input_num.getValue();
    }

    public void setInput(Number input) {
        this.input_num = input;
        inputSetted = true;
    }

    public Circle getInputCircle() {
        return inputCircle;
    }

    public void setInputCircle(Circle inputCircle) {
        this.inputCircle = inputCircle;
    }

    public boolean isInputSetted() {
        return inputSetted;
    }

    public void setInputSetted(boolean inputSetted) {
        this.inputSetted = inputSetted;
    }
}
