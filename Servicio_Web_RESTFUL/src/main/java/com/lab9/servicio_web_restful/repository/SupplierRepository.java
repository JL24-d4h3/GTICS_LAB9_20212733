package com.lab9.servicio_web_restful.repository;

import com.lab9.servicio_web_restful.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}