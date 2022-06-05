package com.lagu.shop.module.user.mapper;

import com.lagu.shop.module.product.entity.Status;
import com.lagu.shop.module.user.dto.UserForm;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.entity.UserRole;

import java.util.UUID;

public class UserFormMapper {

    public static UserEntity map(UserForm form) {
        return new UserEntity()
                .setStatus(Status.ACTIVE)
                .setUuid(UUID.randomUUID().toString())
                .setRole(UserRole.USER);
    }

}
