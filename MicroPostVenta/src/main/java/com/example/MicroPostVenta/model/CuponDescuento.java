package com.example.MicroPostVenta.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //se conecta con entidad
@Table(name="cupondescuento") //a tabla nombre persona
@Data //antes de data ahora va lo de arriba
@AllArgsConstructor
@NoArgsConstructor

public class CuponDescuento {
    @Id
    private int id_cupon_descuento; //pk
    @Column(name="codigo", nullable =false)
    private int codigo;

    @Min(value = 0, message = "El descuento no puede ser menor a 0")
    @Max(value = 100, message = "El descuento no puede ser mayor a 100")
    private int descuento_pct;

    @Min(value = 0, message = "El monto de descuento no puede ser menor a 0")
    private int descuento_monto;

    @NotNull(message = "La fecha de expiración no puede ser nula")
    private Date fecha_expiracion;
    private boolean activo;
}
