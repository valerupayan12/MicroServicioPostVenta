package com.example.MicroPostVenta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.MicroPostVenta.model.TipoVenta;
import com.example.MicroPostVenta.repository.TipoVentaRepository;
import com.example.MicroPostVenta.service.TipoVentaService;

@Service
@Transactional
public class TipoVentaServiceimpl implements TipoVentaService {

    @Autowired
    private TipoVentaRepository ventaRepository;

    @Override
    public List<TipoVenta> getVentas() {
        return ventaRepository.obtenerVentas();
    }

    @Override
    public TipoVenta getVenta(int id_venta) {
        TipoVenta venta = ventaRepository.buscarVenta(id_venta);
        if (venta != null) {
            return venta;
        }
        return new TipoVenta();
    }

    @Override
    public TipoVenta saveVenta(TipoVenta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public int updateVenta(TipoVenta venta) {
        ventaRepository.save(venta);
        return 1;
    }

    @Override
    public int deleteVenta(int id_venta) {
        if (ventaRepository.existsById(id_venta)) {
            ventaRepository.deleteById(id_venta);
            return 1;
        }
        return 0;
    }
}
