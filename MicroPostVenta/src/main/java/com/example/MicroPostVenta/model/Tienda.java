package com.example.MicroPostVenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //se conecta con entidad
@Table(name="tienda") //la tabla nombre persona
@Data
@AllArgsConstructor
@NoArgsConstructor
    
public class Tienda {
    @Id
    private int id_tienda;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name="nombre", nullable=false)
    private String nombre;
    
    @NotBlank(message = "La dirección no puede estar vacía")
    @Column(name="direccion", nullable=false)
    private String direccion;
    @Column(name="comuna", nullable=false)
    private int comuna;
    @Column(name="region", nullable=false)
    private int region;

}
