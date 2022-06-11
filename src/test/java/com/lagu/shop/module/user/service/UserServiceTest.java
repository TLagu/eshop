package com.lagu.shop.module.user.service;

import com.lagu.shop.module.user.dto.UserDto;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.mapper.UserFormMapper;
import com.lagu.shop.module.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.lagu.shop.module.user.mapper.UserMapperTest.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    public final static int PAGE = 0;
    public final static int SIZE = 5;

    @Mock
    UserRepository userRepository;
    @Mock
    UserFormMapper userFormMapperMock = mock(UserFormMapper.class);
    @InjectMocks
    UserService userService;

    @Test
    void shouldGetUserByEmail() {
        //given
        when(userRepository.findByEmail(EMAIL)).thenReturn(new UserEntity());
        //when
        UserDto dto = userService.getDtoByEmail(EMAIL);
        //then
        assertThat(dto).isNotNull().isInstanceOf(UserDto.class);
        //verify
        verify(userRepository).findByEmail(EMAIL);
    }

    @Test
    void shouldGetUserByUuid() {
        //given
        when(userRepository.getByUuid(UUID)).thenReturn(new UserEntity());
        //when
        UserDto dto = userService.getDtoByUuid(UUID);
        //then
        assertThat(dto).isNotNull().isInstanceOf(UserDto.class);
        //verify
        verify(userRepository).getByUuid(UUID);
    }

    //TODO: how to test a reference to a static method
    //TODO: How to test a method that returns nothing
    //TODO: Should mappers be tested?
//    @Test
//    void shouldCreateUser() {
//        //given
//        try (MockedStatic<UserFormMapper> mocked = Mockito.mockStatic(UserFormMapper.class)) {
//            var mockInstant = mock(UserFormMapper.class);
//            when(mockInstant.map(USER_FORM.setUuid(null), USER_ENTITY.getRole())).thenReturn(USER_ENTITY);
//        }
//        //when(userFormMapperMock.map(USER_FORM.setUuid(null), USER_ENTITY.getRole())).thenReturn(USER_ENTITY);
//        when(userRepository.saveAndFlush(USER_ENTITY)).thenReturn(USER_ENTITY);
//        //when
//        UserDto dto = userService.createOrUpdate(USER_FORM.setUuid(null));
//        //then
//        assertThat(dto).isNotNull().isInstanceOf(UserDto.class);
//        //verify
//        verify(userRepository).saveAndFlush(USER_ENTITY);
//    }

    //TODO: I can't test createOrUpdate method
//    @Test
//    void shouldUpdateUser() {
//        //given
//        when(userRepository.saveAndFlush(USER_ENTITY)).thenReturn(USER_ENTITY);
//        when(userRepository.getByUuid(UUID)).thenReturn(new UserEntity());
//        //when
//        UserDto dto = userService.createOrUpdate(USER_FORM);
//        //then
//        assertThat(dto).isNotNull().isInstanceOf(UserDto.class);
//        //verify
//        verify(userRepository).saveAndFlush(USER_ENTITY);
//        verify(userRepository).getByUuid(UUID);
//    }

}