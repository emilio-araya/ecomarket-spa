package com.ecomarket_spa.ecomarket_spa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.ecomarket_spa.ecomarket_spa.model.Producto;
import com.ecomarket_spa.ecomarket_spa.repository.ProductoRepository;
import com.ecomarket_spa.ecomarket_spa.service.ProductoServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void guardarProducto_retornaProductoGuardado() {
        Producto producto = new Producto();
        Producto guardado = new Producto();

        when(productoRepository.save(producto)).thenReturn(guardado);

        Producto resultado = productoService.guardarProducto(producto);

        assertSame(guardado, resultado);
        verify(productoRepository).save(producto);
    }

    @Test
    void obtenerProductoPorId_retornaProductoExistente() {
        Producto producto = new Producto();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoService.obtenerProductoPorId(1L);

        assertSame(producto, resultado);
        verify(productoRepository).findById(1L);
    }

    @Test
    void obtenerProductoPorId_lanzaExcepcionCuandoNoExiste() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> productoService.obtenerProductoPorId(99L));
        verify(productoRepository).findById(99L);
    }

    @Test
    void listarProductos_retornaProductosDelRepositorio() {
        Producto producto1 = new Producto();
        Producto producto2 = new Producto();
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        var resultado = productoService.listarProductos();

        assertEquals(2, resultado.size());
        assertEquals(Arrays.asList(producto1, producto2), resultado);
        verify(productoRepository).findAll();
    }

    @Test
    void eliminarProducto_eliminaCuandoExiste() {
        when(productoRepository.existsById(1L)).thenReturn(true);

        productoService.eliminarProducto(1L);

        verify(productoRepository).existsById(1L);
        verify(productoRepository).deleteById(1L);
    }

    @Test
    void eliminarProducto_lanzaExcepcionCuandoNoExiste() {
        when(productoRepository.existsById(99L)).thenReturn(false);

        assertThrows(NoSuchElementException.class,
                () -> productoService.eliminarProducto(99L));
        verify(productoRepository, never()).deleteById(anyLong());
    }
}
