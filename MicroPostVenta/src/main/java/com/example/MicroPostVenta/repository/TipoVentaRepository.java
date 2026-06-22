package com.example.MicroPostVenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MicroPostVenta.model.TipoVenta;

//import com.example.EcoMarketSPA.model.Venta;

public interface TipoVentaRepository extends JpaRepository<TipoVenta, Integer> {
    @Query("SELECT v FROM Venta v")
    List<TipoVenta> obtenerVentas();

    @Query("SELECT v FROM Venta v WHERE v.id_venta = :id_venta")
    TipoVenta buscarVenta(int id_venta);

}
