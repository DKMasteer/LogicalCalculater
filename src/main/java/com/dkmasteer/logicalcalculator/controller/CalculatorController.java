package com.dkmasteer.logicalcalculator.controller;

import com.dkmasteer.logicalcalculator.model.data_storage.Action;
import com.dkmasteer.logicalcalculator.model.data_storage.Actionear;
import com.dkmasteer.logicalcalculator.model.data_storage.Linear;
import com.dkmasteer.logicalcalculator.model.io.Opener;
import com.dkmasteer.logicalcalculator.model.io.Saver;
import com.dkmasteer.logicalcalculator.model.math.function.functions.arithmetic.*;
import com.dkmasteer.logicalcalculator.model.math.function.functions.trigonometry.Cos;
import com.dkmasteer.logicalcalculator.model.math.function.functions.trigonometry.Ctg;
import com.dkmasteer.logicalcalculator.model.math.function.functions.trigonometry.Sin;
import com.dkmasteer.logicalcalculator.model.math.function.functions.trigonometry.Tg;
import com.dkmasteer.logicalcalculator.model.math.operation.operations.*;
import com.dkmasteer.logicalcalculator.model.node.Cell;
import com.dkmasteer.logicalcalculator.model.node.cells.LogicalElement;
import com.dkmasteer.logicalcalculator.model.node.cells.NumberCell;
import com.dkmasteer.logicalcalculator.model.node.cells.logical_elems.EndPoint;
import com.dkmasteer.logicalcalculator.model.node.cells.logical_elems.math_logical_elements.FunctionCell;
import com.dkmasteer.logicalcalculator.model.node.cells.logical_elems.math_logical_elements.OperationCell;
import com.dkmasteer.logicalcalculator.model.user_interaction.AnswerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author daniilkrasnov
 * @date 10.08.2025 11:56
 */

public class CalculatorController implements Initializable {
    @FXML private AnchorPane LCAction_ap;
    @FXML private Label LCArithmeticTitle_lbl;
    @FXML private Button LCCalc_btn;
    @FXML private VBox LCElemets_vb;
    @FXML private Label LCInputTitle_lbl;
    @FXML private Label LCTrigonometryTitle_lbl;
    @FXML private CheckBox inRadians_cb;
    @FXML private Label status_lbl;

    private final ArrayList<Cell> cellList = new ArrayList<>();
    private final ArrayList<Line> linesList = new ArrayList<>();
    private final Linear linear = Linear.getInstance();
    private Actionear actionear = Actionear.getInstance();

    @FXML
    void calculateOnAction(ActionEvent event) {
        for (Cell cell : cellList) {
            if (cell instanceof LogicalElement logicalElement) {
               logicalElement.drawResult();
            }
        }
        status_lbl.setText("Подсчёт");
    }

    @FXML
    void changeItemTo(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        String id = clicked.getId();
        Action action;

        switch (id) {
            case "plus_btn":
                status_lbl.setText("Сумма");
                action = Action.PLUS;
                break;
            case "minus_btn":
                status_lbl.setText("Разность");
                action = Action.MINUS;
                break;
            case "mult_btn":
                status_lbl.setText("Умножение");
                action = Action.MULTIPLY;
                break;
            case "divide_btn":
                status_lbl.setText("Деление");
                action = Action.DIVIDE;
                break;
            case "mod_btn":
                status_lbl.setText("Остаток");
                action = Action.MOD;
                break;
            case "cbrt_btn":
                status_lbl.setText("Корень 3");
                action = Action.CBRT;
                break;
            case "sqrt_btn":
                status_lbl.setText("Корень 2");
                action = Action.SQRT;
                break;
            case "ln_btn":
                status_lbl.setText("LogE");
                action = Action.LN;
                break;
            case "log_btn":
                status_lbl.setText("Log");
                action = Action.LOG;
                break;
            case "sin_btn":
                status_lbl.setText("Синус");
                action = Action.SIN;
                break;
            case "cos_btn":
                status_lbl.setText("Косинус");
                action = Action.COS;
                break;
            case "tg_btn":
                status_lbl.setText("Тангенс");
                action = Action.TG;
                break;
            case "ctg_btn":
                status_lbl.setText("Котангенс");
                action = Action.CTG;
                break;
            case "e_btn":
                status_lbl.setText("Число: E");
                action = Action.E;
                break;
            case "pi_btn":
                status_lbl.setText("Число: π");
                action = Action.PI;
                break;
            case "numb_btn":
                status_lbl.setText("Переменная");
                action = Action.NUMBER;
                break;
            case "delete_btn":
                status_lbl.setText("Удаление");
                action = Action.DELETE;
                break;
            case "pow_btn":
                status_lbl.setText("Степень");
                action = Action.POW;
                break;
            case "bigger_btn":
                status_lbl.setText("Больше");
                action = Action.BIGGER;
                break;
            case "smaller_btn":
                status_lbl.setText("Меньше");
                action = Action.SMALLER;
                break;
            case "end_btn":
                status_lbl.setText("Конец");
                action = Action.END;
                break;
            case "abs_btn":
                status_lbl.setText("Модуль");
                action = Action.ABS;
                break;
            default:
                status_lbl.setText("Не выбрано");
                action = Action.NOT_SELECTED;
                break;
        }

        actionear.setCurrentAction(action);
    }

    public Cell createCell(double x, double y) {
        return switch (actionear.getCurrentAction()) {
            case PLUS -> new OperationCell(x, y, new Sum());
            case MINUS -> new OperationCell(x, y, new Minus());
            case MULTIPLY -> new OperationCell(x, y, new Multiply());
            case DIVIDE -> new OperationCell(x, y, new Divide());
            case MOD -> new OperationCell(x, y, new Mod());
            case POW -> new OperationCell(x, y, new Pow());
            case BIGGER -> new OperationCell(x, y, new Bigger());
            case SMALLER -> new OperationCell(x, y, new Smaller());
            case CBRT -> new FunctionCell(x, y, new Cbrt());
            case SQRT -> new FunctionCell(x, y, new Sqrt());
            case LN -> new FunctionCell(x, y, new Ln());
            case LOG -> new FunctionCell(x, y, new Log());
            case ABS -> new FunctionCell(x, y, new Abs());
            case SIN -> new FunctionCell(x, y, new Sin(inRadians_cb.isSelected()));
            case COS -> new FunctionCell(x, y, new Cos(inRadians_cb.isSelected()));
            case TG -> new FunctionCell(x, y, new Tg(inRadians_cb.isSelected()));
            case CTG -> new FunctionCell(x, y, new Ctg(inRadians_cb.isSelected()));
            case E -> new NumberCell(x, y, Math.E);
            case PI -> new NumberCell(x, y, Math.PI);
            case NUMBER -> new NumberCell(x, y, AnswerUtil.getNumber());
            case END -> new EndPoint(x, y);
            default -> null;  // или "unknown_btn"
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Linear.setPane(LCAction_ap);
        LCAction_ap.setOnMouseClicked(mouseEvent -> {
            if (actionear.isCell()) {
                Cell newCell = createCell(mouseEvent.getX(), mouseEvent.getY());
                newCell.paintComponents(LCAction_ap);
                cellList.add(newCell);
                actionear.drop();
                status_lbl.setText("Не выбрано");
            } else {
                status_lbl.setText("Связка");
            }
        });
    }
}