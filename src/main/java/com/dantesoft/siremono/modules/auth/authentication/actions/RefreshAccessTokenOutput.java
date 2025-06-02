package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import lombok.Data;

@Data
public class RefreshAccessTokenOutput implements CommandOutput {
  private String jwt;
}
