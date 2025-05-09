package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisableAccountInput implements CommandInput {
  private String password;

}
