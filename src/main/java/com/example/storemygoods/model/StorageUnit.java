package com.example.storemygoods.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor @Data @Builder @ToString
public class StorageUnit {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private Integer sqFeet;
    private Integer price;
    private String about;
}
