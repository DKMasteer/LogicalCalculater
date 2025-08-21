package com.dkmasteer.logicalcalculator.model.data_storage;

import com.dkmasteer.logicalcalculator.model.math.Number;
import com.dkmasteer.logicalcalculator.model.node.Cell;
import com.dkmasteer.logicalcalculator.model.node.ShapePaint;
import com.dkmasteer.logicalcalculator.model.node.cells.LogicalElement;
import com.dkmasteer.logicalcalculator.model.node.cells.NumberCell;
import com.dkmasteer.logicalcalculator.model.node.cells.logical_elems.EndPoint;
import com.dkmasteer.logicalcalculator.model.node.cells.logical_elems.math_logical_elements.FunctionCell;
import com.dkmasteer.logicalcalculator.model.node.cells.logical_elems.math_logical_elements.OperationCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import static com.dkmasteer.logicalcalculator.model.math.MathConstant.BASE_VALUE;

/**
 * @author daniilkrasnov
 * @date 19.08.2025 10:59
 */

public class Linear {
    private static Linear instance;
    private static AnchorPane pane;
    private Cell outputCell;
    private LogicalElement inputCell;
    private final Actionear actionear = Actionear.getInstance();

    private Circle inputCircle;

    private Linear() {}

    public static Linear getInstance() {
        if (instance == null) {
            instance = new Linear();
        }
        return instance;
    }

    public static void setPane(AnchorPane pane) {
        Linear.pane = pane;
    }

    public void addElement(Cell element) {
        if (outputCell == null) {
            if (element.getOutputCircle().getFill() == Color.GREEN) {
                System.out.println("Added new outputCell: " + element.toString());
                outputCell = element;
                ShapePaint.paintCircle(outputCell.getOutputCircle(), Color.YELLOW, Color.TRANSPARENT); // Подчеркиваем выбор
                actionear.look();
                System.out.println(Actionear.getInstance().getCurrentAction());
            } else {
                System.out.println("Нет, мистер программистер. Двойное соединение запрещено!");
                refresh();
            }
        } else if (element instanceof LogicalElement) {
            if (element != outputCell) {
                if (((LogicalElement) element).hasFreeInputs()) {
                    inputCell = (LogicalElement) element;

                    // draw line
                    Circle outputCircle = outputCell.getOutputCircle();
                    Circle inputCircle = this.inputCircle;

                    if (inputCircle == null) {
                        // Автоматически определяем точку
                        if (inputCell instanceof OperationCell inputOperCell) {
                            System.out.println("inputOperCell.getInputs() = " + inputOperCell.getInputs());
                            if (inputOperCell.getInputs() == 2) {
                                inputCircle = inputOperCell.getY() >= outputCell.getY() ? bookCircle1(inputOperCell) : bookCircle2(inputOperCell);
                            } else {
                                if (inputOperCell.isInput1Setted()) {
                                    inputCircle = bookCircle2(inputOperCell);
                                } else {
                                    inputCircle = bookCircle1(inputOperCell);
                                }
                            }
                        } else if (inputCell instanceof FunctionCell) {
                            inputCircle = bookFuncCircle((FunctionCell) inputCell);
                        } else {
                            inputCircle = bookEndCircle((EndPoint) inputCell);
                        }
                    } else if (inputCell instanceof OperationCell inputOperCell) {
                        if (inputCircle == inputOperCell.getInputCircle1()) bookCircle1(inputOperCell);
                        else bookCircle2(inputOperCell);
                    } else if (inputCell instanceof FunctionCell functionCell) {
                        bookFuncCircle(functionCell);
                    } else if (inputCell instanceof EndPoint endPoint) {
                        bookEndCircle(endPoint);
                    } else {
                        System.out.println("Not booked, Linear.java");
                    }

                    drawLine(outputCircle, inputCircle);
                } else {
                    ShapePaint.toCommonCircle(outputCell.getOutputCircle());
                    refresh();
                    System.out.println("Ноу, мистер фиш. Свободных мест нет");
                }
            } else {
                ShapePaint.toCommonCircle(outputCell.getOutputCircle());
                refresh();
                System.out.println("Один и тот же элемент, скрепление запрещено");
            }
        } else {
            // Нельзя вторым число
            ShapePaint.toCommonCircle(outputCell.getOutputCircle());
            ShapePaint.toCommonCircle(element.getOutputCircle());
            refresh();
            Actionear.getInstance().drop();
            System.out.println("Такие элементы связать нельзя");
        }
    }

