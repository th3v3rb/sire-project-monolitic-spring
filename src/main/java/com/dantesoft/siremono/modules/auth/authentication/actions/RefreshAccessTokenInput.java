package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Data;

@Data
public class RefreshAccessTokenInput implements CommandInput {
  private String refreshToken;

}
