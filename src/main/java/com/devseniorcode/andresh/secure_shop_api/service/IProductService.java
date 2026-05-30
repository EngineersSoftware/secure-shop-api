package com.devseniorcode.andresh.secure_shop_api.service;

import java.util.List;

import com.devseniorcode.andresh.secure_shop_api.dto.ProductRequestDTO;
import com.devseniorcode.andresh.secure_shop_api.dto.ProductResponseDTO;

public interface IProductService {
    ProductResponseDTO createProduct(ProductRequestDTO requestDTO);

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO);

    void deleteProduct(Long id);
}
