package com.slamach.todoapi.service;

import com.slamach.todoapi.model.Task;
import com.slamach.todoapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
  public List<Task> getTasks() {
    User user = new User(1L, "slamach", "123");
    return List.of(
        new Task(1L, "Learn Spring :)", true, user),
        new Task(2L, "Create To Do API", false, user)
    );
  }
}
