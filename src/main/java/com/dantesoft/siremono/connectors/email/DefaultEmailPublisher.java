package com.dantesoft.siremono.connectors.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.dantesoft.siremono.connectors.email.dto.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultEmailPublisher implements EmailAdapter {

  private final JavaMailSender mailSender;
  @Value("${spring.mail.username}")
  private String appEmailAddress;

  public void send(Email email) {
    try {
      log.info("Sending email to {} from {}", email.getTo(), appEmailAddress);
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper;
      helper = new MimeMessageHelper(mimeMessage, true);

      helper.setFrom(appEmailAddress);
      helper.setTo(email.getTo());
      helper.setText(email.getBody(), true);
      helper.setSubject(email.getSubject());
      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      log.error("Error trying send message: {}", e);
    }
  }

}
