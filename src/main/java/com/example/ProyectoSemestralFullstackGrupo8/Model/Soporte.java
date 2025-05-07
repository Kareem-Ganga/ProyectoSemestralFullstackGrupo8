package com.example.ProyectoSemestralFullstackGrupo8.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Soporte {
    private int id;
    private String nombre;
    private String contraseña;
    private String correo;
}
