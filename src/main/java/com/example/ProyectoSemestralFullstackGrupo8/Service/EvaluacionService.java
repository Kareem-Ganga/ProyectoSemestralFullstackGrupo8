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
    EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> getAllEvaluaciones(){
        return evaluacionRepository.findAll();
    }

    public Evaluacion getEvaluacionById(int id){
        return evaluacionRepository.findById(id).get();
    }


    public Evaluacion addEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }


    public Evaluacion updateEvaluacion(int id, Evaluacion evaluacion) {
        Evaluacion ev = evaluacionRepository.findById(id).get();
        ev.setTitulo(evaluacion.getTitulo());
        ev.setDescripcion(evaluacion.getDescripcion());
        ev.setEstado(evaluacion.getEstado());
        ev.setRetroalimentacion(evaluacion.getRetroalimentacion());
        evaluacionRepository.save(ev);
        return ev;
    }



    public void deleteEvaluacion(int id){
        evaluacionRepository.deleteById(id);
    }

    public List<Evaluacion> getEvaluacionesPorEstudiante(int idEstudiante) {
        return evaluacionRepository.findByIdEstudiante(idEstudiante);
    }


}
