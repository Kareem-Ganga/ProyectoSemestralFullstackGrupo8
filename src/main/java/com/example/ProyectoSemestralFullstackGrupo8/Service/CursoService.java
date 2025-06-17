package com.example.ProyectoSemestralFullstackGrupo8.Service;


import com.example.ProyectoSemestralFullstackGrupo8.Model.Curso;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;


    public List<Curso> getAllCursos(){
        return cursoRepository.findAll();
    }


    public Curso getCursoById(int id){
        return cursoRepository.findById(id).get();
    }


    public Curso addCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public void deleteCurso(int id){
       cursoRepository.deleteById(id);
    }

    public Curso updateCurso(int id, Curso curso){
        Curso cur = cursoRepository.findById(id).get();
        cur.setTitulo(curso.getTitulo());
        cur.setDescripcion(curso.getDescripcion());
        cur.setReseña(curso.getReseña());
        cursoRepository.save(cur);
        return cur;
    }
}
