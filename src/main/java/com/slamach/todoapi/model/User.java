package com.slamach.todoapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "todo_user")
public class User {
  @Id
  @SequenceGenerator(name = "todo_user_id_seq", sequenceName = "todo_user_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_user_id_seq")
  private Long id;
  @Column(unique = true, nullable = false)
  private String username;
  @Column(nullable = false)
  @JsonIgnore
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private String password;
}
