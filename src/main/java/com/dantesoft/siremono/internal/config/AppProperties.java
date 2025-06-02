package com.dantesoft.siremono.internal.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(
    prefix = "internal")
@Data
public class AppProperties {
  private String[] whiteListedEndpoints;
  private Front front;
  private Token token;
  private JWT jwt;
  private Storage storage;

  public record Storage(String endpoint, String accessKey, String secretKey, String itemsBucket,
                        String profileImagesBucket) {
  }

  public record JWT(String secret, String expiration) {
  }

  public record Front(String emailVerificationUrl, String passwordResetUrl) {
  }

  public record Token(long expiration) {
  }

}
