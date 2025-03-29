package com.dantesoft.siremono.modules.items.brands.actions;

import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Enable brand input",
    description = "The requested arguments of the 'Enable brand input' action")
public class EnableBrandInput implements ActionInputContract {
  private UUID id;
}
