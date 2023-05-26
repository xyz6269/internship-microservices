package com.example.orderservice.dto;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {

    private String name;
    private Integer quantity;
    private Double price;
}