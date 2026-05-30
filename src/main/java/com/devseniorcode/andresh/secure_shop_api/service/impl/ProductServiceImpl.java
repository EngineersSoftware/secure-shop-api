package com.devseniorcode.andresh.secure_shop_api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devseniorcode.andresh.secure_shop_api.dto.ProductRequestDTO;
import com.devseniorcode.andresh.secure_shop_api.dto.ProductResponseDTO;
import com.devseniorcode.andresh.secure_shop_api.entity.ProductEntity;
import com.devseniorcode.andresh.secure_shop_api.repository.ProductRepository;
import com.devseniorcode.andresh.secure_shop_api.service.IProductService;
import com.devseniorcode.andresh.secure_shop_api.exception.ResourceNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        ProductEntity entity = ProductEntity.builder()
                .name(requestDTO.getName())
                .price(requestDTO.getPrice())
                .description(requestDTO.getDescription())
                .build();

        ProductEntity savedEntity = productRepository.save(entity);

        return mapToResponseDTO(savedEntity);
    }

    @Override
    @Transactional(readOnly = true) // Optimiza la consulta indicando que no habrá modificaciones
    public ProductResponseDTO getProductById(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        
        return mapToResponseDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        ProductEntity existingEntity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        existingEntity.setName(requestDTO.getName());   
        existingEntity.setPrice(requestDTO.getPrice());
        existingEntity.setDescription(requestDTO.getDescription());


        ProductEntity updatedEntity = productRepository.save(existingEntity);
        return mapToResponseDTO(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        
        productRepository.delete(entity);
    }

    private ProductResponseDTO mapToResponseDTO(ProductEntity entity) {
        return ProductResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .build();
    }
}
