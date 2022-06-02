package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.product.entity.OrderEntity;
import com.lagu.shop.module.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    List<OrderEntity> findByUserOrderByCreatedOn(UserEntity user);

    OrderEntity getByUserAndUuid(UserEntity user, String uuid);
}
