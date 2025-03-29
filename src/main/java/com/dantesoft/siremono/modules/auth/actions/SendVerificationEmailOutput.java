package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendVerificationEmailOutput implements ActionOutputContract {
  private String message;
}
