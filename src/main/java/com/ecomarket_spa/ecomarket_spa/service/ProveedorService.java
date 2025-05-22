package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Proveedor;

import java.util.List;

public interface ProveedorService {
    
    Proveedor guardarProveedor(Proveedor proveedor);
    
    Proveedor obtenerProveedorPorId(Long id);
    
    List<Proveedor> listarProveedores();
    
    Proveedor actualizarProveedor(Long id, Proveedor proveedor);
    
    boolean eliminarProveedor(Long id);
    
    List<Proveedor> buscarProveedoresPorNombre(String nombre);
}
