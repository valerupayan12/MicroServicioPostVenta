package com.example.MicroPostVenta.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //se conecta con entidad
@Table(name="resenacalificacion") //la tabla nombre persona
@Data //antes de data ahora va lo de arriba
@AllArgsConstructor
@NoArgsConstructor

public class ResenaCalificacion {
    @Id
    private int id_resena;
    @ManyToOne
    @JoinColumn(name="id_cliente", nullable=false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_producto", nullable=false)
    private Producto producto;
    
    @NotBlank(message = "La motivo no puede estar vacía")
    @Column(name="motivo", nullable=false)
    private String motivo;
    @Column(name="estado", nullable=false)
    private boolean estado;
    @NotBlank(message = "La fecha no puede estar vacía")
    private Date fecha;

}
