package com.lab9.servicio_web_restful.repository;

import com.lab9.servicio_web_restful.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}