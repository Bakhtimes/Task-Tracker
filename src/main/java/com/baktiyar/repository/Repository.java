package com.baktiyar.repository;

import java.util.List;

import com.baktiyar.models.Status;

public interface Repository<Task> {
    void add(Task task);
    Task getByID(int taskID);
    List<Task> getAll();
    List<Task> getByStatus(Status status);
    void update(Task task);
    boolean delete(int taskID);
}
