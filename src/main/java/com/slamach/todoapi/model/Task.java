package com.slamach.todoapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "todo_task")
public class Task {
  @Id
  @SequenceGenerator(name = "todo_task_id_seq", sequenceName = "todo_task_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_task_id_seq")
  private Long id;
  @Column(nullable = false)
  private String content;
  @Column(nullable = false)
  private Boolean completed;
  @ManyToOne
  @JoinColumn(nullable = false)
  @JsonIgnore
  private User owner;

  public Task(String content, Boolean completed, User owner) {
    this.content = content;
    this.completed = completed;
    this.owner = owner;
  }
}
