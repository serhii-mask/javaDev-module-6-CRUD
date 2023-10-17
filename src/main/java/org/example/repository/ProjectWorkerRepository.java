package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectWorkerRepository {
    private static final String PREPARED_STATEMENT_INSERT = "INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?)";
    private PreparedStatement insertStatement;

    public ProjectWorkerRepository(Connection connection) {
        try {
            this.insertStatement = connection.prepareStatement(PREPARED_STATEMENT_INSERT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveProjectWorker(int projectId, int workerId) {
        try {
            this.insertStatement.setInt(1, projectId);
            this.insertStatement.setInt(2, workerId);
            this.insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
