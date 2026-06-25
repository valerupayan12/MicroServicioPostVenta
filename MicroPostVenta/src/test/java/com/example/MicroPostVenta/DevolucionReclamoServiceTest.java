package com.example.MicroPostVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.MicroPostVenta.model.DevolucionReclamo;
import com.example.MicroPostVenta.repository.DevolucionReclamoRepository;
import com.example.MicroPostVenta.service.DevolucionReclamoService;

@SpringBootTest
public class DevolucionReclamoServiceTest {

    @Autowired
    private DevolucionReclamoService devolucionReclamoService;

    @MockitoBean
    private DevolucionReclamoRepository devolucionReclamoRepository;

    // OBTENER DEVOLUCIONES/RECLAMOS
    @Test
    public void testGetDevolucionReclamos() {
        DevolucionReclamo devolucion = new DevolucionReclamo();
        devolucion.setId_devolucion(1);
        devolucion.setMotivo("Producto defectuoso");
        when(devolucionReclamoRepository.findAll()).thenReturn(List.of(devolucion));

        List<DevolucionReclamo> devoluciones = devolucionReclamoService.getDevolucionReclamos();

        assertNotNull(devoluciones);
        assertEquals(1, devoluciones.size());
    }

    // BUSCAR DEVOLUCION/RECLAMO (existe)
    @Test
    public void testGetDevolucionReclamoById() {
        int id = 1;
        DevolucionReclamo devolucion = new DevolucionReclamo();
        devolucion.setId_devolucion(id);
        devolucion.setMotivo("Producto defectuoso");
        when(devolucionReclamoRepository.findById(id)).thenReturn(Optional.of(devolucion));

        DevolucionReclamo encontrada = devolucionReclamoService.getDevolucionReclamoById(id);

        assertNotNull(encontrada);
        assertEquals("Producto defectuoso", encontrada.getMotivo());
    }

    // BUSCAR DEVOLUCION/RECLAMO (no existe)
    @Test
    public void testGetDevolucionReclamoById_noExiste() {
        int id = 99;
        when(devolucionReclamoRepository.findById(id)).thenReturn(Optional.empty());

        DevolucionReclamo resultado = devolucionReclamoService.getDevolucionReclamoById(id);

        assertNotNull(resultado); // nunca null, por el orElse(new DevolucionReclamo())
        assertEquals(0, resultado.getId_devolucion());
    }

    // CREAR DEVOLUCION/RECLAMO
    @Test
    public void testSaveDevolucionReclamo() {
        DevolucionReclamo devolucion = new DevolucionReclamo();
        devolucion.setMotivo("Producto defectuoso");
        when(devolucionReclamoRepository.save(devolucion)).thenReturn(devolucion);

        DevolucionReclamo creada = devolucionReclamoService.saveDevolucionReclamo(devolucion);

        assertNotNull(creada);
        assertEquals("Producto defectuoso", creada.getMotivo());
    }

    // ACTUALIZAR DEVOLUCION/RECLAMO
    @Test
    public void testUpdateDevolucionReclamo() {
        DevolucionReclamo devolucion = new DevolucionReclamo();
        devolucion.setId_devolucion(1);
        devolucion.setMotivo("Motivo actualizado");
        when(devolucionReclamoRepository.save(devolucion)).thenReturn(devolucion);

        int resultado = devolucionReclamoService.updateDevolucionReclamo(devolucion);

        assertEquals(1, resultado);
        verify(devolucionReclamoRepository).save(devolucion);
    }

    // ELIMINAR DEVOLUCION/RECLAMO (no existe)
    @Test
    public void testDeleteDevolucionReclamo() {
        int id = 1;
        when(devolucionReclamoRepository.existsById(id)).thenReturn(false);

        int resultado = devolucionReclamoService.deleteDevolucionReclamo(id);

        assertEquals(0, resultado);
        verify(devolucionReclamoRepository, never()).deleteById(id);
    }

    // ELIMINAR DEVOLUCION/RECLAMO (existe)
    @Test
    public void testDeleteDevolucionReclamo_existe() {
        int id = 1;
        when(devolucionReclamoRepository.existsById(id)).thenReturn(true);

        int resultado = devolucionReclamoService.deleteDevolucionReclamo(id);

        assertEquals(1, resultado);
        verify(devolucionReclamoRepository).deleteById(id);
    }
}