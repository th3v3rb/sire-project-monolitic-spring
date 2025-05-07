package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.commands.CommandOutput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserOutput implements CommandOutput {
	private String token;
}
