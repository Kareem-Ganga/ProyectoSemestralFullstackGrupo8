package com.example.ProyectoSemestralFullstackGrupo8.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Administrador {
    private int id;
    private String contraseña;
}
