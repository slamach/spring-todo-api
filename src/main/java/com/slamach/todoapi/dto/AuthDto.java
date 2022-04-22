package com.slamach.todoapi.dto;

import com.slamach.todoapi.util.RegisterValidationGroup;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AuthDto {
  @Size(
      groups = {RegisterValidationGroup.class},
      min = 4,
      message = "Username should contain at least 4 characters."
  )
  private String username;
  @Size(
      groups = {RegisterValidationGroup.class},
      min = 8,
      message = "Password should contain at least 8 characters."
  )
  private String password;
}
