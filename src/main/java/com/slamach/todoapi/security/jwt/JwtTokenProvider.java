package com.slamach.todoapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.slamach.todoapi.config.AppConfig;
import com.slamach.todoapi.exception.InvalidJwtTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
  private final AppConfig appConfig;
  private Algorithm algorithm;

  @PostConstruct
  public void init() {
    algorithm = Algorithm.HMAC256(appConfig.getTokenSecret());
  }

  public String createToken(String username) {
    return JWT.create()
        .withPayload(Map.of("username", username))
        .withIssuer(appConfig.getTokenIssuer())
        .withIssuedAt(new Date())
        .withExpiresAt(new Date((new Date()).getTime() + appConfig.getTokenExpirationMSec()))
        .sign(algorithm);
  }

  public String resolveToken(HttpServletRequest httpServletRequest) {
    String headerAuth = httpServletRequest.getHeader("Authorization");
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }
    throw new InvalidJwtTokenException("Invalid authorization token.");
  }

  public String getUsernameFromToken(String token) {
    return JWT.decode(token).getClaim("username").asString();
  }

  public Boolean verifyToken(String token) {
    JWTVerifier jwtVerifier = JWT.require(algorithm)
        .withIssuer(appConfig.getTokenIssuer())
        .build();
    try {
      jwtVerifier.verify(token);
      return true;
    } catch (JWTVerificationException exception) {
      throw new InvalidJwtTokenException("Invalid authorization token.");
    }
  }
}
