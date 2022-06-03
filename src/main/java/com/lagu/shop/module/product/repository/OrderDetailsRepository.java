package com.lagu.shop.module.product.repository;

import com.lagu.shop.module.product.entity.OrderDetailsEntity;
import com.lagu.shop.module.product.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long>, JpaSpecificationExecutor<OrderDetailsEntity> {

    List<OrderDetailsEntity> findByOrderOrderByProduct(OrderEntity order);

}
