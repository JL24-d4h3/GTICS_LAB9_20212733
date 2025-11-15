package com.lab9.servicio_web_restful.repository;

import com.lab9.servicio_web_restful.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}