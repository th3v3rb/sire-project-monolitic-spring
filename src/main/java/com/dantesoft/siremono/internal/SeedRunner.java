package com.dantesoft.siremono.internal;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedRunner {
  @Bean
  CommandLineRunner runSeeders(List<SeedCommand> databaseSeeders) {
    return _ -> {
      for (var c : databaseSeeders) {
        c.seed();
      }
    };
  }
}