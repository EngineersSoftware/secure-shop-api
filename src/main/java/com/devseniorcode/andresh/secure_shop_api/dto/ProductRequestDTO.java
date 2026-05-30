package com.devseniorcode.andresh.secure_shop_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDTO {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre del producto debe tener entre 3 y 50 caracteres")
    private String name;

    @NotNull(message = "El precio del producto no puede ser nulo")
    @Min(value = 1, message = "El precio del producto debe ser mayor a 0")
    private Double price;

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String description;

}
