package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetUserStatusInput implements ActionInputContract {
  private String jwt;
}
