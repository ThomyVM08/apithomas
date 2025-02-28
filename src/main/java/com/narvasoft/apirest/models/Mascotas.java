package com.narvasoft.apirest.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "mascotas")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Mascotas  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_mascota")
    private String nombre;

    @Column(name = "raza")
    private String raza;

    @Column(name = "edad")
    private String edad;


    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "foto")
    private String foto;
}
