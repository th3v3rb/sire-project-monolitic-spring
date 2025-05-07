package com.dantesoft.siremono.modules.auth.store.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.dantesoft.siremono.modules.auth.store.entity.RoleEntity;

public class RoleMapper {

    public static RoleDTO map(RoleEntity entity) {
        var dto = new RoleDTO();
        var permissions = PermissionMapper.mapAll(entity.getPermissions());

        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setPermissions(permissions);
        return dto;
    }

    public static List<RoleDTO> mapAll(Set<RoleEntity> roles) {
        return roles.stream()
                .map(RoleMapper::map)
                .collect(Collectors.toList());
    }

}
