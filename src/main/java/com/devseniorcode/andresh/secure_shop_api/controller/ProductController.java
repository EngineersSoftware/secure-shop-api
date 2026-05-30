package com.devseniorcode.andresh.secure_shop_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devseniorcode.andresh.secure_shop_api.dto.ProductRequestDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductRequestDTO productDto){
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto " + productDto.getName() + " validado y creado con exito");
    }

}
