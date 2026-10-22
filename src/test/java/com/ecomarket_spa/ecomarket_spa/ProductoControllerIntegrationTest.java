package com.ecomarket_spa.ecomarket_spa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ecomarket_spa.ecomarket_spa.model.Producto;
import com.ecomarket_spa.ecomarket_spa.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Contrato HTTP del catálogo: códigos de estado, validación y mapeo de errores.
 *
 * <p>Sin esto, la única red de seguridad eran tests de servicio con mocks, que
 * no detectan un cambio de status, un campo que deja de validarse o un error
 * que pasa de 404 a 500.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void creaUnProductoYDevuelveCreated() throws Exception {
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(new Producto(
                                null, "Café", "Grano 1kg", 8990.0, 12, "Almacén"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nombre").value("Café"))
                .andExpect(jsonPath("$.precio").value(8990.0))
                .andExpect(jsonPath("$.stock").value(12));
    }

    @Test
    void rechazaUnProductoInvalidoCampoPorCampo() throws Exception {
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(Map.of(
                                "nombre", " ",
                                "precio", 0,
                                "stock", -1,
                                "categoria", ""))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nombre").value("El nombre es obligatorio"))
                .andExpect(jsonPath("$.precio").value("El precio debe ser mayor a 0"))
                .andExpect(jsonPath("$.stock").value("El stock debe ser un número positivo"))
                .andExpect(jsonPath("$.categoria").value("La categoría es obligatoria"));
    }

    @Test
    void respondeNotFoundCuandoElProductoNoExiste() throws Exception {
        mockMvc.perform(get("/api/productos/{id}", 9999))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Producto no encontrado con el ID: 9999"));
    }

    @Test
    void listaYBuscaProductosPorNombre() throws Exception {
        productoRepository.save(new Producto(null, "Yerba", "1kg", 4500.0, 5, "Almacén"));
        productoRepository.save(new Producto(null, "Detergente", "500ml", 2000.0, 3, "Limpieza"));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        mockMvc.perform(get("/api/productos/buscar").param("nombre", "Yer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Yerba"));
    }

    @Test
    void actualizaUnProductoExistente() throws Exception {
        Producto guardado = productoRepository.save(
                new Producto(null, "Té", "Saquitos", 1990.0, 8, "Almacén"));

        mockMvc.perform(put("/api/productos/{id}", guardado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(new Producto(
                                null, "Té verde", "Saquitos x20", 2590.0, 15, "Almacén"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(guardado.getId()))
                .andExpect(jsonPath("$.nombre").value("Té verde"))
                .andExpect(jsonPath("$.precio").value(2590.0))
                .andExpect(jsonPath("$.stock").value(15));
    }

    @Test
    void actualizarUnProductoInexistenteDevuelveNotFound() throws Exception {
        mockMvc.perform(put("/api/productos/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(new Producto(
                                null, "Fantasma", "x", 1000.0, 1, "Otros"))))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Producto no encontrado con el ID: 9999"));
    }

    @Test
    void eliminaUnProductoYDevuelveNoContent() throws Exception {
        Producto guardado = productoRepository.save(
                new Producto(null, "Azúcar", "1kg", 1200.0, 20, "Almacén"));

        mockMvc.perform(delete("/api/productos/{id}", guardado.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/productos/{id}", guardado.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminarUnProductoInexistenteDevuelveNotFound() throws Exception {
        mockMvc.perform(delete("/api/productos/{id}", 9999))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Producto no encontrado con el ID: 9999"));
    }

    private String json(Object body) throws Exception {
        return objectMapper.writeValueAsString(body);
    }
}
