package com.dantesoft.siremono.internal.database.seeders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeederRunner {
  private final List<DatabaseSeeder> seeders;

  @Async("eventTaskExecutor")
  @EventListener(ApplicationReadyEvent.class)
  public void runSeeders() {
    for (DatabaseSeeder seeder : seeders) {
      try {
        seeder.seed();
      } catch (Exception e) {
        log.error("Seeder failed: {}", seeder.getClass().getSimpleName(), e);
      }
    }
  }
}

