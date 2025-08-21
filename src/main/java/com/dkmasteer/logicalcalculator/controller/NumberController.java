package com.dkmasteer.logicalcalculator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author daniilkrasnov
 * @date 20.08.2025 20:53
 */

public class NumberController implements Initializable {
    @FXML private Button back_btn;
    @FXML private Button next_btn;
    @FXML private TextField number_tf;

    private Double resultNumber;
    private boolean confirmed = false;

    public Double getResultNumber() {
        return resultNumber;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @FXML
    void back(ActionEvent event) {
        confirmed = false;
        closeWindow();
    }

    @FXML
    void next(ActionEvent event) {
        validateAndProceed();
    }

    private void validateAndProceed() {
        String input = number_tf.getText().trim().replace(',', '.');

        if (check(input)) {
            try {
                resultNumber = Double.parseDouble(input);
                confirmed = true;
                closeWindow();
            } catch (NumberFormatException e) {
                showAlert("Ошибка", "Неверный формат числа", Alert.AlertType.ERROR);
                number_tf.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            }
        } else {
            showAlert("Ошибка", "Пожалуйста, введите корректное число", Alert.AlertType.WARNING);
            number_tf.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) number_tf.getScene().getWindow();
        stage.close();
    }

    private boolean check(String content) {
        if (content == null || content.trim().isEmpty()) {
            return false;
        }

        // Заменяем запятые на точки для проверки
        String normalizedContent = content.replace(',', '.');

        // Проверка на допустимые символы: цифры, точка, запятая, минус
        if (!normalizedContent.matches("^-?\\d*[.,]?\\d*$")) {
            return false;
        }

        // Проверка на несколько точек/запятых
        if (normalizedContent.chars().filter(ch -> ch == '.').count() > 1) {
            return false;
        }

        // Проверка на минус не в начале
        if (normalizedContent.contains("-") && normalizedContent.indexOf("-") != 0) {
            return false;
        }

        try {
            // Проверка на пустую строку после "-" или только разделитель
            if (normalizedContent.equals("-") || normalizedContent.equals(".") || normalizedContent.equals(",")) {
                return false;
            }

            Double.parseDouble(normalizedContent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Валидация на КАЖДОЕ нажатие клавиши
        number_tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                number_tf.setStyle("-fx-border-color: #D0D0D0; -fx-border-width: 1px;");
                next_btn.setDisable(true);
                return;
            }

            // Проверка в реальном времени (с учетом запятых)
            if (check(newValue)) {
                number_tf.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
                next_btn.setDisable(false);
            } else {
                number_tf.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                next_btn.setDisable(true);
            }
        });

        // Обработка нажатия Enter в текстовом поле
        number_tf.setOnAction(actionEvent -> {
            if (check(number_tf.getText())) {
                validateAndProceed();
            }
        });

        // Обработка каждого нажатия клавиши для валидации ввода (разрешаем запятые)
        number_tf.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("^-?[0-9]*[.,]?[0-9]*$")) {
                number_tf.setText(oldValue);
            }
        });

        // Установка начального стиля
        number_tf.setStyle("-fx-border-color: #D0D0D0; -fx-border-width: 1px;");
        next_btn.setDisable(true);

        // Фокус на текстовое поле при открытии
        number_tf.requestFocus();
    }
}