package com.slamach.todoapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("To Do API (Spring)")
            .description("Spring learning project.")
            .version("v0.0.1")
            .contact(new Contact()
                .name("Dmitry Sviridov")
                .email("sviridov.dvv@gmail.com")
                .url("https://dmitrysviridov.com")));
  }
}
