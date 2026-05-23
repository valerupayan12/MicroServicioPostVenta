package com.example.MicroPostVenta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MicroPostVenta.model.Cliente;
import com.example.MicroPostVenta.model.Genero;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    Optional<Cliente> findbyId(int id_cliente);

    boolean existexistsById(int id_cliente);
    List<Cliente> findByGeneroId(Genero generoid);

    List<Cliente> obtenerClientes();
    Cliente buscarCliente(int id_cliente);

}
