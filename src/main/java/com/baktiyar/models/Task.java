package com.baktiyar.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
    public static int id = 0;
    private int taskID;
    private String description;
    private Status status;
    private final LocalDate createdAt;
    private LocalDate updatedAt;

    @JsonCreator
    public Task(@JsonProperty("taskID") int taskID,
                @JsonProperty("description") String description,
                @JsonProperty("status") Status status,
                @JsonProperty("createdAt") LocalDate createdAt,
                @JsonProperty("updatedAt") LocalDate updatedAt) {
        id = taskID;
        this.taskID = taskID;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Task(String description, Status status) {
        id++;
        this.taskID = id;
        this.description = description;
        this.status = status;
        this.createdAt = LocalDate.now();
        this.updatedAt = null;
    }

    public int getTaskID(){
        return this.taskID;
    }

    public void setTaskID(int taskID){
        this.taskID = taskID;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public LocalDate getCreatedAt(){
        return this.createdAt;
    }

    public LocalDate getUpdatedAt(){
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Task.class.getSimpleName() + "[", "]")
                .add("taskID=" + taskID)
                .add("description='" + description + "'")
                .add("status=" + status)
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Task)) return false;
        Task task = (Task) obj;
        return task.getTaskID() == this.getTaskID() && Objects.equals(task.getDescription(), this.getDescription()) && task.getStatus() == this.getStatus() && Objects.equals(task.getCreatedAt(), this.getCreatedAt()) && Objects.equals(task.getUpdatedAt(), this.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTaskID(), this.getDescription(), this.getStatus(), this.getCreatedAt(), this.getUpdatedAt());
    }
}
