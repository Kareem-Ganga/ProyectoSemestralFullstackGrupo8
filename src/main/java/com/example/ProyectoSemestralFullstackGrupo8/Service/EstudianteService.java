package com.example.ProyectoSemestralFullstackGrupo8.Service;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Estudiante;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class EstudianteService {


    @Autowired
    EstudianteRepository estudianteRepository;

    public List<Estudiante> getAllEstudiantes(){
        return estudianteRepository.findAll();
    }

    public Estudiante getEstudianteById(int id){
        return estudianteRepository.findById(id).get();
    }

    public Estudiante addEstudiante(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    public void deleteEstudiante(int id){
        estudianteRepository.deleteById(id);
    }


    public Estudiante updateEstudiante(int id, Estudiante estudiante){
        Estudiante est = estudianteRepository.findById(id).get();
        est.setUsername(estudiante.getUsername());
        est.setContraseña(estudiante.getContraseña());
        est.setCorreo(estudiante.getCorreo());
        estudianteRepository.save(est);
        return est;
    }
}
