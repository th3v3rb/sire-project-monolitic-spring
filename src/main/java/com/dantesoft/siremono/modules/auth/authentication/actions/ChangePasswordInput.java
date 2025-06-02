package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordInput implements CommandInput {
  @NotBlank
  private String currentPassword;
  @NotBlank
  @Size(min = 8)
  private String updatedPassword;
  @NotBlank
  @Size(min = 8)
  private String confirmationPassword;

}
