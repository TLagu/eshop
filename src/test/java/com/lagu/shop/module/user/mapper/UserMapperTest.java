package com.lagu.shop.module.user.mapper;

import com.lagu.shop.module.product.entity.Status;
import com.lagu.shop.module.user.dto.UserForm;
import com.lagu.shop.module.user.entity.UserEntity;
import com.lagu.shop.module.user.entity.UserRole;

public class UserMapperTest {

    public static final Status STATUS = Status.ACTIVE;
    public static final String UUID = "53c069de-e435-11ec-8fea-0242ac120002";
    public static final String EMAIL = "user1@wp.pl";
    public static final String PASSWORD = "$2a$10$9rdlBPfe5uhGITH23dgRx.3FvzR78SCqnuW2e0yt78THlxoMHYWb.";
    public static final String FIRST_NAME = "user1";
    public static final String LAST_NAME = "user1";
    public static final UserRole ROLE = UserRole.USER;
    public static final Double LONGITUDE = 14.5528;
    public static final Double LATITUDE = 53.4285;
    public static final String COUNTRY = "Polska";
    public static final String CITY = "Szczecin";
    public static final String POST_CODE = "00-000";
    public static final String POST = "Szczecin";
    public static final String STREET = "Szeroka 1/2";

    public static final UserEntity USER_ENTITY = new UserEntity()
            .setStatus(STATUS)
            .setUuid(UUID)
            .setEmail(EMAIL)
            .setPassword(PASSWORD)
            .setFirstName(FIRST_NAME)
            .setLastName(LAST_NAME)
            .setRole(ROLE)
            .setLongitude(LONGITUDE)
            .setLatitude(LATITUDE)
            .setCountry(COUNTRY)
            .setCity(CITY)
            .setPostCode(POST_CODE)
            .setPost(POST)
            .setStreet(STREET);

    public static final UserForm USER_FORM = new UserForm()
            .setStatus(STATUS.toString())
            .setUuid(UUID)
            .setEmail(EMAIL)
            .setPassword(PASSWORD)
            .setFirstName(FIRST_NAME)
            .setLastName(LAST_NAME)
            .setRole(ROLE.toString())
            .setLongitude(LONGITUDE)
            .setLatitude(LATITUDE)
            .setCountry(COUNTRY)
            .setCity(CITY)
            .setPostCode(POST_CODE)
            .setPost(POST)
            .setStreet(STREET);

}