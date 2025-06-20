package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendResetPasswordEmailInput implements CommandInput {
  @NotBlank(message = "El email es requerido")
  @Email(message = "El formato de email no es valido")
  private String email;
}
