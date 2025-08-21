package com.dkmasteer.logicalcalculator.model.io;

import java.io.File;

/**
 * @author daniilkrasnov
 * @date 21.08.2025 09:59
 */

public class FileUtils {

    public static String getAppDataDirectory() {
        String userHome = System.getProperty("user.home");
        return userHome + "/Library/Application Support/LogicalCalcFiles/";
    }

    public static String getFullFilePath(String fileName) {
        if (!fileName.toLowerCase().endsWith(".lc")) {
            fileName += ".lc";
        }
        return getAppDataDirectory() + fileName;
    }

    public static boolean fileExists(String fileName) {
        File file = new File(getFullFilePath(fileName));
        return file.exists() && file.isFile();
    }

    public static String[] getAvailableFiles() {
        File directory = new File(getAppDataDirectory());
        if (directory.exists() && directory.isDirectory()) {
            return directory.list((dir, name) -> name.toLowerCase().endsWith(".lc"));
        }
        return new String[0];
    }
}