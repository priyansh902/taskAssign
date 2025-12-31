package com.example.assignment.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment.user.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);
}

