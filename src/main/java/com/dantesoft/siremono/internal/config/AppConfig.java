package com.dantesoft.siremono.internal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class AppConfig {
  @Bean
  Clock clock() {
    return Clock.systemUTC();
  }
}
