package com.slamach.todoapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
  private long id;
  private String content;
  private boolean completed;
  private User owner;
}
