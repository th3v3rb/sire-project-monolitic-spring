package com.dantesoft.siremono.modules.people.people.actions;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.people.people.store.PersonEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonSaveOutput implements ActionOutputContract {
  private PersonEntity data;
}
