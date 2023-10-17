package org.example.repository;

import org.example.entities.Project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectRepository {
    private static final String PREPARED_STATEMENT_INSERT = "INSERT INTO project (client_id, start_date, finish_date) VALUES (?, ?, ?)";
    private PreparedStatement insertStatement;

    public ProjectRepository(Connection connection) {
        try {
            this.insertStatement = connection.prepareStatement(PREPARED_STATEMENT_INSERT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveProject(Project project) {
        try {
            this.insertStatement.setInt(1, project.getClientId());
            this.insertStatement.setDate(2, Date.valueOf(project.getStartDate()));
            this.insertStatement.setDate(3, Date.valueOf(project.getFinishDate()));
            this.insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
