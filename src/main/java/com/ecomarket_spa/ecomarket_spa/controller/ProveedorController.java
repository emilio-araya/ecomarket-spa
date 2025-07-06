package com.ecomarket_spa.ecomarket_spa.controller;

import com.ecomarket_spa.ecomarket_spa.model.Proveedor;
import com.ecomarket_spa.ecomarket_spa.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
@Tag(name = "Proveedores", description = "CRUD de proveedores del sistema")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Operation(summary = "Crear un nuevo proveedor")
    @PostMapping
    public ResponseEntity<EntityModel<Proveedor>> crearProveedor(@RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.guardarProveedor(proveedor);
        EntityModel<Proveedor> proveedorModel = toModel(nuevoProveedor);
        return new ResponseEntity<>(proveedorModel, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener proveedor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Proveedor>> obtenerProveedor(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.obtenerProveedorPorId(id);
        if (proveedor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toModel(proveedor));
    }

    @Operation(summary = "Listar todos los proveedores")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Proveedor>>> listarProveedores() {
        List<Proveedor> proveedores = proveedorService.listarProveedores();

        List<EntityModel<Proveedor>> proveedorModels = proveedores.stream()
            .map(this::toModel)
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Proveedor>> collectionModel =
            CollectionModel.of(proveedorModels,
                linkTo(methodOn(ProveedorController.class).listarProveedores()).withSelfRel());

        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un proveedor por ID")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Proveedor>> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        Proveedor actualizado = proveedorService.actualizarProveedor(id, proveedor);
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(toModel(actualizado));
    }

    @Operation(summary = "Eliminar proveedor por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        boolean eliminado = proveedorService.eliminarProveedor(id);
        return eliminado
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Buscar proveedores por nombre")
    @GetMapping("/buscar")
    public ResponseEntity<CollectionModel<EntityModel<Proveedor>>> buscarPorNombre(@RequestParam String nombre) {
        List<Proveedor> proveedores = proveedorService.buscarProveedoresPorNombre(nombre);

        List<EntityModel<Proveedor>> proveedorModels = proveedores.stream()
            .map(this::toModel)
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Proveedor>> collectionModel =
            CollectionModel.of(proveedorModels,
                linkTo(methodOn(ProveedorController.class).buscarPorNombre(nombre)).withSelfRel());

        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    // HATEOAS helper
    private EntityModel<Proveedor> toModel(Proveedor proveedor) {
        return EntityModel.of(proveedor,
            linkTo(methodOn(ProveedorController.class).obtenerProveedor(proveedor.getId())).withSelfRel(),
            linkTo(methodOn(ProveedorController.class).listarProveedores()).withRel("proveedores"));
    }
}
