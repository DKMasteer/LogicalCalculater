package com.dkmasteer.logicalcalculator;

import com.dkmasteer.logicalcalculator.model.data_storage.ScenePath;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LCRunner extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(LCRunner.class.getResource(ScenePath.CalculatorScene));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Logical calculator");
        stage.setScene(scene);
        stage.show();
    }
}
