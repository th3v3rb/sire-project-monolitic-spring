package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordInput implements ActionInputContract {

  @NotBlank(message = "El password es requerido")
  @Size(min = 8, message = "El mínimo de caracteres es 8")
  private String password;

  @NotBlank(message = "La confirmación de password es necesaria")
  @Size(min = 8, message = "El mínimo de caracteres es 8")
  private String passwordConfirmation;

  // hidden field
  private String token;

  public boolean isPasswordMatch() {
    return this.password.equals(passwordConfirmation);
  }
}
