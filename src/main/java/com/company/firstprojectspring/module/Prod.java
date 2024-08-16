package com.company.firstprojectspring.module;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prod")
public class Prod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prodId;

    private String prodOwner;
    private String prodName;
    private String prodType;
    private String prodColor;
    private String prodPrice;


    private Integer userId;
}
