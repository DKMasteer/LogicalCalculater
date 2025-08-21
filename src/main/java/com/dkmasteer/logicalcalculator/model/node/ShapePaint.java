package com.dkmasteer.logicalcalculator.model.node;

import com.dkmasteer.logicalcalculator.model.data_storage.Action;
import com.dkmasteer.logicalcalculator.model.data_storage.Actionear;
import com.dkmasteer.logicalcalculator.model.data_storage.Linear;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Класс-утилита для создания ячеек в виде shape
 *
 * @author daniilkrasnov
 * @date 17.08.2025 13:00
 */

public class ShapePaint {
    private static final double NUMB_RECT_HEIGHT = 25;
    private static final double CALC_RECT_WIDTH = 40;
    private static final double CALC_RECT_HEIGHT = 30;
    private static final double DECIMAL_CHAR_WIDTH = 7.2; // Optimal for default JavaFX font
    private static final double DECIMAL_PADDING = 6; // 3px on each side
    private static final double RADIUS = 4;
    private static final double LINE_STROKE_WIDTH = 2.5;
    private static final double RECT_STROKE_WIDTH = 1;

    private static final Color BASE_LINE_COLOR = Color.YELLOW;
    private static final Color BASE_NUM_TEXT_COLOR = Color.WHITE;
    private static final Color BASE_CALC_TEXT_COLOR = Color.RED;
    private static final Color BASE_RECT_COLOR = Color.BLUE;

    private static final Color BASE_CIRCLE_COLOR = Color.GREEN;
    private static final Color BASE_LINKED_CIRCLE_COLOR = Color.YELLOW;
    private static final Color BASE_UNDERLINED_CIRCLE_COLOR = Color.WHITE;
    private static final Color INVISIBLE = Color.TRANSPARENT;

    private ShapePaint() {
    }

    // Methods for creating rectangle of cell
    public static Rectangle createNumberRectangle(double value, double x, double y, Color borderColor) {
        double rectangleWidth = Cell.format(value).length() * DECIMAL_CHAR_WIDTH + DECIMAL_PADDING;

        Rectangle rectangle = new Rectangle(x - rectangleWidth / 2,
                y - NUMB_RECT_HEIGHT / 2,
                rectangleWidth,
                NUMB_RECT_HEIGHT);

        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStrokeWidth(RECT_STROKE_WIDTH);
        rectangle.setStroke(borderColor);

        return rectangle;
    }

    public static Rectangle createNumberRectangle(double value, double x, double y) {
        return createNumberRectangle(value, x, y, BASE_RECT_COLOR);
    }

    public static Rectangle createCalcRectangle(double x, double y, Color borderColor) {
        Rectangle rectangle = new Rectangle(x - CALC_RECT_WIDTH / 2,
                y - CALC_RECT_HEIGHT / 2,
                CALC_RECT_WIDTH,
                CALC_RECT_HEIGHT);

        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStrokeWidth(RECT_STROKE_WIDTH);
        rectangle.setStroke(borderColor);

        rectangle.setOnMouseClicked(_ -> rectangle.setStrokeWidth(1));

        rectangle.setOnMouseEntered(_ -> rectangle.setStrokeWidth(3));
        rectangle.setOnMouseExited(_ -> rectangle.setStrokeWidth(1));

        return rectangle;
    }

    public static Rectangle createCalcRectangle(double x, double y) {
        return createCalcRectangle(x, y, Color.PURPLE);
    }

    // Methods for creating output circle of cell
    public static Circle createOutputCircle(double rectWidth, double x, double y) {
        return createOutputCircle(rectWidth, x, y, BASE_CIRCLE_COLOR);
    }

    // Methods for creating styled circle
    private static Circle createCircle(double x, double y, Color color) {
        Circle circle = new Circle(x, y, RADIUS);

        circle.setFill(color);

        circle.setOnMouseClicked(_ -> circle.setStroke(Color.GREEN));
        circle.setOnMouseEntered(_ -> circle.setStroke(Color.WHITE));
        circle.setOnMouseExited(_ -> circle.setStroke(Color.TRANSPARENT));

        return circle;
    }

    public static Circle createOutputCircle(double rectWidth, double x, double y, Color circleColor) {
        return createCircle(x + rectWidth / 2, y, circleColor);
    }

    // Methods for creating input circle of cell
    public static Circle createInputCircle(double rectWidth, double x, double y) {
        return createInputCircle(rectWidth, x, y, BASE_CIRCLE_COLOR);
    }

    public static Circle createInputCircle(double rectWidth, double x, double y, Color circleColor) {
        return createCircle(x - rectWidth / 2, y, circleColor);
    }

    // Methods for creating two input circles of cell (operation cell)
    public static Circle[] createInputCircles(double rectWidth, double x, double y) {
        return createInputCircles(rectWidth, x, y,
                BASE_CIRCLE_COLOR, BASE_CIRCLE_COLOR);
    }

