package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
