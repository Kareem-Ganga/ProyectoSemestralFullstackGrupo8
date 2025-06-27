package com.example.ProyectoSemestralFullstackGrupo8.Assemblers;

import com.example.ProyectoSemestralFullstackGrupo8.Controller.SoporteController;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Soporte;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;



@Component
public class SoporteModelAssembler implements RepresentationModelAssembler<Soporte, EntityModel<Soporte>> {
    @Override
    public EntityModel<Soporte> toModel(Soporte soporte) {
        return EntityModel.of(soporte,
                linkTo(methodOn(SoporteController.class).getSoporteById(soporte.getId())).withSelfRel(),
                linkTo(methodOn(SoporteController.class).updateSoporte(soporte.getId(),soporte)).withRel("PUT"),
                linkTo(methodOn(SoporteController.class).deleteSoporte(soporte.getId())).withRel("DELETE"),
                linkTo(methodOn(SoporteController.class).getAllSoportes()).withRel("GET"));
    }
}