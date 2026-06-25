package com.example.MicroPostVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.MicroPostVenta.model.CuponDescuento;
import com.example.MicroPostVenta.repository.CuponDescuentoRepository;
import com.example.MicroPostVenta.service.CuponDescuentoService;

@SpringBootTest
public class CuponDescuentoTest {

    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    @MockitoBean
    private CuponDescuentoRepository cuponDescuentoRepository;

    // OBTENER CUPONES
    @Test
    public void testObtenerCuponDescuentos() {
        CuponDescuento cupon = new CuponDescuento();
        cupon.setId_cupon_descuento(1);
        cupon.setCodigo(12345678);
        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(cupon));

        List<CuponDescuento> cupones = cuponDescuentoService.obtenerCuponDescuentos();

        assertNotNull(cupones);
        assertEquals(1, cupones.size());
    }

    // BUSCAR CUPON
    @Test
    public void testBuscarCuponDescuento() {
        int id = 1;
        CuponDescuento cupon = new CuponDescuento();
        cupon.setId_cupon_descuento(id);
        cupon.setCodigo(12345678);
        when(cuponDescuentoRepository.findById(id)).thenReturn(Optional.of(cupon));

        CuponDescuento encontrado = cuponDescuentoService.buscarCuponDescuento(id);

        assertNotNull(encontrado);
        assertEquals(12345678, encontrado.getCodigo());
    }

    // BUSCAR CUPON no existe
    @Test
    public void testBuscarCuponDescuento_noExiste() {
        int id = 99;
        when(cuponDescuentoRepository.findById(id)).thenReturn(Optional.empty());

        CuponDescuento resultado = cuponDescuentoService.buscarCuponDescuento(id);

        assertNotNull(resultado); // nunca null, por el orElse(new CuponDescuento())
        assertEquals(0, resultado.getId_cupon_descuento());
    }

    // CREAR CUPON
    @Test
    public void testCrearCuponDescuento() {
        CuponDescuento cupon = new CuponDescuento();
        cupon.setCodigo(11112222);
        when(cuponDescuentoRepository.save(cupon)).thenReturn(cupon);

        CuponDescuento creado = cuponDescuentoService.crearCuponDescuento(cupon);

        assertNotNull(creado);
        assertEquals(11112222, creado.getCodigo());
    }

    // ACTUALIZAR CUPON
    @Test
    public void testActualizarCuponDescuento() {
        CuponDescuento cupon = new CuponDescuento();
        cupon.setId_cupon_descuento(1);
        cupon.setDescuento_pct(20);
        when(cuponDescuentoRepository.save(cupon)).thenReturn(cupon);

        CuponDescuento actualizado = cuponDescuentoService.actualizarCuponDescuento(cupon);

        assertNotNull(actualizado);
        assertEquals(20, actualizado.getDescuento_pct());
    }

    // ELIMINAR CUPON (no existe)
    @Test
    public void testEliminarCuponDescuento() {
        int id = 1;
        when(cuponDescuentoRepository.existsById(id)).thenReturn(false);

        int resultado = cuponDescuentoService.eliminarCuponDescuento(id);

        assertEquals(0, resultado);
        verify(cuponDescuentoRepository, never()).deleteById(id);
    }

    // ELIMINAR CUPON (existe)
    @Test
    public void testEliminarCuponDescuento_existe() {
        int id = 1;
        when(cuponDescuentoRepository.existsById(id)).thenReturn(true);

        int resultado = cuponDescuentoService.eliminarCuponDescuento(id);

        assertEquals(1, resultado);
        verify(cuponDescuentoRepository).deleteById(id);
    }

    // DELETE BY CODIGO (sin implementar todavia -> debe lanzar excepcion)
    @Test
    public void testDeleteByCodigo_noImplementado() {
        assertThrows(UnsupportedOperationException.class,
                () -> cuponDescuentoService.deleteByCodigo("12345678"));
    }
}