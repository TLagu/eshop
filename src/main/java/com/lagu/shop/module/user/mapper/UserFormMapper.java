package com.lagu.shop.module.user.mapper;

import com.lagu.shop.module.product.entity.Status;
import com.lagu.shop.module.user.dto.UserForm;
import com.lagu.shop.module.user.entity.ContactType;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.entity.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class UserFormMapper {

    public static UserEntity map(UserForm userForm, UserRole userRole, ContactType contact, BCryptPasswordEncoder encoder) {
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
                .setStreet(userForm.getStreet())
                .setContact(contact);
        if (!userForm.getPassword().isEmpty()) {
            String encodedPassword = encoder.encode(userForm.getPassword());
            userEntity.setPassword(encodedPassword);
        }
        return userEntity;
    }

    public static UserForm map (UserEntity entity) {
        return new UserForm()
                .setUuid(entity.getUuid())
                .setEmail(entity.getEmail())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setRole((entity.getRole() == null) ? UserRole.USER.toString() : entity.getRole().toString())
                .setLongitude(entity.getLongitude())
                .setLatitude(entity.getLatitude())
                .setCountry(entity.getCountry())
                .setCity(entity.getCity())
                .setPostCode(entity.getPostCode())
                .setPost(entity.getPost())
                .setStreet(entity.getStreet())
                .setContact((entity.getContact() == null) ? ContactType.EMAIL.toString() : entity.getContact().toString());
    }

}
