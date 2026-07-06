package com.example.MicroPostVenta.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //se conecta con entidad
@Table(name="pedido") //la tabla nombre persona
@Data //antes de data ahora va lo de arriba
@AllArgsConstructor
@NoArgsConstructor

public class Pedido {
    @Id
    private int id_pedido;
    @ManyToOne
    @JoinColumn(name="id_cliente", nullable=false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_tienda", nullable=false)
    private Tienda tienda;
    @Column(name="estado", nullable=false)
    private boolean estado;
    @ManyToOne
    @JoinColumn(name="id_cupon", nullable=false)
    private CuponDescuento cupondescuento;
    @NotNull(message = "La fecha de pedido no puede estar vacía")
    @Column(name="fecha_pedido", nullable=false)
    private Date fecha_pedido;
}
