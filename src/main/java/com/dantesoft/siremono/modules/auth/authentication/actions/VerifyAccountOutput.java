package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyAccountOutput implements CommandOutput {
  String message;
}
