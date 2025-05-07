package com.dantesoft.siremono.connectors.email;

import com.dantesoft.siremono.connectors.email.dto.Email;

import jakarta.mail.MessagingException;

public interface EmailAdapter {
  
  void send(Email email) throws MessagingException;
  
}
