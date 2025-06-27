package com.example.ProyectoSemestralFullstackGrupo8.Controller;

import com.example.ProyectoSemestralFullstackGrupo8.Assemblers.CursoModelAssembler;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Curso;
import com.example.ProyectoSemestralFullstackGrupo8.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


@RestController
@RequestMapping("/cursos")
@Tag(name="Controlador de Cursos",description = "Servicios Rest para gestion de cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    CursoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener los cursos", description = "Servicio GET para obtener la lista completa de cursos ingresados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de cursos"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Curso>>> getAllCursos(){
        List<Curso> lista = cursoService.getAllCursos();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar Curso por ID", description = "Servicio GET para obtener un curso por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Curso"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    @Parameter(description = "El ID del curso",example = "123")
    public ResponseEntity<EntityModel<Curso>> getCursoById(@PathVariable int id){
        Curso cur = cursoService.getCursoById(id);
        if(cur == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else  {
            return new ResponseEntity<>(assembler.toModel(cur), HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Curso", description = "Servicio POST para agregar nuevos cursos en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso agregado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<Curso> addCurso(@RequestBody Curso curso){
        Curso nuevoCurso = cursoService.addCurso(curso);
        if (nuevoCurso!=null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Curso por ID", description = "Servicio DELETE a un curso por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Curso"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    @Parameter(description = "El Id del curso", example = "123")
    public ResponseEntity<Void> deleteCursoById(@PathVariable int id) {
        if (cursoService.getCursoById(id) != null) {
            cursoService.deleteCurso(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Curso", description = "Servicio UPDATE para actualizar un Curso del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso agregado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "204",description = "No se encontraron datos")
    })
    public ResponseEntity<Curso> updateCurso(@PathVariable int id, @RequestBody Curso curso){
        if (cursoService.getCursoById(id) != null) {
            cursoService.updateCurso(id, curso);
            return new ResponseEntity<>(HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
