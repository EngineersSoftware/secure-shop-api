package com.devseniorcode.andresh.secure_shop_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devseniorcode.andresh.secure_shop_api.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
}
