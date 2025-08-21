package com.dkmasteer.logicalcalculator.model.user_interaction;

import com.dkmasteer.logicalcalculator.LCRunner;
import com.dkmasteer.logicalcalculator.controller.NumberController;
import com.dkmasteer.logicalcalculator.model.data_storage.ScenePath;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author daniilkrasnov
 * @date 18.08.2025 16:27
 */

public class AnswerUtil {
    public static double getNumber() {
        FXMLLoader loader = new FXMLLoader(LCRunner.class.getResource(ScenePath.NumberScene));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NumberController controller = loader.getController();

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.setScene(new Scene(root));
        dialogStage.setTitle("Ввод числа");
        dialogStage.showAndWait();

        if (controller.isConfirmed()) {
            return controller.getResultNumber();
        }

        return -1;
    }
}