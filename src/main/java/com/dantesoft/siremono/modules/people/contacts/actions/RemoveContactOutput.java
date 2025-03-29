package com.dantesoft.siremono.modules.people.contacts.actions;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.people.contacts.store.ContactEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RemoveContactOutput implements ActionOutputContract {
  private ContactEntity data;
}
