package com.slamach.todoapi.service;

import com.slamach.todoapi.dto.AuthDto;
import com.slamach.todoapi.exception.AuthenticationException;
import com.slamach.todoapi.exception.UserAlreadyExistsException;
import com.slamach.todoapi.model.User;
import com.slamach.todoapi.repository.UserRepository;
import com.slamach.todoapi.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  public String loginUser(AuthDto authDto) {
    try {
      User user = (User) loadUserByUsername(authDto.getUsername());
      if (!passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
        throw new AuthenticationException("Invalid password.");
      } else {
        return jwtTokenProvider.createToken(authDto.getUsername());
      }
    } catch (UsernameNotFoundException | AuthenticationException exception) {
      throw new AuthenticationException("Invalid username or password.");
    }
  }

  public String registerUser(AuthDto authDto) {
    if (userRepository.existsByUsername(authDto.getUsername())) {
      throw new UserAlreadyExistsException("User already exists.");
    }
    userRepository.save(new User(
        authDto.getUsername(),
        passwordEncoder.encode(authDto.getPassword())
    ));
    return jwtTokenProvider.createToken(authDto.getUsername());
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> foundUser = userRepository.findByUsername(username);
    if (foundUser.isEmpty()) {
      throw new UsernameNotFoundException("Username not found.");
    }
    return foundUser.get();
  }
}
