package com.example.MicroPostVenta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.MicroPostVenta.repository.ClienteRepository;
import com.example.MicroPostVenta.service.ClienteService;

import antlr.collections.List;

@SpringBootTest
public class ClienteServiceTest<Cliente> {

    // Inyecta el servicio de Cliente para ser probado.
    @Autowired
    private ClienteService clienteService;

    // Crea un mock del repositorio de Cliente para simular su comportamiento.
    @Mock
    private ClienteRepository clienteRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente("1", "Juan", "juan@email.com")));

        // Llama al método findAll() del servicio.
          <Cliente> clientes = clienteService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        Cliente cliente = new Cliente(codigo, "Juan", "juan@email.com");

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(clienteRepository.findAll()).thenReturn(Optional.of(cliente));

        // Llama al método findByCodigo() del servicio.
        Cliente found = clienteService.findByCodigo(codigo);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(codigo, found.getCodigo());
    }

    @Test
    public void testSave() {
        Cliente cliente = new Cliente("1", "Juan", "juan@email.com");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(clienteRepository.saveAll(List.of(cliente))).thenReturn(List.of(cliente));

        // Llama al método save() del servicio.
        Cliente saved = clienteService.save(cliente);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Juan", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(clienteRepository).deleteById (codigo);

        // Llama al método deleteByCodigo() del servicio.
        clienteService.deleteByCodigo(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(clienteRepository, times(1)).deleteAllById(codigo);
    }

}
