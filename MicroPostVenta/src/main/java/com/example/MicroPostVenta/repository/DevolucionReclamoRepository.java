package com.example.MicroPostVenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MicroPostVenta.model.DevolucionReclamo;

@Repository
public interface DevolucionReclamoRepository extends JpaRepository<DevolucionReclamo, Integer> {
    @Query("SELECT d FROM DevolucionReclamo d")
    List<DevolucionReclamo> obtenerDevolucionReclamos();

    @Query("SELECT d FROM DevolucionReclamo d WHERE d.id_devolucion = :id_devolucion")
    DevolucionReclamo busDevolucionReclamo(int id_devolucion);


}
