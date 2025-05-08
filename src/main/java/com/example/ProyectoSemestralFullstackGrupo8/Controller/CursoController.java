package com.example.ProyectoSemestralFullstackGrupo8.Controller;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Curso;
import com.example.ProyectoSemestralFullstackGrupo8.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String getAllCursos(){
        return cursoService.getAllCursos();
    }

    @PostMapping
    public String addCurso(@RequestBody Curso curso){
        return cursoService.addCurso(curso);
    }

    @GetMapping("/{id}")
    public String getCursoById(@PathVariable int id){
        return cursoService.getCursoById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCurso(@PathVariable int id){
        return cursoService.deleteCurso(id);
    }

    @PutMapping("/{id}")
    public String updateCurso(@PathVariable int id, @RequestBody Curso curso){
        return cursoService.updateCurso(id, curso);
    }
}
