package com.example.ProyectoSemestralFullstackGrupo8.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String descripcion;
    private String estado;
    private String retroalimentacion;

    private int idEstudiante;
}
