package com.baktiyar.service;

import java.util.List;

import com.baktiyar.models.Status;

public interface Service<Task> {
    void add(Task task);
    void update(Task task);
    boolean delete(int taskId);
    Task getByID(int taskId);
    List<Task> getAll();
    List<Task> getByStatus(Status status);
}
