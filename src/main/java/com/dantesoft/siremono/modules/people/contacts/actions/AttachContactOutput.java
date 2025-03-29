package com.dantesoft.siremono.modules.people.contacts.actions;

import com.dantesoft.siremono.internal.actions.ActionOutputContract;
import com.dantesoft.siremono.modules.people.contacts.store.ContactEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachContactOutput implements ActionOutputContract{
  private ContactEntity data;
}
