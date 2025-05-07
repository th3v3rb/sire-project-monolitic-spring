package com.dantesoft.siremono.modules.auth.store.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.dantesoft.siremono.modules.auth.store.entity.PermissionEntity;

public class PermissionMapper {

    public static PermissionDTO map(PermissionEntity entity) {
        var dto = new PermissionDTO();
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        return dto;
    }

    public static List<PermissionDTO> mapAll(Set<PermissionEntity> list) {
        return list.stream()
                .map(PermissionMapper::map)
                .collect(Collectors.toList());
    }

}
