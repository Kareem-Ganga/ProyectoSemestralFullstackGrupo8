package com.example.ProyectoSemestralFullstackGrupo8.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.example.ProyectoSemestralFullstackGrupo8.Assemblers.EstudianteModelAssembler;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Estudiante;
import com.example.ProyectoSemestralFullstackGrupo8.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/estudiantes")
@Tag(name="Controlador de Estudiante",description = "Servicios Rest para gestion de estudiantes")



public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    EstudianteModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener estudiantes", description = "Servicio GET para obtener la lista completa de estudiantes ingresados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de cursos"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Estudiante>>> getAllEstudiantes(){
        List<Estudiante> lista = estudianteService.getAllEstudiantes();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }


    @PostMapping
    @Operation(summary = "Agregar Curso", description = "Servicio POST para agregar nuevos estudiantes en la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso agregado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Estudiante.class))),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<Estudiante> addEstudiante(@RequestBody Estudiante curso){
        Estudiante nuevoEstudiante = estudianteService.addEstudiante(curso);
        if (nuevoEstudiante!=null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar Curso por ID", description = "Servicio GET para obtener un estudiante por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Estudiante"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    @Parameter(description = "El ID del estudiante",example = "123")
    public ResponseEntity<EntityModel<Estudiante>> getEstudianteById(@PathVariable int id){
        Estudiante est = estudianteService.getEstudianteById(id);
        if(est == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else  {
            return new ResponseEntity<>(assembler.toModel(est), HttpStatus.OK);
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Estudiante por ID", description = "Servicio DELETE a un estudiante por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Estudiante"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    @Parameter(description = "El Id del Estudiante", example = "123")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable int id) {
        if (estudianteService.getEstudianteById(id) != null) {
            estudianteService.deleteEstudiante(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Estudiante", description = "Servicio UPDATE para actualizar a un estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso agregado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Estudiante.class))),
            @ApiResponse(responseCode = "204",description = "No se encontraron datos")
    })
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable int id, @RequestBody Estudiante estudiante){
        if (estudianteService.getEstudianteById(id) != null) {
            estudianteService.updateEstudiante(id, estudiante);
            return new ResponseEntity<>(HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
