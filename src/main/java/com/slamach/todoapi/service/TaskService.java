package com.slamach.todoapi.service;

import com.slamach.todoapi.dto.TaskDto;
import com.slamach.todoapi.exception.PermissionDeniedException;
import com.slamach.todoapi.exception.TaskNotFoundException;
import com.slamach.todoapi.model.Task;
import com.slamach.todoapi.model.User;
import com.slamach.todoapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
  private final TaskRepository taskRepository;
  private final UserService userService;

  public List<Task> getUserTasks(String username) {
    User user = (User) userService.loadUserByUsername(username);
    return taskRepository.findByOwner(user).get();
  }

  public Task addTask(TaskDto taskDto, String username) {
    User user = (User) userService.loadUserByUsername(username);
    return taskRepository.save(new Task(
        taskDto.getContent(),
        taskDto.getCompleted(),
        user
    ));
  }

  public Task updateTask(Long taskId, TaskDto taskDto, String username) {
    Optional<Task> foundTask = taskRepository.findById(taskId);
    if (foundTask.isEmpty()) {
      throw new TaskNotFoundException("No such task found.");
    }

    Task updatedTask = foundTask.get();
    User user = (User) userService.loadUserByUsername(username);
    if (!user.getUsername().equals(updatedTask.getOwner().getUsername())) {
      throw new PermissionDeniedException("You are not allowed to modify this task.");
    }

    if (taskDto.getContent() != null) {
      updatedTask.setContent(taskDto.getContent());
    }
    if (taskDto.getCompleted() != null) {
      updatedTask.setCompleted(taskDto.getCompleted());
    }

    return taskRepository.save(updatedTask);
  }

  public Task deleteTask(Long taskId, String username) {
    Optional<Task> foundTask = taskRepository.findById(taskId);
    if (foundTask.isEmpty()) {
      throw new TaskNotFoundException("No such task found.");
    }

    Task deletingTask = foundTask.get();
    User user = (User) userService.loadUserByUsername(username);
    if (!user.getUsername().equals(deletingTask.getOwner().getUsername())) {
      throw new PermissionDeniedException("You are not allowed to delete this task.");
    }

    taskRepository.deleteById(taskId);
    return deletingTask;
  }
}
