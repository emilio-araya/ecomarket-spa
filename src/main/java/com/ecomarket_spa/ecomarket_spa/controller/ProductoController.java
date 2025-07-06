package com.ecomarket_spa.ecomarket_spa.controller;

import com.ecomarket_spa.ecomarket_spa.dto.ProductoDTO;
import com.ecomarket_spa.ecomarket_spa.model.Producto;
import com.ecomarket_spa.ecomarket_spa.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones CRUD para productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoDTO dto) {
        Producto producto = new Producto(
            null,
            dto.getNombre(),
            dto.getDescripcion(),
            dto.getPrecio(),
            dto.getStock(),
            dto.getCategoria()
        );
        Producto nuevo = productoService.guardarProducto(producto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener un producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(producto);
    }

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @Operation(summary = "Actualizar un producto por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO dto) {
        Producto actualizado = productoService.actualizarProducto(id,
            new Producto(
                id,
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getStock(),
                dto.getCategoria()
            )
        );
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un producto por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        boolean eliminado = productoService.eliminarProducto(id);
        return eliminado
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Buscar productos por nombre")
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarProductosPorNombre(nombre));
    }
}
