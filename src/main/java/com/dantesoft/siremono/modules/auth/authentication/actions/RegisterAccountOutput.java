package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@NotBlank
public class RegisterAccountOutput implements CommandOutput {
}
