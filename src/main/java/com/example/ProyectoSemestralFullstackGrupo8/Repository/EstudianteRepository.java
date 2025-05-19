package com.example.ProyectoSemestralFullstackGrupo8.Repository;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

}
