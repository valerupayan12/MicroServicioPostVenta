package com.example.MicroPostVenta;

import java.util.Date;
import java.util.Random;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.MicroPostVenta.model.Cliente;
import com.example.MicroPostVenta.model.CuponDescuento;
import com.example.MicroPostVenta.model.Pedido;
import com.example.MicroPostVenta.model.Tienda;
import com.example.MicroPostVenta.model.TipoVenta;
import com.example.MicroPostVenta.repository.ClienteRepository;
import com.example.MicroPostVenta.repository.CuponDescuentoRepository;
import com.example.MicroPostVenta.repository.DevolucionReclamoRepository;
import com.example.MicroPostVenta.repository.PedidoRepository;
import com.example.MicroPostVenta.repository.ResenaCalificacionRepository;
import com.example.MicroPostVenta.repository.TiendaRepository;
import com.example.MicroPostVenta.repository.TipoVentaRepository;

@Profile("dev")
@Component
public class DetaLoader  implements CommandLineRunner {

    @Autowired
    private  ClienteRepository clienteRepository;
    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;
    @Autowired
    private DevolucionReclamoRepository devolucionReclamoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ResenaCalificacionRepository resenaCalificacionRepository;
    @Autowired
    private TiendaRepository tiendaRepository;
    @Autowired
    private TipoVentaRepository tipoVentaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar tipos de venta
        for (int i = 0; i < 3; i++) {
            TipoVenta tipoVenta = new TipoVenta();
            tipoVenta.setIdTipo(i + 1);
            tipoVenta.setNombre(faker.book().genre());
            tipoVentaRepository.save(tipoVenta);
        }

        // Generar clientes
        for (int i = 0; i < 5; i++) {
            Cliente  cliente = new Cliente();
            cliente.setCodigo(faker.code().asin());
            cliente.setNombre(faker.educator().course());
            clienteRepository.save(cliente);
        }

        List<Cliente> clientes = clienteRepository.findAll();

        // Generar cuppones de descuento
        for (int i = 0; i < 10; i++) {
            CuponDescuento cupon = new CuponDescuento();
            cupon.setCodigo(faker.code().ean8());
            cupon.setDescripcion(faker.lorem().sentence());
            cupon.setDescuento(faker.number().numberBetween(5, 50));
            cupon.setFechaExpiracion(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS));
            cupon.setCliente(clientes.get(random.nextInt(clientes.size())));
            cuponDescuentoRepository.save(cupon);
        }
        
        // Generar ventas
        for (int i = 0; i < 10; i++) {
            TipoVenta venta = new TipoVenta();
            venta.setIdTipo(i + 1);
            venta.setNombre(faker.book().genre());
            tipoVentaRepository.save(venta);
        }

        List<Cliente>clientes1 = clienteRepository.findAll();
        List<Tienda> tiendas = tiendaRepository.findAll();

        // Generar pedidos
        for (int i = 0; i < 20; i++) {
            Pedido pedido = new Pedido();
            pedido.setId(i + 1);
            pedido.setCliente(clientes.get(random.nextInt(clientes.size())));
            pedido.setTienda(tiendas.get(random.nextInt(tiendas.size())));
            pedido.setFechaSolicitada(new Date());
            pedido.setFechaSolicitada(new Date());
            pedido.setHoraCierre(new Date(System.currentTimeMillis() + faker.number().numberBetween(3600000, 7200000))); // 1-2 horas más
            pedido.setEstado(faker.number().numberBetween(0, 2));
            pedidoRepository.save(pedido);


    }

   }
}