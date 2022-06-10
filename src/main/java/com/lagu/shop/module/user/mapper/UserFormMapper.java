package com.lagu.shop.module.user.mapper;

import com.lagu.shop.module.product.entity.Status;
import com.lagu.shop.module.user.dto.UserForm;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.entity.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class UserFormMapper {

    public static UserEntity map(UserForm userForm, UserRole userRole, BCryptPasswordEncoder encoder) {
        UserEntity userEntity = new UserEntity()
                .setStatus(Status.ACTIVE)
                .setUuid(UUID.randomUUID().toString())
                .setEmail(userForm.getEmail())
                .setFirstName(userForm.getFirstName())
                .setLastName(userForm.getLastName())
                .setRole(userRole)
                .setLongitude(userForm.getLongitude())
                .setLatitude(userForm.getLatitude())
                .setCountry(userForm.getCountry())
                .setCity(userForm.getCity())
                .setPostCode(userForm.getPostCode())
                .setPost(userForm.getPost())
                .setStreet(userForm.getStreet());
        if (userForm.getPassword() != null) {
            String encodedPassword = encoder.encode(userEntity.getPassword());
            userEntity.setPassword(encodedPassword);
        }
        return userEntity;
    }

}
