package com.slamach.todoapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppConfig {
  @Value("${app.tokenSecret}")
  private String tokenSecret;

  @Value("${app.tokenIssuer}")
  private String tokenIssuer;

  @Value("${app.tokenExpirationMSec}")
  private Long tokenExpirationMSec;
}
