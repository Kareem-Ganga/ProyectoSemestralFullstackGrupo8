package com.example.ProyectoSemestralFullstackGrupo8.Service;


import com.example.ProyectoSemestralFullstackGrupo8.Model.Profesor;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {
    @Autowired
    ProfesorRepository profesorRepository;

    public List<Profesor> getAllProfesores(){
        return profesorRepository.findAll();
    }

    public Profesor getProfesorById(int id){
        return profesorRepository.findById(id).get();
    }


    public Profesor addProfesor(Profesor profesor){
        return profesorRepository.save(profesor);
    }

    public void deleteProfesorById(int id){
        profesorRepository.deleteById(id);
    }

    public Profesor updateProfesor(int id,Profesor profesor){
        Profesor pro = profesorRepository.findById(id).get();
        pro.setNombre(profesor.getNombre());
        pro.setCorreo(profesor.getCorreo());
        pro.setContraseña(profesor.getContraseña());
        profesorRepository.save(pro);
        return pro;
    }

}
