package com.example.MicroPostVenta.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //se conecta con entidad
@Table(name="venta") //la tabla nombre persona
@Data //antes de data ahora va lo de arriba
@AllArgsConstructor
@NoArgsConstructor

public class Venta {
    @Id
    private int id_venta;
    @ManyToOne
    @JoinColumn(name="id_pedido", nullable=false)
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name="id_tienda", nullable=false)
    private Tienda tienda;
    @ManyToOne
    @JoinColumn(name="id_cliente", nullable=false)
    private Cliente cliente;
    @NotNull(message = "La fecha de venta no puede estar vacía")
    private Date fecha_venta;
    @Min(value = 0, message = "El total neto debe ser mayor o igual a cero")
    private int total_neto;
    @Min(value = 0, message = "El total de descuento debe ser mayor o igual a cero")
    private int descuento_aplicado;
    @NotBlank(message = "El tipo de documento no puede estar vacío")
    private String tipo_documento;
}
