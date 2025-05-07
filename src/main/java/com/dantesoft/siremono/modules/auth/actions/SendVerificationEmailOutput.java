package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendVerificationEmailOutput implements CommandOutput {
  private String message;
}
