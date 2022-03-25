package com.slamach.todoapi.model;

import com.slamach.todoapi.dto.TaskDto;
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
//  @ManyToOne
//  @JoinColumn(nullable = false)
//  private User owner;

  public Task(TaskDto taskDto) {
    this.content = taskDto.getContent();
    this.completed = taskDto.getCompleted();
  }
}
