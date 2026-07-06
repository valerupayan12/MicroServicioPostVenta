package com.example.MicroPostVenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {

    @Id
    private int id_producto;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    private String descripcion;

    @NotBlank(message = "La categoría no puede estar vacía")
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Min(value = 0, message = "El precio base no puede ser menor a 0")
    @Column(name = "precio_base", nullable = false)
    private int precio_base;

    @Column(name = "estado", nullable = false)
    private boolean estado;

}
