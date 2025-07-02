package com.example.ProyectoSemestralFullstackGrupo8.Controller;


import com.example.ProyectoSemestralFullstackGrupo8.Assemblers.ContenidoModelAssembler;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Contenido;
import com.example.ProyectoSemestralFullstackGrupo8.Service.ContenidoService;
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
@RequestMapping("/contenidos")
@Tag(name="Controlador de Contenidos",description = "Servicios Rest para gestion de contenidos")

public class ContenidoController {

    @Autowired
    private ContenidoService contenidoService;

    @Autowired
    ContenidoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener los contenidos", description = "Servicio GET para obtener la lista completa de contenidos ingresados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de contenidos"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Contenido>>> getAllContenidos() {
        List<Contenido> lista = contenidoService.getAllContenidos();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar Contenido por ID", description = "Servicio GET para obtener un contenido por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Contenido"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    @Parameter(description = "El ID del contenido",example = "123")
    public ResponseEntity<EntityModel<Contenido>> getContenidoById(@PathVariable int id){
        Contenido con = contenidoService.getContenidoById(id);
        if(con == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else  {
            return new ResponseEntity<>(assembler.toModel(con), HttpStatus.OK);
        }
    }


    @PostMapping
    @Operation(summary = "Agregar Contenido", description = "Servicio POST para agregar nuevos contenidos en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Contenido agregado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contenido.class))),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    public ResponseEntity<Contenido> addContenido(@RequestBody Contenido contenido){
        Contenido nuevoContenido = contenidoService.addContenido(contenido);
        if (nuevoContenido!=null){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Contenido", description = "Servicio UPDATE para actualizar un contenido del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Contenido actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contenido.class))),
            @ApiResponse(responseCode = "204",description = "No se encontraron datos")
    })
    public ResponseEntity<Contenido> updateContenido(@PathVariable int id, @RequestBody Contenido contenido){
        if (contenidoService.getContenidoById(id) != null) {
            contenidoService.updateContenido(id, contenido);
            return new ResponseEntity<>(HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Contenido por ID", description = "Servicio DELETE a un contenido por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Contenido"),
            @ApiResponse(responseCode = "404",description = "No se encontraron datos")
    })
    @Parameter(description = "El Id del contenido", example = "123")
    public ResponseEntity<Void> deleteContenido(@PathVariable int id) {
        if (contenidoService.getContenidoById(id) != null) {
            contenidoService.deleteContenido(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
