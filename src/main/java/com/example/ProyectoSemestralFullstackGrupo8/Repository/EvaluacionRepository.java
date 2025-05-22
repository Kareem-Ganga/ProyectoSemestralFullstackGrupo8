package com.example.ProyectoSemestralFullstackGrupo8.Repository;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
    List<Evaluacion> findByIdEstudiante(int idEstudiante);
}
