package com.ecomarket_spa.ecomarket_spa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.ecomarket_spa.ecomarket_spa.model.Proveedor;
import com.ecomarket_spa.ecomarket_spa.repository.ProveedorRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void obtenerProveedorPorId_retornaProveedorExistente() {
        Proveedor p = new Proveedor(1L, "Proveedor X");
        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(p));

        Optional<Proveedor> resultado = proveedorService.obtenerProveedorPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Proveedor X", resultado.get().getNombre());
        verify(proveedorRepository).findById(1L);
    }

    @Test
    void obtenerProveedorPorId_retornaVacioCuandoNoExiste() {
        when(proveedorRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Proveedor> resultado = proveedorService.obtenerProveedorPorId(99L);

        assertFalse(resultado.isPresent());
        verify(proveedorRepository).findById(99L);
    }

    @Test
    void guardarProveedor_retornaProveedorGuardado() {
        Proveedor p = new Proveedor(null, "Nuevo Proveedor");
        Proveedor pGuardado = new Proveedor(2L, "Nuevo Proveedor");

        when(proveedorRepository.save(p)).thenReturn(pGuardado);

        Proveedor resultado = proveedorService.guardarProveedor(p);

        assertNotNull(resultado.getId());
        assertEquals("Nuevo Proveedor", resultado.getNombre());
        verify(proveedorRepository).save(p);
    }
}
