package com.example.MicroPostVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.MicroPostVenta.model.Producto;
import com.example.MicroPostVenta.repository.ProductoRepository;
import com.example.MicroPostVenta.service.ProductoService;

@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockitoBean
    private ProductoRepository productoRepository;

    // OBTENER PRODUCTOS
    @Test
    public void testGetProductos() {
        Producto producto = new Producto();
        producto.setId_producto(1);
        producto.setNombre("Producto 1");
        when(productoRepository.obtenerProductos()).thenReturn(List.of(producto));

        List<Producto> productos = productoService.getProductos();

        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    // BUSCAR PRODUCTO (existe)
    @Test
    public void testGetProducto() {
        int id = 1;
        Producto producto = new Producto();
        producto.setId_producto(id);
        producto.setNombre("Producto 1");
        when(productoRepository.buscarProducto(id)).thenReturn(producto);

        Producto encontrado = productoService.getProducto(id);

        assertNotNull(encontrado);
        assertEquals("Producto 1", encontrado.getNombre());
    }

    // BUSCAR PRODUCTO (no existe)
    @Test
    public void testGetProducto_noExiste() {
        int id = 99;
        when(productoRepository.buscarProducto(id)).thenReturn(null);

        Producto resultado = productoService.getProducto(id);

        assertNotNull(resultado); // nunca null, por el "return new Producto()"
        assertEquals(0, resultado.getId_producto());
    }

    // CREAR PRODUCTO
    @Test
    public void testSaveProducto() {
        Producto producto = new Producto();
        producto.setNombre("Producto 1");
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto creado = productoService.saveProducto(producto);

        assertNotNull(creado);
        assertEquals("Producto 1", creado.getNombre());
    }

    // ACTUALIZAR PRODUCTO (existe)
    @Test
    public void testUpdateProducto_existe() {
        int id = 1;
        Producto productoExistente = new Producto();
        productoExistente.setId_producto(id);
        when(productoRepository.buscarProducto(id)).thenReturn(productoExistente);

        Producto productoNuevo = new Producto();
        productoNuevo.setNombre("Producto Actualizado");
        when(productoRepository.save(productoNuevo)).thenReturn(productoNuevo);

        Producto actualizado = productoService.updateProducto(id, productoNuevo);

        assertNotNull(actualizado);
        assertEquals("Producto Actualizado", actualizado.getNombre());
        assertEquals(id, productoNuevo.getId_producto()); // el service fuerza el id
    }

    // ACTUALIZAR PRODUCTO (no existe)
    @Test
    public void testUpdateProducto_noExiste() {
        int id = 99;
        when(productoRepository.buscarProducto(id)).thenReturn(null);

        Producto productoNuevo = new Producto();
        productoNuevo.setNombre("Producto Actualizado");

        Producto resultado = productoService.updateProducto(id, productoNuevo);

        assertNull(resultado);
    }

    // ELIMINAR PRODUCTO (no existe)
    @Test
    public void testDeleteProducto() {
        int id = 1;
        when(productoRepository.existsById(id)).thenReturn(false);

        int resultado = productoService.deleteProducto(id);

        assertEquals(0, resultado);
        verify(productoRepository, never()).deleteById(id);
    }

    // ELIMINAR PRODUCTO (existe)
    @Test
    public void testDeleteProducto_existe() {
        int id = 1;
        when(productoRepository.existsById(id)).thenReturn(true);

        int resultado = productoService.deleteProducto(id);

        assertEquals(1, resultado);
        verify(productoRepository).deleteById(id);
    }
}