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

    public UserDto getByUuid(String uuid) {
        UserEntity entity = userRepository.getByUuid(uuid);
        return UserMapper.map(entity);
    }

    public void createOrUpdate(UserForm user) {
        if (user.isNew()) {
            create(user);
        } else {
            update(user.getUuid(), user);
        }
    }

    public UserDto create(UserForm user) {
        UserEntity bookEntity = UserFormMapper.map(user);
        UserEntity bookUpdate = userRepository.saveAndFlush(bookEntity);
        return UserMapper.map(bookUpdate);
    }

    public UserDto update(String uuid, UserForm userForm) {
        String role = userForm.getRole();
        UserRole userRole = UserRole.valueOf(role);
        UserEntity user = userRepository.getByUuid(uuid)
                .setEmail(userForm.getEmail())
                .setFirstName(userForm.getFirstName())
                .setLastName(userForm.getLastName())
                .setRole(userRole);
        UserEntity userUpdated = userRepository.saveAndFlush(user);
        return UserMapper.map(userUpdated);
    }

    public void delete(String uuid) {
        UserEntity entity = userRepository.getByUuid(uuid);
        userRepository.delete(entity);
    }

}
