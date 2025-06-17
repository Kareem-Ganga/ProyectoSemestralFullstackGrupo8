package com.example.ProyectoSemestralFullstackGrupo8.Assemblers;


import com.example.ProyectoSemestralFullstackGrupo8.Controller.CursoController;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {
    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
                linkTo(methodOn(CursoController.class).getCursoById(curso.getId())).withSelfRel(),
                linkTo(methodOn(CursoController.class).updateCurso(curso.getId(),curso)).withRel("PUT"),
                linkTo(methodOn(CursoController.class).deleteCursoById(curso.getId())).withRel("DELETE"),
                linkTo(methodOn(CursoController.class).getAllCursos()).withRel("GET"));
    }
}
