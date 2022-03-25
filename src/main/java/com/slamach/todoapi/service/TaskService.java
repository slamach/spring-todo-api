package com.slamach.todoapi.service;

import com.slamach.todoapi.dto.TaskDto;
import com.slamach.todoapi.exception.TaskNotFoundException;
import com.slamach.todoapi.model.Task;
import com.slamach.todoapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
  private final TaskRepository taskRepository;

  public List<Task> getTasks() {
    return taskRepository.findAll();
  }

  public Task addTask(TaskDto taskDto) {
    return taskRepository.save(new Task(taskDto));
  }

  public Task updateTask(Long taskId, TaskDto taskDto) {
    Optional<Task> foundTask = taskRepository.findById(taskId);
    if (foundTask.isEmpty()) {
      throw new TaskNotFoundException("No such task found.");
    }

    Task updatedTask = foundTask.get();
    if (taskDto.getContent() != null) {
      updatedTask.setContent(taskDto.getContent());
    }
    if (taskDto.getCompleted() != null) {
      updatedTask.setCompleted(taskDto.getCompleted());
    }

    return taskRepository.save(updatedTask);
  }

  public Task deleteTask(Long taskId) {
    Optional<Task> foundTask = taskRepository.findById(taskId);
    if (foundTask.isEmpty()) {
      throw new TaskNotFoundException("No such task found.");
    }

    taskRepository.deleteById(taskId);
    return foundTask.get();
  }
}
