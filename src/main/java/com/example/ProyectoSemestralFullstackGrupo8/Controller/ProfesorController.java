package com.example.ProyectoSemestralFullstackGrupo8.Controller;



import com.example.ProyectoSemestralFullstackGrupo8.Assemblers.ProfesorModelAssembler;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Profesor;
import com.example.ProyectoSemestralFullstackGrupo8.Service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
@Tag(name="Controlador de Profesores",description = "Servicios Rest para gestion de Profesores")

public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @Autowired
    ProfesorModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Profesores", description = "Servicio GET para obtener la lista de profesores ingresados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna la lista completa de Profesores"),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Profesor>>> getAllProfesores() {
        List<Profesor> profesores = profesorService.getAllProfesores();
        if(profesores.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(assembler.toCollectionModel(profesores), HttpStatus.OK);
        }
    }


    @PostMapping
    @Operation(summary = "Agregar Profesor", description = "Servicio POST para agregar nuevos profesores a la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profesor agregado",
                content = @Content(mediaType = "application/json",
                        schema =@Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos")
    })
    public ResponseEntity<Profesor> addProfesor(@RequestBody Profesor profesor) {
        Profesor profesornuevo = profesorService.addProfesor(profesor);
        if(profesornuevo != null){
            return new ResponseEntity<>(profesornuevo, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Profesores por ID", description = "Servicio GET para obtener a un profesores por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Profesor"),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos")
    })
    @Parameter(description = "El ID del profesor", example = "123")
    public ResponseEntity<EntityModel<Profesor>> getProfesorById(@PathVariable int id) {
        Profesor profesor = profesorService.getProfesorById(id);
        if(profesor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else  {
            return new ResponseEntity<>(assembler.toModel(profesor), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar profesor por ID", description = "Servicio DELETE para eliminar a un profesor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Profesor"),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos")
    })
    @Parameter(description = "El ID del profesor",example = "123")
    public ResponseEntity<Void> deleteProfesor(@PathVariable int id) {
        if (profesorService.getProfesorById(id) != null) {
            profesorService.deleteProfesorById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar profesor", description = "Servicio UPDATE para actualizar a un profesor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profesor actualizado",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "204", description = "No se encontraron datos")
    })
    public ResponseEntity<Profesor> updateProfesor(@PathVariable int id, @RequestBody Profesor profesor) {
        if (profesorService.getProfesorById(id) != null) {
            profesorService.updateProfesor(id, profesor);
            return new ResponseEntity<>(profesor, HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
