package com.devseniorcode.andresh.secure_shop_api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public String getAllProducts(){
        return "Lista de productos";
    }

    @PostMapping
    public String createProduct(){
        return "Producto CREADO";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id){
        return "Producto " + id + " Actualizado";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return "Producto " + id + " Eliminado";
    }

}
