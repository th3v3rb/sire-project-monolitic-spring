package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.internal.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserOutput implements ActionOutputContract {
  private String message;
  private String token;
  private UserDto user;
}
