package com.example.ProyectoSemestralFullstackGrupo8.Service;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Evaluacion;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> getAllEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public Optional<Evaluacion> getEvaluacionById(int id) {
        return evaluacionRepository.findById(id);
    }

    public Evaluacion addEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion updateEvaluacion(int id, Evaluacion nuevaEvaluacion) {
        Optional<Evaluacion> optional = evaluacionRepository.findById(id);
        if (optional.isPresent()) {
            Evaluacion evaluacion = optional.get();
            evaluacion.setTitulo(nuevaEvaluacion.getTitulo());
            evaluacion.setDescripcion(nuevaEvaluacion.getDescripcion());
            evaluacion.setEstado(nuevaEvaluacion.getEstado());
            evaluacion.setRetroalimentacion(nuevaEvaluacion.getRetroalimentacion());
            evaluacion.setIdEstudiante(nuevaEvaluacion.getIdEstudiante());
            return evaluacionRepository.save(evaluacion);
        }
        return null;
    }

    public boolean deleteEvaluacion(int id) {
        if (evaluacionRepository.existsById(id)) {
            evaluacionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Evaluacion> getEvaluacionesPorEstudiante(int idEstudiante) {
        return evaluacionRepository.findByIdEstudiante(idEstudiante);
    }
}
