package com.example.ProyectoSemestralFullstackGrupo8.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private int id;
    private String username;
    private String contraseña;
    private String correo;
}
