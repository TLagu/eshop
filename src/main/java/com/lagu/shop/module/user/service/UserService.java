package com.lagu.shop.module.user.service;

import com.lagu.shop.core.pagination.ListResponse;
import com.lagu.shop.module.user.dto.UserDto;
import com.lagu.shop.module.user.dto.UserForm;
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

    public UserDto getByEmail(String email) {
        return UserMapper.map(userRepository.findByEmail(email));
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

    public UserDto getByUuid(String uuid) {
        return UserMapper.map(userRepository.getByUuid(uuid));
    }

    public UserDto createOrUpdate(UserForm user, BCryptPasswordEncoder encoder) {
        return (user.isNew()) ? create(user, encoder) : update(user.getUuid(), user, encoder);
    }

    public void delete(String uuid) {
        UserEntity entity = userRepository.getByUuid(uuid);
        userRepository.delete(entity);
    }

    private UserDto create(UserForm userForm, BCryptPasswordEncoder encoder) {
        UserRole userRole = UserRole.valueOf(userForm.getRole());
        UserEntity bookEntity = UserFormMapper.map(userForm, userRole, encoder);
        UserEntity bookUpdate = userRepository.saveAndFlush(bookEntity);
        return UserMapper.map(bookUpdate);
    }

    private UserDto update(String uuid, UserForm userForm, BCryptPasswordEncoder encoder) {
        UserRole userRole = UserRole.valueOf(userForm.getRole());
        UserEntity user = userRepository.getByUuid(uuid)
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
        UserEntity userUpdated = userRepository.saveAndFlush(user);
        return UserMapper.map(userUpdated);
    }

}
