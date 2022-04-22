package com.slamach.todoapi.repository;

import com.slamach.todoapi.model.Task;
import com.slamach.todoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  Optional<List<Task>> findByOwner(User user);
}
