package com.example.ProyectoSemestralFullstackGrupo8.Service;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Estudiante;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    public String getAllEstudiantes(){
        String output="";

        for(Estudiante estudiante:estudianteRepository.findAll()){
            output+="Id del Estudiante: "+estudiante.getId()+"\n";
            output+="El Nombre del estudiante es: "+estudiante.getUsername()+"\n";
            output+="El correo del estudiante es: "+estudiante.getCorreo()+"\n";
        }
        if(output.isEmpty()){
            return "No hay estudiantes registrados";
        }else {
            return output;
        }
    }

    public String getEstudianteById(int id){
        String output="";
        if (estudianteRepository.existsById(id)){
            Estudiante encontrar=estudianteRepository.findById(id).get();
            output+="El id del estudiante es: "+encontrar.getId()+"\n";
            output+="El Nombre del estudiante es: "+encontrar.getUsername()+"\n";
            output+="El correo del estudiante es: "+encontrar.getCorreo()+"\n";
            return output;
        }else{
            return "El Estudiante no fue Encontrado";
        }
    }

    public String addEstudiante(Estudiante estudiante){

        if(!estudianteRepository.existsById(estudiante.getId())){
            estudianteRepository.save(estudiante);
            return "Estudiante añadido a la lista correctamente";
        }else {
            return "El estudiante ya esta en la lista";
        }
    }

    public String deleteEstudiante(int id){
        if (estudianteRepository.existsById(id)){
            estudianteRepository.deleteById(id);
            return "Estudiante eliminado de la lista";
        }else{
            return "Estudiante no existe";
        }
    }


    public String updateEstudiante(int id, Estudiante estudiante){
        if (estudianteRepository.existsById(id)){
            Estudiante encontrado=estudianteRepository.findById(id).get();
            encontrado.setUsername(estudiante.getUsername());
            encontrado.setCorreo(estudiante.getCorreo());
            estudianteRepository.save(encontrado);
            return "Estudiante Actualizado correctamente";
        }else {
            return "No se pudo encontrar al estudiante";
        }
    }
}
