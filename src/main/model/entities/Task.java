package main.model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import main.model.enums.TaskStatus;

public class Task {
    
    private Long id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task() {
    }

    public Task(Long id, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("Id da tarefa: ").append(this.id).append("\n");
        sb.append("Descrição da tarefa: ").append(this.description).append("\n");
        if(this.status == TaskStatus.TODO) sb.append("Status da tarefa: ").append("A fazer").append("\n");
        if(this.status == TaskStatus.IN_PROGRESS) sb.append("Status da tarefa: ").append("Em progresso").append("\n");
        if(this.status == TaskStatus.DONE) sb.append("Status da tarefa: ").append("Concluído").append("\n");
        sb.append("Criado em: ").append(dtf.format(createdAt)).append("\n");
        sb.append("Atualizado em: ").append(dtf.format(updatedAt)).append("\n");
        return sb.toString();
    }

}
