package com.example.assignment.task;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.user.User;
import com.example.assignment.user.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tasks")
@Transactional
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(
            @Valid @RequestBody TaskRequestDTO dto,
            Authentication auth
    ) {
        User user = getUserFromAuth(auth);
        Task task = TaskMapper.toEntity(dto, user);
        return ResponseEntity.ok(
                TaskMapper.toDTO(taskRepo.save(task))
        );
    }

    @GetMapping
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> myTasks(Authentication auth) {
        User user = getUserFromAuth(auth);
        return taskRepo.findByUser(user)
                .stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<TaskResponseDTO> allTasks() {
        return taskRepo.findAll()
                .stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    private User getUserFromAuth(Authentication auth) {
        String email = auth.getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("/{id}")
    @Transactional(readOnly = true)
    public TaskResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO dto,
            Authentication auth
    ) {
        User user = getUserFromAuth(auth);
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not your task");
        }

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        return TaskMapper.toDTO(taskRepo.save(task));
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication auth) {
        User user = getUserFromAuth(auth);
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not your task");
        }

        taskRepo.delete(task);
        return ResponseEntity.ok("Task deleted");
    }


}