    public void refresh(Circle outputCircle, Circle inputCircle) {
        makeCirclesTransparent(outputCircle, inputCircle);
        refresh();
    }

    public void refresh() {
        actionear.drop();
        inputCell = null;
        outputCell = null;
        inputCircle = null;
    }

    public void link(Circle outputCircle, Circle inputCircle) {
        ShapePaint.toLinkCircles(outputCircle, inputCircle);
        refresh();
    }

    private Number getValueFromCell() {
        if (outputCell instanceof NumberCell numberCell) {
            return numberCell.getValue();
        } else if (outputCell instanceof LogicalElement logicalElement) {
            return logicalElement.getResult();
        }

        return new Number();
    }

    private Circle bookCircle1(OperationCell inputCell) {
        inputCell.setInput1Setted(true);
        inputCell.setInputs((byte) (inputCell.getInputs() - 1));
        inputCell.setInput1(getValueFromCell());
        return inputCell.getInputCircle1();
    }

    private Circle bookCircle2(OperationCell inputCell) {
        inputCell.setInput2Setted(true);
        inputCell.setInputs((byte) (inputCell.getInputs() - 1));
        inputCell.setInput2(getValueFromCell());
        return inputCell.getInputCircle2();
    }

    private Circle bookFuncCircle(FunctionCell inputCell) {
        inputCell.setInputSetted(true);
        inputCell.setInputs((byte) 0);
        inputCell.setInput(getValueFromCell());
        return inputCell.getInputCircle();
    }

    private Circle bookEndCircle(EndPoint inputCell) {
        inputCell.setInputValueSetted(true);
        inputCell.setInputs((byte) 0);
        inputCell.setInput(getValueFromCell());
        return inputCell.getInputCircle();
    }

    private void makeCirclesTransparent(Circle outputCircle, Circle inputCircle) {
        ShapePaint.toCommonCircle(outputCircle);
        ShapePaint.toCommonCircle(inputCircle);
    }

    public void drawLine(Circle outputCircle, Circle inputCircle) {
        if (pane != null) {
            if (outputCell != null && inputCell != null) {
                double x1 = outputCircle.getCenterX(), y1 = outputCircle.getCenterY();
                double x2 = inputCircle.getCenterX(), y2 = inputCircle.getCenterY();

                Line line = ShapePaint.createLine(x1, y1, x2, y2);
                pane.getChildren().add(line);

                link(outputCircle, inputCircle);
            } else {
                System.out.println("Не могу нарисовать линию: ");
                System.out.println("OutputCell = null ? " + (outputCell == null ? "Да" : "Нет"));
                System.out.println("InputCell = null ? " + (inputCell == null ? "Да" : "Нет"));
            }
        } else {
            System.out.println("Не могу нарисовать линию, pane = null!");
        }
    }


    public static AnchorPane getPane() {
        return pane;
    }

    public Cell getOutputCell() {
        return outputCell;
    }

    public void setOutputCell(Cell outputCell) {
        this.outputCell = outputCell;
    }

    public LogicalElement getInputCell() {
        return inputCell;
    }

    public void setInputCell(LogicalElement inputCell) {
        this.inputCell = inputCell;
    }

    public Circle getInputCircle() {
        return inputCircle;
    }

    public void setInputCircle(Circle inputCircle) {
        this.inputCircle = inputCircle;
    }
}