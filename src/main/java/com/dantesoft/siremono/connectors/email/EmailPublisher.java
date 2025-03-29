package com.dantesoft.siremono.connectors.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class EmailPublisher implements EmailConnector {

  private final JavaMailSender mailSender;
  @Value("${spring.mail.username}")
  private String appEmail;

  public void send(Email email) {
    try {
      log.info("Sending email to {} from {}", email.getTo(), this.appEmail);
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper;
      helper = new MimeMessageHelper(mimeMessage, true);

      helper.setFrom(this.appEmail);
      helper.setTo(email.getTo());
      helper.setText(email.getBody(), true);
      helper.setSubject(email.getSubject());
      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      log.error("Error trying send message: {}", e);
    }
  }

}
