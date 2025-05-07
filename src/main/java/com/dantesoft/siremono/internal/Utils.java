package com.dantesoft.siremono.internal;

import java.util.Base64;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

//@formatter:off
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

  public static ParsedBase64 parseBase64(String base64) {
    try {
      var parts = base64.split(",", 2);
      if (parts.length != 2) throw new IllegalArgumentException("Base64 mal formado");

      var meta = parts[0];
      var payload = parts[1];
      var contentType = meta.split(":")[1].split(";")[0];

      var data = Base64.getDecoder().decode(payload);
      return new ParsedBase64(contentType, data);
  } catch (Exception e) {
      throw new IllegalArgumentException("Error al parsear Base64", e);
  }
  }
  
  public record ParsedBase64(String contentType, byte[] payload) {
  }
}

