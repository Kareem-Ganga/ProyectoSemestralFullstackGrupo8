package com.example.ProyectoSemestralFullstackGrupo8.Service;


import com.example.ProyectoSemestralFullstackGrupo8.Model.Curso;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public String getAllCursos(){
        String output="";

        for(Curso curso:cursoRepository.findAll()){
            output+="Id del Curso: "+curso.getId()+"\n";
            output+="El Titulo del Curso es: "+curso.getTitulo()+"\n";
            output+="Descripcion: "+curso.getDescripcion()+"\n";
            output+="Reseñas: "+curso.getReseña()+"\n";
        }
        if(output.isEmpty()){
            return "No hay Cursos Registrados";
        }else {
            return output;
        }
    }

    public String getCursoById(int id){
        String output="";

        if (cursoRepository.existsById(id)){
            Curso buscado=cursoRepository.findById(id).get();
            output+="Id del Curso: "+buscado.getId()+"\n";
            output+="El Titulo del Curso es: "+buscado.getTitulo()+"\n";
            output+="Descripcion: "+buscado.getDescripcion()+"\n";
            output+="Reseñas: "+buscado.getReseña()+"\n";
            return output;
        }else{
            return "El Curso No fue Encontrado";
        }
    }

    public String addCurso(Curso curso){

        if(!cursoRepository.existsById(curso.getId())){
            cursoRepository.save(curso);
            return "Curso Agregado Correctamente";
        }else {
            return "Curso ya existente";
        }
    }

    public String deleteCurso(int id){
        if (cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
            return "Curso Eliminado";
        }else{
            return "Curso No Existente";
        }
    }


    public String updateCurso(int id, Curso curso){
        if (cursoRepository.existsById(id)){
            Curso buscado=cursoRepository.findById(id).get();
            buscado.setTitulo(curso.getTitulo());
            buscado.setDescripcion(curso.getDescripcion());
            buscado.setReseña(curso.getReseña());
            cursoRepository.save(buscado);
            return "Curso Actualizado";
        }else {
            return "No se encontro el Curso";
        }
    }
}
