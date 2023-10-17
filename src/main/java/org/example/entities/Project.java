package org.example.entities;

import java.time.LocalDate;

public class Project {
    private int clientId;
    private LocalDate startDate;
    private LocalDate finishDate;

    public Project(int clientId, LocalDate startDate, LocalDate finishDate) {
        this.clientId = clientId;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getClientId() {
        return clientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "clientId=" + clientId +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
