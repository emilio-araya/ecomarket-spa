package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Producto;
import java.util.List;

public interface ProductoService {

    Producto guardarProducto(Producto producto);

    Producto obtenerProductoPorId(Long id);

    List<Producto> listarProductos();

    Producto actualizarProducto(Long id, Producto producto);

    void eliminarProducto(Long id);

    List<Producto> buscarProductosPorNombre(String nombre);
}