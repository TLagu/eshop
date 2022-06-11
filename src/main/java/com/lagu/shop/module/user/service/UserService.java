package com.lagu.shop.module.user.service;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.core.util.ControllerTools;
import com.lagu.shop.module.user.dto.UserDto;
import com.lagu.shop.module.user.dto.UserForm;
import com.lagu.shop.module.user.entity.ContactType;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.entity.UserRole;
import com.lagu.shop.module.user.mapper.UserFormMapper;
import com.lagu.shop.module.user.mapper.UserMapper;
import com.lagu.shop.module.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ListResponse<UserDto> getAllPerPage(int page, int size) {
        Page<UserEntity> pageProduct = userRepository.findAll(PageRequest.of(page, size));
        return new ListResponse<>(
                UserMapper.map(pageProduct.getContent()),
                pageProduct.getTotalPages(),
                pageProduct.getTotalElements(),
                size,
                page
        );
    }

    public UserDto getDtoByUuid(String uuid) {
        return UserMapper.map(userRepository.getByUuid(uuid));
    }

    public UserForm getFormByUuid(String uuid) {
        return UserFormMapper.map(userRepository.getByUuid(uuid));
    }

    public UserDto createOrUpdate(UserForm user, BCryptPasswordEncoder encoder) {
        return (user.isNew()) ? create(user, encoder) : update(user, encoder);
    }

    public void delete(String uuid) {
        UserEntity entity = userRepository.getByUuid(uuid);
        userRepository.delete(entity);
    }

    private UserDto create(UserForm userForm, BCryptPasswordEncoder encoder) {
        UserRole userRole = UserRole.valueOf(userForm.getRole());
        ContactType contact = ContactType.valueOf(userForm.getContact());
        UserEntity userEntity = UserFormMapper.map(userForm, userRole, contact, encoder);
        UserEntity userUpdate = userRepository.saveAndFlush(userEntity);
        return UserMapper.map(userUpdate);
    }

    private UserDto update(UserForm userForm, BCryptPasswordEncoder encoder) {
        UserRole userRole = ControllerTools.setEnumValue(UserRole.values(), UserRole.USER, userForm.getRole());
        ContactType contact = ControllerTools.setEnumValue(ContactType.values(), ContactType.EMAIL, userForm.getContact());
        UserEntity userEntity = userRepository.getByUuid(userForm.getUuid())
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
        UserEntity userUpdated = userRepository.saveAndFlush(userEntity);
        return UserMapper.map(userUpdated);
    }

    public UserDto getDtoByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return UserMapper.map(userEntity);
    }

    public UserForm getFormByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return UserFormMapper.map(userEntity);
    }

    public UserEntity save(UserForm userForm, ContactType contact, BCryptPasswordEncoder encoder) {
        UserEntity userEntity = UserFormMapper.map(userForm, UserRole.USER, contact, encoder);
        return userRepository.saveAndFlush(userEntity);
    }

}
