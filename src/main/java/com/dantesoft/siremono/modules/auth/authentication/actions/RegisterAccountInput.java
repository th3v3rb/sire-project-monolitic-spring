package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.CommandInput;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterAccountInput implements CommandInput {
  @NotBlank(message = "El nombre de usuario es necesario")
  public String username;

  @NotBlank(message = "El email es necesario")
  public String email;

  @NotBlank(message = "La contraseña es necesaria")
  public String password;

  @NotBlank(message = "La confirmacion de la contraseña es necesaria")
  public String passwordConfirmation;

  public boolean passwordsMatch() {
    return password.equals(passwordConfirmation);
  }
}
