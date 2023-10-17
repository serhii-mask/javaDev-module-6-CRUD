package org.example.services;

import org.example.database.Database;
import org.example.entities.Client;
import org.example.entities.Project;
import org.example.entities.Worker;
import org.example.repository.ClientRepository;
import org.example.repository.ProjectRepository;
import org.example.repository.ProjectWorkerRepository;
import org.example.repository.WorkerRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabasePopulateService {

    public static void main(String[] args) throws SQLException {
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("Martin Doe", LocalDate.parse("2004-04-28"), "Trainee", 300));
        workers.add(new Worker("Bob Smith", LocalDate.parse("2005-06-12"), "Trainee", 300));
        workers.add(new Worker("Andrii Johnson", LocalDate.parse("2002-10-22"), "Junior", 700));
        workers.add(new Worker("Liza Brown", LocalDate.parse("2001-04-08"), "Junior", 600));
        workers.add(new Worker("Oleksandra Wilson", LocalDate.parse("1997-11-12"), "Middle", 1500));
        workers.add(new Worker("Max Lee", LocalDate.parse("1999-09-25"), "Middle", 1200));
        workers.add(new Worker("Serhii Wilson", LocalDate.parse("1996-07-18"), "Middle", 2900));
        workers.add(new Worker("Stiven Davis", LocalDate.parse("1998-03-05"), "Middle", 2200));
        workers.add(new Worker("Ilon Miller", LocalDate.parse("1995-12-30"), "Middle", 2600));
        workers.add(new Worker("Stepan Anderson", LocalDate.parse("1994-08-10"), "Senior", 3700));
        workers.add(new Worker("Ivat Johnson", LocalDate.parse("1992-05-20"), "Senior", 5500));
        workers.add(new Worker("Petro Davis", LocalDate.parse("1991-01-15"), "Senior", 4600));

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("John Kozak"));
        clients.add(new Client("Mary Giga"));
        clients.add(new Client("Robert Yavorenko"));
        clients.add(new Client("Alice Ukrainka"));
        clients.add(new Client("Michael Petliyra"));
        clients.add(new Client("Emma Chubrynets"));
        clients.add(new Client("David Markivna"));

        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, LocalDate.parse("2023-01-01"), LocalDate.parse("2024-02-28")));
        projects.add(new Project(1, LocalDate.parse("2023-02-15"), LocalDate.parse("2023-05-15")));
        projects.add(new Project(2, LocalDate.parse("2023-03-10"), LocalDate.parse("2025-06-10")));
        projects.add(new Project(3, LocalDate.parse("2023-04-05"), LocalDate.parse("2024-07-05")));
        projects.add(new Project(3, LocalDate.parse("2023-05-20"), LocalDate.parse("2025-10-20")));
        projects.add(new Project(3, LocalDate.parse("2023-06-15"), LocalDate.parse("2023-07-15")));
        projects.add(new Project(4, LocalDate.parse("2023-07-01"), LocalDate.parse("2024-11-30")));
        projects.add(new Project(5, LocalDate.parse("2023-08-10"), LocalDate.parse("2026-08-20")));
        projects.add(new Project(6, LocalDate.parse("2023-09-05"), LocalDate.parse("2024-09-05")));
        projects.add(new Project(6, LocalDate.parse("2023-10-01"), LocalDate.parse("2023-11-30")));
        projects.add(new Project(7, LocalDate.parse("2023-09-01"), LocalDate.parse("2025-01-30")));

        Connection connection = Database.getInstance().getConnection();
        WorkerRepository workerRepository = new WorkerRepository(connection);
        ClientRepository clientRepository = new ClientRepository(connection);
        ProjectRepository projectRepository = new ProjectRepository(connection);
        ProjectWorkerRepository projectWorkerRepository = new ProjectWorkerRepository(connection);

        for (Worker worker : workers) {
            workerRepository.saveWorker(worker);
        }
        workerRepository.batchSaveWorkers(workers);

        for (Client client : clients) {
            clientRepository.saveClient(client);
        }

        for (Project project : projects) {
            projectRepository.saveProject(project);
        }

        projectWorkerRepository.saveProjectWorker(1, 1);
        projectWorkerRepository.saveProjectWorker(1, 3);
        projectWorkerRepository.saveProjectWorker(1, 5);
        projectWorkerRepository.saveProjectWorker(1, 10);
        projectWorkerRepository.saveProjectWorker(2, 3);
        projectWorkerRepository.saveProjectWorker(2, 6);
        projectWorkerRepository.saveProjectWorker(2, 12);
        projectWorkerRepository.saveProjectWorker(3, 7);
        projectWorkerRepository.saveProjectWorker(3, 11);
        projectWorkerRepository.saveProjectWorker(4, 2);
        projectWorkerRepository.saveProjectWorker(4, 4);
        projectWorkerRepository.saveProjectWorker(4, 11);
        projectWorkerRepository.saveProjectWorker(5, 10);
        projectWorkerRepository.saveProjectWorker(6, 4);
        projectWorkerRepository.saveProjectWorker(6, 9);
        projectWorkerRepository.saveProjectWorker(7, 1);
        projectWorkerRepository.saveProjectWorker(7, 2);
        projectWorkerRepository.saveProjectWorker(7, 8);
        projectWorkerRepository.saveProjectWorker(8, 3);
        projectWorkerRepository.saveProjectWorker(8, 1);
        projectWorkerRepository.saveProjectWorker(8, 7);
        projectWorkerRepository.saveProjectWorker(8, 10);
        projectWorkerRepository.saveProjectWorker(9, 4);
        projectWorkerRepository.saveProjectWorker(9, 9);
        projectWorkerRepository.saveProjectWorker(10, 2);
        projectWorkerRepository.saveProjectWorker(10, 5);
        projectWorkerRepository.saveProjectWorker(10, 8);
        projectWorkerRepository.saveProjectWorker(11, 12);
    }
}