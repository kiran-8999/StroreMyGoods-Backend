package com.example.storemygoods.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private Long rentingId;
    private String userEmail;
    private String billType;
    private Integer price;
    private String time;
    private LocalDate date=LocalDate.now();
    private String status = "Pending Amount";
    private String endDate;

}
