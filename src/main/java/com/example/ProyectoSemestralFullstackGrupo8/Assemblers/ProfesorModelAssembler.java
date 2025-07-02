package com.example.ProyectoSemestralFullstackGrupo8.Assemblers;


import com.example.ProyectoSemestralFullstackGrupo8.Controller.ProfesorController;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Profesor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;




@Component
public class ProfesorModelAssembler implements RepresentationModelAssembler<Profesor, EntityModel<Profesor>> {
    @Override
    public EntityModel<Profesor> toModel(Profesor profesor) {
        return EntityModel.of(profesor,
                linkTo(methodOn(ProfesorController.class).getProfesorById(profesor.getId())).withSelfRel(),
                linkTo(methodOn(ProfesorController.class).updateProfesor(profesor.getId(), profesor)).withRel("PUT"),
                linkTo(methodOn(ProfesorController.class).deleteProfesor(profesor.getId())).withRel("DELETE"),
                linkTo(methodOn(ProfesorController.class).getAllProfesores()).withRel("GET"));
    }
}
