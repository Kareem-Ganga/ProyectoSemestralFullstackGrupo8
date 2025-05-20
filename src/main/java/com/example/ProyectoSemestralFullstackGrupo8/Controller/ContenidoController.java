package com.example.ProyectoSemestralFullstackGrupo8.Controller;


import com.example.ProyectoSemestralFullstackGrupo8.Model.Contenido;
import com.example.ProyectoSemestralFullstackGrupo8.Service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService contenidoService;

    @GetMapping
    public List<Contenido> getAllContenidos() {
        return contenidoService.getAllContenidos();
    }

    @GetMapping("/{id}")
    public Optional<Contenido> getContenidoById(@PathVariable int id) {
        return contenidoService.getContenidoById(id);
    }

    @PostMapping
    public Contenido addContenido(@RequestBody Contenido contenido) {
        return contenidoService.addContenido(contenido);
    }

    @PutMapping("/{id}")
    public Contenido updateContenido(@PathVariable int id, @RequestBody Contenido contenido) {
        return contenidoService.updateContenido(id, contenido);
    }

    @DeleteMapping("/{id}")
    public String deleteContenido(@PathVariable int id) {
        if(contenidoService.deleteContenido(id)) {
            return "Contenido eliminado correctamente";
        } else {
            return "No se encontró contenido con id " + id;
        }
    }
}
