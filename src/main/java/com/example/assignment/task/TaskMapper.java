package com.example.assignment.task;

import com.example.assignment.user.User;

public class TaskMapper {

    public static Task toEntity(TaskRequestDTO dto, User user) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setUser(user);
        return task;
    }

    public static TaskResponseDTO toDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getUser().getEmail()
        );
    }
}
