package com.baktiyar.repository;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.baktiyar.models.Status;
import com.baktiyar.models.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TaskRepository implements Repository<Task> {
    
    private List<Task> repository;
    private final ObjectMapper mapper;
    private final File file;

    public TaskRepository() {
        this.repository = new LinkedList<>();
        this.file = new File("tasks.json");
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        verifyFile();
        loadTasks();
    }

    @Override
    public void add(Task task) {
        repository.add(task);
        saveTasks();
    }

    @Override
    public void update(Task task) {
        this.repository.stream()
                .filter(t -> t.getTaskID() == task.getTaskID())
                .forEach(t -> {
                    t.setDescription(task.getDescription());
                    t.setStatus(task.getStatus());
                    t.setUpdatedAt(task.getUpdatedAt());
                });
        saveTasks();
    }
    
    @Override
    public boolean delete(int taskID) {
        boolean status = this.repository.removeIf(t -> t.getTaskID() == taskID);
        if (status) {
            saveTasks();
            Task.id--;
        }
        return status;
    }

    @Override
    public Task getByID(int taskID) {
        return this.repository.stream()
                .filter(t -> t.getTaskID() == taskID)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> getAll() {
        return this.repository;
    }

    @Override
    public List<Task> getByStatus(Status status) {
        return this.repository.stream()
                .filter(t -> t.getStatus().equals(status))
                .toList();
    }

    private void verifyFile() {
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadTasks() {
        try {
            this.repository = mapper.readValue(this.file, new TypeReference<List<Task>>() {});
        } catch (JsonProcessingException e) {
            //System.out.println("Error reading tasks from file. " + e.getMessage());
        } catch (IOException e) {
            //System.out.println("Error reading tasks from file. " + e.getMessage());
        }
    }

    private void saveTasks() {
        try {
            mapper.writeValue(this.file, this.repository);
        } catch (JsonProcessingException e) {
            //System.out.println("Error saving tasks to file. " + e.getMessage());
        } catch (IOException e) {
            //System.out.println("Error saving tasks to file. " + e.getMessage());
        }
    }
}
