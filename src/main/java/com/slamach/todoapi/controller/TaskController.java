package com.slamach.todoapi.controller;

import com.slamach.todoapi.dto.TaskDto;
import com.slamach.todoapi.model.Task;
import com.slamach.todoapi.service.TaskService;
import com.slamach.todoapi.util.AddTaskValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {
  private final TaskService taskService;

  @GetMapping
  public List<Task> getUserTasks(Principal principal) {
    return taskService.getUserTasks(principal.getName());
  }

  @PostMapping
  public Task addTask(@Validated({AddTaskValidationGroup.class}) @RequestBody TaskDto taskDto, Principal principal) {
    return taskService.addTask(taskDto, principal.getName());
  }

  @PutMapping("/{taskId}")
  public Task updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto, Principal principal) {
    return taskService.updateTask(taskId, taskDto, principal.getName());
  }

  @DeleteMapping("/{taskId}")
  public Task deleteTask(@PathVariable Long taskId, Principal principal) {
    return taskService.deleteTask(taskId, principal.getName());
  }
}
