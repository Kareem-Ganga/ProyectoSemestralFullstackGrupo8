package com.example.ProyectoSemestralFullstackGrupo8.Controller;

import com.example.ProyectoSemestralFullstackGrupo8.Assemblers.EvaluacionModelAssembler;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Evaluacion;
import com.example.ProyectoSemestralFullstackGrupo8.Service.EvaluacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;


@RestController
@RequestMapping("/evaluaciones")
@Tag(name="Evaluaciones",description = "Servicio de gestion de evaluaciones")

public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    EvaluacionModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener las evaluaciones", description = "Servicio GET para obtener la lista completa de evaluaciones ingresadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de evaluaciones"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Evaluacion>>> getAllEvaluaciones(){
        List<Evaluacion> lista = evaluacionService.getAllEvaluaciones();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Evaluacion por ID", description = "Servicio GET para obtener una evaluacion por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Evaluacion"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    @Parameter(description = "El ID de la evaluacion",example = "123")
    public ResponseEntity<EntityModel<Evaluacion>> getEvaluacionById(@PathVariable int id){
        Evaluacion ev = evaluacionService.getEvaluacionById(id);
        if(ev == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else  {
            return new ResponseEntity<>(assembler.toModel(ev), HttpStatus.OK);
        }
    }


    @PostMapping
    @Operation(summary = "Agregar Evaluacion", description = "Servicio POST para agregar nuevas evaluaciones en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Evaluacion agregado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<Evaluacion> addEvaluacion(@RequestBody Evaluacion evaluacion){
        Evaluacion nuevaEvaluacion = evaluacionService.addEvaluacion(evaluacion);
        if (nuevaEvaluacion!=null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Evaluacion", description = "Servicio UPDATE para actualizar una evaluacion del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Evaluacion actualizada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "204",description = "No se encontraron datos")
    })
    public ResponseEntity<Evaluacion> updateEvaluacion(@PathVariable int id, @RequestBody Evaluacion evaluacion) {
        if (evaluacionService.getEvaluacionById(id) != null) {
            evaluacionService.updateEvaluacion(id, evaluacion);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evaluacion por ID", description = "Servicio DELETE a una evaluacion por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Evaluacion"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    @Parameter(description = "El Id de la evaluacion", example = "123")
    public ResponseEntity<Void> deleteEvaluacion(@PathVariable int id) {
        if (evaluacionService.getEvaluacionById(id) != null) {
            evaluacionService.deleteEvaluacion(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/estudiante/{idEstudiante}")
    @Operation(summary = "Obtener la Evaluacion por Id del estudiante", description = "Servicio GET para obtener una evaluacion por la ID del estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna La Id de la evaluacion"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Evaluacion>>> getEvaluacionesPorEstudiante(@PathVariable int idEstudiante) {
        List<Evaluacion> lista = evaluacionService.getEvaluacionesPorEstudiante(idEstudiante);
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }
}
