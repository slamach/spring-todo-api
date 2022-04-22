package com.slamach.todoapi.controller;

import com.slamach.todoapi.dto.AuthDto;
import com.slamach.todoapi.service.UserService;
import com.slamach.todoapi.util.RegisterValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserService userService;

  @PostMapping("/login")
  public String login(@RequestBody AuthDto authDto) {
    return userService.loginUser(authDto);
  }

  @PostMapping("/register")
  public String register(@Validated({RegisterValidationGroup.class}) @RequestBody AuthDto authDto) {
    return userService.registerUser(authDto);
  }
}
