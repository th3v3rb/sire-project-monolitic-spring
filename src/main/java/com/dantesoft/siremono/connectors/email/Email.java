package com.dantesoft.siremono.connectors.email;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {
  private String to;
  private List<String> cc;
  private List<String> bcc;
  private String subject;
  private String body;
  private List<Attachment> attachments;

}
