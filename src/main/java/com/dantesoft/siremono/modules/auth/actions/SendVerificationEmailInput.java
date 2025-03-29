package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendVerificationEmailInput implements ActionInputContract {
  @NotEmpty(message = "El email no puede estar vacio")
  @NotBlank(message = "El email es requerido")
  @Email(message = "El formato proveido debe ser un correo")
  private String email;
}
