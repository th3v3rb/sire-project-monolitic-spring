package com.dantesoft.siremono.modules.profile.actions;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.profile.store.dto.UserInformationDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileFindOutput extends AbstractOutput<UserInformationDTO> {
}
