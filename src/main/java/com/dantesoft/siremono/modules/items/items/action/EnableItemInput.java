package com.dantesoft.siremono.modules.items.items.action;

import java.util.UUID;

import com.dantesoft.siremono.internal.actions.ActionInputContract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Enable item input",
    description = "The necessary data for enable a item by the 'Enable item' action")
public class EnableItemInput implements ActionInputContract {
  @Schema(description = "The id of the item to enable")
  private UUID id;
}
