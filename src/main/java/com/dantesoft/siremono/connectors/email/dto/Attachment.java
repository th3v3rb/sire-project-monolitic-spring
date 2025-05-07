package com.dantesoft.siremono.connectors.email.dto;

import java.nio.ByteBuffer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attachment {
  private String filename;
  private String contentType;
  private ByteBuffer content;
}
