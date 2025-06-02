package com.dantesoft.siremono.modules.auth.profile.actions;

import com.dantesoft.siremono.internal.commands.AbstractOutput;
import com.dantesoft.siremono.modules.auth.profile.store.dto.UserInformationDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileFindOutput extends AbstractOutput<UserInformationDTO> {
}
