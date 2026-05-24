package com.example.MicroPostVenta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroPostVenta.model.Venta;
import com.example.MicroPostVenta.repository.VentaRepository;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    //obtener
    public List<Venta> getVentas(){
        return ventaRepository.obtenerVentas();
    }
    //bucar
    public Venta getVenta(int id_venta){
        Venta ventas = ventaRepository.buscarVenta(id_venta);
        if (ventas!=null) {
        return ventas;
        }else
        return new Venta();
    }
    //eliminar
    public int deleteVenta(int id_venta){
        ventaRepository.delete(getVenta(id_venta));
        return 1;
    }
    //buardar
    public Venta saveVenta(Venta venta){
        return ventaRepository.save(venta);
    }
    //modificar
    public int updateVenta(Venta venta){
        ventaRepository.save(venta);
        return 1;
    }

}
