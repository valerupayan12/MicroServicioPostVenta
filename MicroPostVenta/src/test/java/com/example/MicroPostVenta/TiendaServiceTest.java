package com.example.MicroPostVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.MicroPostVenta.model.Tienda;
import com.example.MicroPostVenta.repository.TiendaRepository;
import com.example.MicroPostVenta.service.TiendaService;

@SpringBootTest
public class TiendaServiceTest {

    @Autowired
    private TiendaService tiendaService;

    @MockitoBean
    private TiendaRepository tiendaRepository;

    // OBTENER TIENDAS
    @Test
    public void testGetTiendas() {
        Tienda tienda = new Tienda();
        tienda.setId_tienda(1);
        tienda.setDireccion("Av. Siempre Viva 123");
        when(tiendaRepository.obtenerTienda()).thenReturn(List.of(tienda));

        List<Tienda> tiendas = tiendaService.getTiendas();

        assertNotNull(tiendas);
        assertEquals(1, tiendas.size());
    }

    // BUSCAR TIENDA (existe)
    @Test
    public void testGetTienda() {
        int id = 1;
        Tienda tienda = new Tienda();
        tienda.setId_tienda(id);
        tienda.setDireccion("Av. Siempre Viva 123");
        when(tiendaRepository.buscarTienda(id)).thenReturn(tienda);

        Tienda encontrada = tiendaService.getTienda(id);

        assertNotNull(encontrada);
        assertEquals("Av. Siempre Viva 123", encontrada.getDireccion());
    }

    // BUSCAR TIENDA (no existe)
    @Test
    public void testGetTienda_noExiste() {
        int id = 99;
        when(tiendaRepository.buscarTienda(id)).thenReturn(null);

        Tienda resultado = tiendaService.getTienda(id);

        assertNotNull(resultado); // nunca null, por el "return new Tienda()"
        assertEquals(0, resultado.getId_tienda());
    }

    // CREAR TIENDA
    @Test
    public void testSaveTienda() {
        Tienda tienda = new Tienda();
        tienda.setDireccion("Av. Siempre Viva 123");
        when(tiendaRepository.save(tienda)).thenReturn(tienda);

        Tienda creada = tiendaService.saveTienda(tienda);

        assertNotNull(creada);
        assertEquals("Av. Siempre Viva 123", creada.getDireccion());
    }

    // ACTUALIZAR TIENDA (existe)
    @Test
    public void testUpdateTienda_existe() {
        int id = 1;
        Tienda tiendaExistente = new Tienda();
        tiendaExistente.setId_tienda(id);
        when(tiendaRepository.buscarTienda(id)).thenReturn(tiendaExistente);

        Tienda tiendaNueva = new Tienda();
        tiendaNueva.setDireccion("Direccion Actualizada");
        when(tiendaRepository.save(tiendaNueva)).thenReturn(tiendaNueva);

        Tienda actualizada = tiendaService.updateTienda(id, tiendaNueva);

        assertNotNull(actualizada);
        assertEquals("Direccion Actualizada", actualizada.getDireccion());
        assertEquals(id, tiendaNueva.getId_tienda()); // el service fuerza el id
    }

    // ACTUALIZAR TIENDA (no existe)
    @Test
    public void testUpdateTienda_noExiste() {
        int id = 99;
        when(tiendaRepository.buscarTienda(id)).thenReturn(null);

        Tienda tiendaNueva = new Tienda();
        tiendaNueva.setDireccion("Direccion Actualizada");

        Tienda resultado = tiendaService.updateTienda(id, tiendaNueva);

        assertNull(resultado);
    }

    // ELIMINAR TIENDA (no existe)
    @Test
    public void testDeleteTienda() {
        int id = 1;
        when(tiendaRepository.existsById(id)).thenReturn(false);

        int resultado = tiendaService.deleteTienda(id);

        assertEquals(0, resultado);
        verify(tiendaRepository, never()).deleteById(id);
    }

    // ELIMINAR TIENDA (existe)
    @Test
    public void testDeleteTienda_existe() {
        int id = 1;
        when(tiendaRepository.existsById(id)).thenReturn(true);

        int resultado = tiendaService.deleteTienda(id);

        assertEquals(1, resultado);
        verify(tiendaRepository).deleteById(id);
    }

    // DELETE BY CODIGO (sin implementar todavia -> debe lanzar excepcion)
    @Test
    public void testDeleteByCodigo_noImplementado() {
        assertThrows(UnsupportedOperationException.class,
                () -> tiendaService.deleteByCodigo("ABC123"));
    }
}