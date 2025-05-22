package com.ecomarket_spa.ecomarket_spa.repository;

import com.ecomarket_spa.ecomarket_spa.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
}
