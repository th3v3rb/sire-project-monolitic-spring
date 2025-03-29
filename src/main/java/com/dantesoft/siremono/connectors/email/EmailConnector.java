package com.dantesoft.siremono.connectors.email;

import jakarta.mail.MessagingException;

public interface EmailConnector {
  
  void send(Email email) throws MessagingException;
  
}
