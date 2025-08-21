package com.dkmasteer.logicalcalculator.run;

/**
 * @author daniilkrasnov
 * @date 10.08.2025 12:40
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;

/**
 * Methods:
 * 1) load(String pathTo)
 * */

public class LCSceneLoader {
    public static Scene load(String pathTo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File(pathTo).toURI().toURL());
            return fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("NULL");
            return null;
        }
    }
}