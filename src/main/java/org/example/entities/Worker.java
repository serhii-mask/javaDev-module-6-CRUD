package org.example.entities;

import java.time.LocalDate;

public class Worker {

    private String name;
    private LocalDate birthday;
    private String level;
    private int salary;

    public Worker(String name, LocalDate birthday, String level, int salary) {
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getLevel() {
        return level;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", level='" + level + '\'' +
                ", salary=" + salary +
                '}';
    }
}