    public static Circle[] createInputCircles(double rectWidth, double x, double y, Color circleColor) {
        return createInputCircles(rectWidth, x, y, circleColor, circleColor);
    }

    public static Circle[] createInputCircles(double rectWidth, double x, double y, Color circleColor1, Color circleColor2) {
        return new Circle[]{
                createCircle(x - rectWidth / 2, y - NUMB_RECT_HEIGHT / 4, circleColor1),
                createCircle(x - rectWidth / 2, y + NUMB_RECT_HEIGHT / 4, circleColor2)
        };
    }

    // Method for creating text
    private static Text createText(String content, double rectWidth, double x, double y, Color contentColor) {
        Text contentText = new Text(content);

        contentText.setFill(contentColor);
        contentText.setLayoutX(x - rectWidth / 2 + 3);
        contentText.setLayoutY(y + contentText.getBoundsInLocal().getHeight() / 4);


        return contentText;
    }

    // Method for creating value text
    public static Text createValueText(double value, double rectWidth, double x, double y, Color contentColor) {
        return createText(Cell.format(value), rectWidth, x, y, contentColor);
    }

    public static Text createValueText(double value, double rectWidth, double x, double y) {
        return createValueText(value, rectWidth, x, y, BASE_NUM_TEXT_COLOR);
    }

    // Method for creating regular text, for example 'sin'
    // Calc means for elements like operation, function and end-point
    public static Text createCalcText(String content, double x, double y, Color contentColor) {
        return createText(content, CALC_RECT_WIDTH, x, y, contentColor);
    }

    public static Text createCalcText(String content, double x, double y) {
        return createCalcText(content, x, y, BASE_CALC_TEXT_COLOR);
    }

    // Method for creating line
    public static Line createLine(double startX, double startY, double endX, double endY) {
        return createLine(startX, startY, endX, endY, BASE_LINE_COLOR);
    }

    public static Line createLine(double startX, double startY, double endX, double endY, Color lineColor) {
        Line line = new Line(startX, startY, endX, endY);

        line.setStroke(lineColor);
        line.setStrokeWidth(LINE_STROKE_WIDTH);

        return line;
    }

    // Create result for it
    public static Text createCalcResultText(double result, double rectWidth, double x, double y, Color contentColor) {
        String valueText = String.format("%.2f", result);

        Text resultText = new Text(valueText);

        resultText.setFill(contentColor);
        resultText.setLayoutX(x - rectWidth / 2 + 3);
        resultText.setLayoutY(y - CALC_RECT_WIDTH / 2 - 3);

        return resultText;
    }

    // For Numbers and Other not Logical elements
    public static void addUIFunc(Cell element, Rectangle rectangle, Text contentText, Circle outputCircle) {
        setOnClick(element, null, null, outputCircle, rectangle);
        setOnEntered(null, null, outputCircle, rectangle);
        setOnExited(null, null, outputCircle, rectangle);
    }

    // For logicalElement:Operation
    public static void addUIFunc(Cell element, Rectangle rectangle, Text contentText, Circle outputCircle, Circle inputCircle1, Circle inputCircle2) {
        setOnClick(element, inputCircle1, inputCircle2, outputCircle, rectangle);
        setOnEntered(inputCircle1, inputCircle2, outputCircle, rectangle);
        setOnExited(inputCircle1, inputCircle2, outputCircle, rectangle);
    }

    // For logicalElement:Function
    public static void addUIFunc(Cell element, Rectangle rectangle, Text contentText, Circle outputCircle, Circle inputCircle) {
        setOnClick(element, inputCircle, null, outputCircle, rectangle);
        setOnEntered(inputCircle, null, outputCircle, rectangle);
        setOnExited(inputCircle, null, outputCircle, rectangle);
    }

    // For operations
    private static void setOnClick(Cell element, Circle input1Circle, Circle input2Circle, Circle outputCircle, Rectangle rectangle) {
        // Fot input1Circle clicking
        if (input1Circle != null) {
            input1Circle.setOnMouseClicked(_ -> {
                // Выбор собственного кружочка
                System.out.println("input1Circle");
                if (input1Circle.getFill() != BASE_LINKED_CIRCLE_COLOR
                        && Actionear.getInstance().getCurrentAction() == Action.LOOKING_FOR_INPUT) {
                    Linear.getInstance().setInputCircle(input1Circle); // Раз уж сами выбрали
                }
                Linear.getInstance().addElement(element);
            });
        }

        // Fot input2Circle clicking
        if (input2Circle != null) {
            input2Circle.setOnMouseClicked(_ -> {
                if (input2Circle.getFill() != BASE_LINKED_CIRCLE_COLOR
                        && Actionear.getInstance().getCurrentAction() == Action.LOOKING_FOR_INPUT) {
                    Linear.getInstance().setInputCircle(input2Circle); // Раз уж сами выбрали
                }
                Linear.getInstance().addElement(element);
            });
        }

        // For output Clicking
        if (outputCircle != null) {
            outputCircle.setOnMouseClicked(mouseEvent -> {
                // Output circle и так только один
                Linear.getInstance().addElement(element);
            });
        }

        // For rectangle clicking
        if (rectangle != null) {
            System.out.println("Rect");
            rectangle.setOnMouseClicked(mouseEvent -> {
                Linear.getInstance().addElement(element);
                rectangle.setStrokeWidth(1);
            });
        }
    }

