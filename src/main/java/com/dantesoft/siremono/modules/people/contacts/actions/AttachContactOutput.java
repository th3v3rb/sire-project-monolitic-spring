package com.dantesoft.siremono.modules.people.contacts.actions;

import com.dantesoft.siremono.internal.commands.CommandOutput;
import com.dantesoft.siremono.modules.people.contacts.store.ContactEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachContactOutput implements CommandOutput{
  private ContactEntity data;
}
