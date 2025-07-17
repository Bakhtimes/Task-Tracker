package com.baktiyar.service;

import java.util.List;

import com.baktiyar.models.Status;
import com.baktiyar.models.Task;
import com.baktiyar.repository.TaskRepository;

public class TaskService implements Service<Task> {
    private final TaskRepository repository;

    public TaskService() {
        this.repository = new TaskRepository();
    }

    @Override
    public void add(Task task) {
        this.repository.add(task);
    }

    @Override
    public void update(Task task) {
        this.repository.update(task);
    }

    @Override
    public boolean delete(int taskID) {
        return this.repository.delete(taskID);
    }

    @Override
    public Task getByID(int taskID) {
        return this.repository.getByID(taskID);
    }

    @Override
    public List<Task> getAll() {
        return this.repository.getAll();
    }

    @Override
    public List<Task> getByStatus(Status status) {
        return this.repository.getByStatus(status);
    }
}
