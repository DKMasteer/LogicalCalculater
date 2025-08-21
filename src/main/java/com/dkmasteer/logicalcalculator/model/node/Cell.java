package com.dkmasteer.logicalcalculator.model.node;

import com.dkmasteer.logicalcalculator.model.math.Number;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Класс-родитель для всех видов ячеек (число, функция, операция и end-point)
 *
 * @author daniilkrasnov
 * @date 17.08.2025 11:17
 */

public abstract class Cell {
    // Базовые элементы (присутствуют у всех)
    protected Rectangle rectangle;
    protected Text contentText;
    protected Circle outputCircle;

    // Координаты
    protected double x;
    protected double y;

    // Количество свободных входов, изначально 0
    protected byte inputs = 0;

    // Максимальное количество допустимых символов
    private static final int DECIMALS = 12;
    private static final int ANSWER_DECIMALS = 4;

    public Cell(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Отрисовываем фигуры элементы, в зависимости от состава ячейки переопределяется
    public void paintComponents(AnchorPane pane) {
        pane.getChildren().addAll(contentText, rectangle, outputCircle);
    }

    // Создаём интерактивные элементы
    protected void createInteractiveElements(double x, double y, double value) {
        rectangle = ShapePaint.createNumberRectangle(value, x, y);
        contentText = ShapePaint.createValueText(value, rectangle.getWidth(), x, y);
        outputCircle = ShapePaint.createOutputCircle(rectangle.getWidth(), x, y);
    }

    protected void createInteractiveElements(double x, double y, String content) {
        rectangle = ShapePaint.createCalcRectangle(x, y);
        contentText = ShapePaint.createCalcText(content, x, y);
        outputCircle = ShapePaint.createOutputCircle(rectangle.getWidth(), x, y);
    }

    // Форматирование строки в приемлемый вид, до decimals цифр
    // Иначе - запись в экспоненциальной записи
    private static String format(double value, int decimals) {
        String valueStr = Double.toString(value);

        if (valueStr.length() > decimals) {
            String formula = "%." + decimals + "e";
            return String.format(formula, value);
        }

        return valueStr.replace(".", ",");
    }

    // Форматирование строки в приемлемый вид, до 12 цифр
    protected static String format(double value) {
        return format(value, DECIMALS);
    }

    // Форматирование строки в приемлемый вид, но параметр - number
    protected static String format(Number num) {
        return format(num.getValue());
    }

    // Форматирование ответа, до 4 цифр
    protected static String formatAnswer(Number answer) {
        return format(answer.getValue(), ANSWER_DECIMALS);
    }

    // Getters and setters
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Text getContentText() {
        return contentText;
    }

    public void setContentText(Text contentText) {
        this.contentText = contentText;
    }

    public Circle getOutputCircle() {
        return outputCircle;
    }

    public void setOutputCircle(Circle outputCircle) {
        this.outputCircle = outputCircle;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public byte getInputs() {
        return inputs;
    }

    public void setInputs(byte inputs) {
        this.inputs = inputs;
    }
}
