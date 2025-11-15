package com.lab9.servicio_web_restful.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Products")
@Getter
@Setter

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Integer id;

    @Column(name = "ProductName", length = 50)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;

    @Column(name = "Unit", length = 25)
    private String unit;

    @Column(name = "Price")
    private Double price;
}
