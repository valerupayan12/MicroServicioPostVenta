package com.example.MicroPostVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroPostVenta.model.Venta;
import com.example.MicroPostVenta.repository.VentaRepository;
import com.example.MicroPostVenta.service.impl.VentaServiceimpl;

@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaServiceimpl ventaService;

    @Test
    public void testGetVentas() {
        Venta venta = new Venta();
        venta.setId_venta(1);
        venta.setTipo_documento("boleta");

        when(ventaRepository.obtenerVentas()).thenReturn(List.of(venta));

        List<Venta> ventas = ventaService.getVentas();

        assertNotNull(ventas);
        assertEquals(1, ventas.size());
    }

    @Test
    public void testGetVenta_existe() {
        int id = 1;
        Venta venta = new Venta();
        venta.setId_venta(id);
        venta.setTipo_documento("boleta");

        when(ventaRepository.buscarVenta(id)).thenReturn(venta);

        Venta encontrada = ventaService.getVenta(id);

        assertNotNull(encontrada);
        assertEquals("boleta", encontrada.getTipo_documento());
    }

    @Test
    public void testGetVenta_noExiste() {
        int id = 99;

        when(ventaRepository.buscarVenta(id)).thenReturn(null);

        Venta resultado = ventaService.getVenta(id);

        // La implementación retorna new Venta() cuando no encuentra
        assertNotNull(resultado);
        assertEquals(0, resultado.getId_venta());
    }

    @Test
    public void testSaveVenta() {
        Venta venta = new Venta();
        venta.setId_venta(1);
        venta.setTipo_documento("factura");
        venta.setTotal_neto(50000);

        when(ventaRepository.save(venta)).thenReturn(venta);

        Venta creada = ventaService.saveVenta(venta);

        assertNotNull(creada);
        assertEquals("factura", creada.getTipo_documento());
        assertEquals(50000, creada.getTotal_neto());
    }

    @Test
    public void testUpdateVenta() {
        Venta venta = new Venta();
        venta.setId_venta(1);
        venta.setTipo_documento("boleta");

        when(ventaRepository.save(venta)).thenReturn(venta);

        int resultado = ventaService.updateVenta(venta);

        assertEquals(1, resultado);
        verify(ventaRepository).save(venta);
    }

    @Test
    public void testDeleteVenta_noExiste() {
        int id = 99;

        when(ventaRepository.existsById(id)).thenReturn(false);

        int resultado = ventaService.deleteVenta(id);

        assertEquals(0, resultado);
        verify(ventaRepository, never()).deleteById(id);
    }

    @Test
    public void testDeleteVenta_existe() {
        int id = 1;

        when(ventaRepository.existsById(id)).thenReturn(true);

        int resultado = ventaService.deleteVenta(id);

        assertEquals(1, resultado);
        verify(ventaRepository).deleteById(id);
    }
}