package com.slamach.todoapi.dto;

import com.slamach.todoapi.util.AddTaskValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TaskDto {
  @NotBlank(
      groups = {AddTaskValidationGroup.class},
      message = "Task content cannot be empty."
  )
  private String content;
  @NotNull(
      groups = {AddTaskValidationGroup.class},
      message = "Task completed status cannot be empty."
  )
  private Boolean completed;
}
