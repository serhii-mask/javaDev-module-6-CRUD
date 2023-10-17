package org.example;

import org.example.services.DatabaseInitService;
import org.example.services.DatabasePopulateService;
import org.example.services.DatabaseQueryService;
import org.example.database.Database;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseInitService.main(args);
        DatabasePopulateService.main(args);

        Database database = Database.getInstance();
        DatabaseQueryService queryService = new DatabaseQueryService(database);

        System.out.println("FindMaxSalaryWorker() = " + queryService.findMaxSalaryWorker());
        System.out.println("FindMaxProjectsClient() = " + queryService.findMaxProjectsClient());
        System.out.println("FindLongestProject() = " + queryService.findLongestProject());
        System.out.println("FindYoungestEldestWorkers() = " + queryService.findYoungestEldestWorkers());
        System.out.println("PrintProjectPrices() = " + queryService.printProjectPrices());

        database.close();
    }
}