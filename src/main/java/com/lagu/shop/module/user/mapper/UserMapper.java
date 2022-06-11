package com.lagu.shop.module.user.mapper;

import com.lagu.shop.module.user.dto.UserDto;
import com.lagu.shop.module.user.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto map(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserDto()
                .setUuid(entity.getUuid())
                .setStatus(entity.getStatus())
                .setEmail(entity.getEmail())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setRole(entity.getRole())
                .setLongitude(entity.getLongitude())
                .setLatitude(entity.getLatitude())
                .setCountry(entity.getCountry())
                .setCity(entity.getCity())
                .setPostCode(entity.getPostCode())
                .setPost(entity.getPost())
                .setStreet(entity.getStreet())
                .setContact(entity.getContact());
    }

    public static List<UserDto> map(List<UserEntity> entities) {
        return entities.stream()
                .map(UserMapper::map)
                .collect(Collectors.toList());
    }

}
