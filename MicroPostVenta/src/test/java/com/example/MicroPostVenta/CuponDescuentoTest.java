package com.example.MicroPostVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.MicroPostVenta.model.CuponDescuento;
import com.example.MicroPostVenta.repository.CuponDescuentoRepository;
import com.example.MicroPostVenta.service.CuponDescuentoService;

@SpringBootTest
public class CuponDescuentoTest {

    // Inyecta el servicio de Cliente para ser probado.
    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    // Crea un mock del repositorio de Cliente para simular su comportamiento.
    @Mock
    private CuponDescuentoRepository cuponDescuentoRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(new CuponDescuento("1", "Juan", "juan@email.com")));

        // Llama al método findAll() del servicio.
          <CuponDescuento> Object cupones = cuponDescuentoService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(cupones);
        assertEquals(1, cupones.size());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        CuponDescuento cupon = new CuponDescuento(codigo, "Juan", "juan@email.com");

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(cuponDescuentoRepository.findAll()).thenReturn(Optional.of(cupon));

        // Llama al método findByCodigo() del servicio.
        CuponDescuento found = cuponDescuentoService.findByCodigo(codigo);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(codigo, found.getCodigo());
    }

    @Test
    public void testSave() {
        CuponDescuento cupon = new CuponDescuento("1", "Juan", "juan@email.com");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(cuponDescuentoRepository.saveAll(List.of(cupon))).thenReturn(List.of(cupon));

        // Llama al método save() del servicio.
        CuponDescuento saved = cuponDescuentoService.save(cupon );

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Juan", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(cuponDescuentoRepository).delete (codigo);

        // Llama al método deleteByCodigo() del servicio.
        cuponDescuentoService.deleteByCodigo(codigo);

            // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(cuponDescuentoRepository, times(1)).deleteById(codigo);
    }


}
