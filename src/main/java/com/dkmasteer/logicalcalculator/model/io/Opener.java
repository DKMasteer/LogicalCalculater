package com.dkmasteer.logicalcalculator.model.io;

import com.dkmasteer.logicalcalculator.model.node.Cell;

import java.io.*;
import java.util.ArrayList;

/**
 * @author daniilkrasnov
 * @date 21.08.2025 09:36
 */

public class Opener {
    public static ArrayList<Cell> open(String fullFileName) {
        if (fullFileName == null || fullFileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        try (ObjectInputStream obs = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(fullFileName)))) {

            Object readObject = obs.readObject();

            if (!(readObject instanceof ArrayList)) {
                throw new IOException("Invalid file format: expected ArrayList");
            }

            ArrayList<?> loadedList = (ArrayList<?>) readObject;
            ArrayList<Cell> result = new ArrayList<>();

            for (Object item : loadedList) {
                if (item instanceof Cell) {
                    result.add((Cell) item);
                } else {
                    throw new IOException("Invalid object type in file: expected Cell objects");
                }
            }

            return result;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fullFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fullFileName, e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found during deserialization", e);
        }
    }
}