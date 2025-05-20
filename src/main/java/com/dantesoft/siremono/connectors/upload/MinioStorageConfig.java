package com.dantesoft.siremono.connectors.upload;

import com.dantesoft.siremono.internal.config.AppProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioStorageConfig {
  private final AppProperties app;

  @Bean
  MinioClient minioClient() {
    var conf = app.getStorage();
    return MinioClient.builder()
        .endpoint(conf.endpoint())
        .credentials(conf.accessKey(), conf.secretKey())
        .build();
  }
}
