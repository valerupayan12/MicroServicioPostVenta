package com.example.MicroPostVenta.service;

import java.util.List;

import com.example.MicroPostVenta.model.TipoVenta;

public interface TipoVentaService {

    List<TipoVenta> getVentas();

    TipoVenta getVenta(int id_venta);

    TipoVenta saveVenta(TipoVenta venta);

    int updateVenta(TipoVenta venta);

    int deleteVenta(int id_venta);
}
