package com.example.ProyectoSemestralFullstackGrupo8.Controller;
import com.example.ProyectoSemestralFullstackGrupo8.Assemblers.SoporteModelAssembler;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Soporte;
import com.example.ProyectoSemestralFullstackGrupo8.Service.SoporteService;
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
@RequestMapping("/soportes")
@Tag(name="Soportes",description = "Servicio de gestion de soportes")

public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @Autowired
    SoporteModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener los Soportes", description = "Servicio GET para obtener la lista completa de soportes ingresados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de soportes"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Soporte>>> getAllSoportes(){
        List<Soporte> lista = soporteService.getAllSoportes();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Soporte", description = "Servicio POST para agregar nuevos soportes en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Soporte agregado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Soporte.class))),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<Soporte> addSoporte(@RequestBody Soporte soporte){
        Soporte nuevoSoporte = soporteService.addSoporte(soporte);
        if (nuevoSoporte!=null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Soporte por ID", description = "Servicio GET para obtener un soporte por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Soporte"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    @Parameter(description = "El ID del Soporte",example = "123")
    public ResponseEntity<EntityModel<Soporte>> getSoporteById(@PathVariable int id){
        Soporte sop = soporteService.getSoporteById(id);
        if(sop == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else  {
            return new ResponseEntity<>(assembler.toModel(sop), HttpStatus.OK);
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Soporte por ID", description = "Servicio DELETE a un soporte por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Soporte"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    @Parameter(description = "El Id del soporte", example = "123")
    public ResponseEntity<Void> deleteSoporte(@PathVariable int id) {
        if (soporteService.getSoporteById(id) != null) {
            soporteService.deleteSoporte(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Soporte", description = "Servicio UPDATE para actualizar un soporte del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Soporte actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Soporte.class))),
            @ApiResponse(responseCode = "204",description = "No se encontraron datos")
    })
    public ResponseEntity<Soporte> updateSoporte(@PathVariable int id, @RequestBody Soporte soporte){
        if (soporteService.getSoporteById(id) != null) {
            soporteService.updateSoporte(id, soporte);
            return new ResponseEntity<>(HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
