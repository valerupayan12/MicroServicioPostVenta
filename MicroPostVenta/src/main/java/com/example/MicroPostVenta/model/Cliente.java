package com.example.MicroPostVenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id; 
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//se conecta con entidad
@Table(name = "cliente")//nombre de la tabla en la base de dstos
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    private int id_cliente;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name="nombre", nullable=false)
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Column(name="email", nullable=false)
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{1,9}$", message = "El teléfono debe contener solo numero y tener como máximo 9 dígitos")
    @Column(name="telefono", nullable=false)
    private String telefono;

    @Column(name="id_comuna", nullable=false)
    private int comuna;

    private String direccion_envio;

    @Column(name="id_genero", nullable=false)
    private int genero;

}
