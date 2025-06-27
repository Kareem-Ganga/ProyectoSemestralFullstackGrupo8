package com.example.ProyectoSemestralFullstackGrupo8.Assemblers;

import com.example.ProyectoSemestralFullstackGrupo8.Controller.ContenidoController;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Contenido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ContenidoModelAssembler implements RepresentationModelAssembler<Contenido, EntityModel<Contenido>> {
    @Override
    public EntityModel<Contenido> toModel(Contenido contenido) {
        return EntityModel.of(contenido,
                linkTo(methodOn(ContenidoController.class).getContenidoById(contenido.getId())).withSelfRel(),
                linkTo(methodOn(ContenidoController.class).updateContenido(contenido.getId(),contenido)).withRel("PUT"),
                linkTo(methodOn(ContenidoController.class).deleteContenido(contenido.getId())).withRel("DELETE"),
                linkTo(methodOn(ContenidoController.class).getAllContenidos()).withRel("GET"));
    }
}