    private static void setOnEntered(Circle input1Circle, Circle input2Circle, Circle outputCircle, Rectangle rectangle) {
        if (input1Circle != null) {
            input1Circle.setOnMouseEntered(_ -> {
                rectangle.setStrokeWidth(3);

                if (input1Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                    input1Circle.setStroke(BASE_UNDERLINED_CIRCLE_COLOR);
                }
            });
        }

        // Fot input2Circle clicking
        if (input2Circle != null) {
            input2Circle.setOnMouseEntered(_ -> {
                rectangle.setStrokeWidth(3);
                if (input2Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                    input2Circle.setStroke(BASE_UNDERLINED_CIRCLE_COLOR);
                }
            });
        }

        // For output Clicking
        outputCircle.setOnMouseEntered(_ -> {
            rectangle.setStrokeWidth(3);
            if (outputCircle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                outputCircle.setStroke(BASE_UNDERLINED_CIRCLE_COLOR);
            }
        });

        // For rectangle
        rectangle.setOnMouseEntered(_ -> {
            rectangle.setStrokeWidth(3);

            System.out.println(Actionear.getInstance().getCurrentAction());
            if (Actionear.getInstance().getCurrentAction() != Action.LOOKING_FOR_INPUT) {
                if (outputCircle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                    outputCircle.setStroke(BASE_UNDERLINED_CIRCLE_COLOR);
                }
            } else {
                if (input2Circle == null) // => function or smt like that
                {
                    if (input1Circle != null && input1Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                        input1Circle.setStroke(BASE_UNDERLINED_CIRCLE_COLOR);
                    }
                } else {
                    System.out.println("Operation looking");
                    double y = Linear.getInstance().getOutputCell().getOutputCircle().getCenterY();

                    // Определить ближайший
                    if (input1Circle != null && y <= input1Circle.getCenterY()) {
                        if (input1Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                            input1Circle.setStroke(BASE_UNDERLINED_CIRCLE_COLOR);
                        } else if (input2Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                            input2Circle.setStroke(BASE_UNDERLINED_CIRCLE_COLOR);
                        }
                    } else {
                        if (input2Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                            input2Circle.setStroke(BASE_UNDERLINED_CIRCLE_COLOR);
                        } else if (input1Circle != null && input1Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                            input1Circle.setStroke(BASE_UNDERLINED_CIRCLE_COLOR);
                        }
                    }
                }
            }
        });
    }

    private static void setOnExited(Circle input1Circle, Circle input2Circle, Circle outputCircle, Rectangle rectangle) {
        if (input1Circle != null) {
            rectangle.setStrokeWidth(1);
            input1Circle.setOnMouseExited(_ -> {
                if (input1Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                    input1Circle.setStroke(INVISIBLE);
                }
            });
        }

        if (input2Circle != null) {
            rectangle.setStrokeWidth(1);
            input2Circle.setOnMouseExited(_ -> {
                if (input2Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                    input2Circle.setStroke(INVISIBLE);
                }
            });
        }

        outputCircle.setOnMouseExited(_ -> {
            rectangle.setStrokeWidth(1);
            if (outputCircle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                outputCircle.setStroke(INVISIBLE);
            }
        });

        rectangle.setOnMouseExited(_ -> {
            rectangle.setStrokeWidth(1);

            if (outputCircle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                outputCircle.setStroke(INVISIBLE);
            }

            if (input2Circle != null && input2Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                input2Circle.setStroke(INVISIBLE);
            }

            if (input1Circle != null && input1Circle.getFill() != BASE_LINKED_CIRCLE_COLOR) {
                input1Circle.setStroke(INVISIBLE);
            }
        });
    }

    public static void paintCircle(Circle circle, Color innerColor, Color strokeColor) {
        circle.setFill(innerColor);
        circle.setStroke(strokeColor);
    }

    // Баги здесь если что
    public static void toCommonCircle(Circle circle) {
        paintCircle(circle, BASE_CIRCLE_COLOR, Color.TRANSPARENT);
    }

    public static void toLinkCircles(Circle... circles) {
        for (Circle circle : circles) {
            paintCircle(circle, BASE_LINKED_CIRCLE_COLOR, Color.TRANSPARENT);
        }
    }
}