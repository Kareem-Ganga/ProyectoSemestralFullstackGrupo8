package com.example.ProyectoSemestralFullstackGrupo8.Assemblers;

import com.example.ProyectoSemestralFullstackGrupo8.Controller.EvaluacionController;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Evaluacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EvaluacionModelAssembler implements RepresentationModelAssembler<Evaluacion, EntityModel<Evaluacion>> {
    @Override
    public EntityModel<Evaluacion> toModel(Evaluacion evaluacion) {
        return EntityModel.of(evaluacion,
                linkTo(methodOn(EvaluacionController.class).getEvaluacionById(evaluacion.getId())).withSelfRel(),
                linkTo(methodOn(EvaluacionController.class).updateEvaluacion(evaluacion.getId(),evaluacion)).withRel("PUT"),
                linkTo(methodOn(EvaluacionController.class).deleteEvaluacion(evaluacion.getId())).withRel("DELETE"),
                linkTo(methodOn(EvaluacionController.class).getAllEvaluaciones()).withRel("GET"));
    }
}
