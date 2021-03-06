package com.lagu.shop.module.user.repository;

import com.lagu.shop.module.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity findByEmail(String email);

    UserEntity getByUuid(String uuid);

}
