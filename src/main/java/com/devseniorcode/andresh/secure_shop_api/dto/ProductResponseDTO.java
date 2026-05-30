package com.devseniorcode.andresh.secure_shop_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {

    private Long id;
    private String name;
    private Double price;
    private String description;

}