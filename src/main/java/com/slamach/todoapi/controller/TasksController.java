package com.slamach.todoapi.controller;

import com.slamach.todoapi.model.Task;
import com.slamach.todoapi.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TasksController {
  private final TasksService tasksService;

  @GetMapping
  public List<Task> getTasks() {
    return tasksService.getTasks();
  }
}
