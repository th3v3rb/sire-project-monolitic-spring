package com.dantesoft.siremono.connectors.email.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Email {
  private String to;
  private List<String> cc = new ArrayList<String>();
  private List<String> bcc = new ArrayList<String>();
  private List<Attachment> attachments = new ArrayList<Attachment>();
  private String subject;
  private String body;

}
