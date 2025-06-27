package com.example.ProyectoSemestralFullstackGrupo8.Assemblers;


import com.example.ProyectoSemestralFullstackGrupo8.Controller.EstudianteController;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Estudiante;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EstudianteModelAssembler implements RepresentationModelAssembler<Estudiante, EntityModel<Estudiante>> {
    @Override
    public EntityModel<Estudiante> toModel(Estudiante estudiante) {
        return EntityModel.of(estudiante,
                linkTo(methodOn(EstudianteController.class).getEstudianteById(estudiante.getId())).withSelfRel(),
                linkTo(methodOn(EstudianteController.class).updateEstudiante(estudiante.getId(),estudiante)).withRel("PUT"),
                linkTo(methodOn(EstudianteController.class).deleteEstudiante(estudiante.getId())).withRel("DELETE"),
                linkTo(methodOn(EstudianteController.class).getAllEstudiantes()).withRel("GET"));
    }
}
