package com.ecomarket_spa.ecomarket_spa.repository;

import com.ecomarket_spa.ecomarket_spa.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByNombreContaining(String nombre);
}