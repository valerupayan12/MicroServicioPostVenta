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

import com.example.MicroPostVenta.model.ResenaCalificacion;
import com.example.MicroPostVenta.repository.ResenaCalificacionRepository;
import com.example.MicroPostVenta.service.ResenaCalificacionService;

@SpringBootTest
public class ResenaCalificacionServiceTest {

    @Autowired
    private ResenaCalificacionService resenaCalificacionService;

    @MockitoBean
    private ResenaCalificacionRepository resenaCalificacionRepository;

    // OBTENER RESENAS/CALIFICACIONES
    @Test
    public void testGetResenaCalificaciones() {
        ResenaCalificacion resena = new ResenaCalificacion();
        resena.setId_resena(1);
        resena.setMotivo("Buen producto");
        when(resenaCalificacionRepository.obtenerResenaCalificacion()).thenReturn(List.of(resena));

        List<ResenaCalificacion> resenas = resenaCalificacionService.getResenaCalificaciones();

        assertNotNull(resenas);
        assertEquals(1, resenas.size());
    }

    // BUSCAR RESENA/CALIFICACION (existe)
    @Test
    public void testGetResenaCalificacion() {
        int id = 1;
        ResenaCalificacion resena = new ResenaCalificacion();
        resena.setId_resena(id);
        resena.setMotivo("Buen producto");
        when(resenaCalificacionRepository.buscarResenaCalificacion(id)).thenReturn(resena);

        ResenaCalificacion encontrada = resenaCalificacionService.getResenaCalificacion(id);

        assertNotNull(encontrada);
        assertEquals("Buen producto", encontrada.getMotivo());
    }

    // BUSCAR RESENA/CALIFICACION (no existe)
    @Test
    public void testGetResenaCalificacion_noExiste() {
        int id = 99;
        when(resenaCalificacionRepository.buscarResenaCalificacion(id)).thenReturn(null);

        ResenaCalificacion resultado = resenaCalificacionService.getResenaCalificacion(id);

        assertNotNull(resultado); // nunca null, por el "return new ResenaCalificacion()"
        assertEquals(0, resultado.getId_resena());
    }

    // CREAR RESENA/CALIFICACION
    @Test
    public void testSaveResenaCalificacion() {
        ResenaCalificacion resena = new ResenaCalificacion();
        resena.setMotivo("Buen producto");
        when(resenaCalificacionRepository.save(resena)).thenReturn(resena);

        ResenaCalificacion creada = resenaCalificacionService.saveResenaCalificacion(resena);

        assertNotNull(creada);
        assertEquals("Buen producto", creada.getMotivo());
    }

    // ACTUALIZAR RESENA/CALIFICACION (existe)
    @Test
    public void testUpdateResenaCalificacion_existe() {
        int id = 1;
        ResenaCalificacion resenaExistente = new ResenaCalificacion();
        resenaExistente.setId_resena(id);
        when(resenaCalificacionRepository.buscarResenaCalificacion(id)).thenReturn(resenaExistente);

        ResenaCalificacion resenaNueva = new ResenaCalificacion();
        resenaNueva.setMotivo("Motivo actualizado");
        when(resenaCalificacionRepository.save(resenaNueva)).thenReturn(resenaNueva);

        ResenaCalificacion actualizada = resenaCalificacionService.updateResenaCalificacion(id, resenaNueva);

        assertNotNull(actualizada);
        assertEquals("Motivo actualizado", actualizada.getMotivo());
        assertEquals(id, resenaNueva.getId_resena()); // el service fuerza el id
    }

    // ACTUALIZAR RESENA/CALIFICACION (no existe)
    @Test
    public void testUpdateResenaCalificacion_noExiste() {
        int id = 99;
        when(resenaCalificacionRepository.buscarResenaCalificacion(id)).thenReturn(null);

        ResenaCalificacion resenaNueva = new ResenaCalificacion();
        resenaNueva.setMotivo("Motivo actualizado");

        ResenaCalificacion resultado = resenaCalificacionService.updateResenaCalificacion(id, resenaNueva);

        assertNull(resultado);
    }

    // ELIMINAR RESENA/CALIFICACION (no existe)
    @Test
    public void testDeleteResenaCalificacion() {
        int id = 1;
        when(resenaCalificacionRepository.existsById(id)).thenReturn(false);

        int resultado = resenaCalificacionService.deleteResenaCalificacion(id);

        assertEquals(0, resultado);
        verify(resenaCalificacionRepository, never()).deleteById(id);
    }

    // ELIMINAR RESENA/CALIFICACION (existe)
    @Test
    public void testDeleteResenaCalificacion_existe() {
        int id = 1;
        when(resenaCalificacionRepository.existsById(id)).thenReturn(true);

        int resultado = resenaCalificacionService.deleteResenaCalificacion(id);

        assertEquals(1, resultado);
        verify(resenaCalificacionRepository).deleteById(id);
    }
}