package com.slamach.todoapi.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AuthDto {
  @Size(min = 4, message = "Username should contain at least 4 characters.")
  private String username;
  @Size(min = 8, message = "Password should contain at least 8 characters.")
  private String password;
}
