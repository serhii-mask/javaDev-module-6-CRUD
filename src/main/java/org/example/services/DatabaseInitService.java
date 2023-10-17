package org.example.services;

import org.example.database.Database;

import static org.example.utils.ReaderFiles.readSqlFile;

public class DatabaseInitService {

    public static void main(String[] args) {
        String path = "./sql/init_db.sql";
        String sql = readSqlFile(path);

        Database.getInstance().initDB(sql);
    }
}