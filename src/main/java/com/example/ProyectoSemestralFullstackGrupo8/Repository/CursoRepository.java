package com.example.ProyectoSemestralFullstackGrupo8.Repository;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
