package com.lab9.servicio_web_restful.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private Integer id;

    @Column(name = "CategoryName", length = 25)
    private String categoryName;

    @Column(name = "Description", length = 255)
    private String description;
}