package com.dantesoft.siremono.connectors.email.dto;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class Attachment {
  private String filename;
  private String contentType;
  private ByteBuffer content;
}
