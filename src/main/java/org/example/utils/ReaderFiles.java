package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderFiles {
    public static String readSqlFile(String path) {
        try  {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
            return "The path is wrong!";
        }
    }
}
