package com.slamach.todoapi.controller;

import com.slamach.todoapi.dto.TaskDto;
import com.slamach.todoapi.model.Task;
import com.slamach.todoapi.service.TaskService;
import com.slamach.todoapi.util.AddTaskValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {
  private final TaskService taskService;

  @GetMapping
  public List<Task> getTasks() {
    return taskService.getTasks();
  }

  @PostMapping
  public Task addTask(@Validated({AddTaskValidationGroup.class}) @RequestBody TaskDto taskDto) {
    return taskService.addTask(taskDto);
  }

  @PutMapping("/{taskId}")
  public Task updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
    return taskService.updateTask(taskId, taskDto);
  }

  @DeleteMapping("/{taskId}")
  public Task deleteTask(@PathVariable Long taskId) {
    return taskService.deleteTask(taskId);
  }
}
