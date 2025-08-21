package com.dkmasteer.logicalcalculator.model.io;

import com.dkmasteer.logicalcalculator.model.node.Cell;

import java.io.*;
import java.util.ArrayList;

/**
 * @author daniilkrasnov
 * @date 21.08.2025 09:36
 */

public class Saver {
    public static void save(String fileName, ArrayList<Cell> cells) {
        // Получаем домашнюю директорию пользователя
        String userHome = System.getProperty("user.home");
        String directoryPath = userHome + "/Library/Application Support/LogicalCalcFiles/";
        String filePath = directoryPath + fileName + ".lc";

        // Создаем директорию, если она не существует
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new RuntimeException("Cannot create directory: " + directoryPath);
            }
        }

        try (ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            ous.writeObject(cells);
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
            throw new RuntimeException("Failed to save file: " + filePath, e);
        }
    }
}