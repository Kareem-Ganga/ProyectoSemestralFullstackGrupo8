package com.example.ProyectoSemestralFullstackGrupo8.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Estudiante {

    private int id;
    private String username;
    private String contraseña;
    private String correo;
}
