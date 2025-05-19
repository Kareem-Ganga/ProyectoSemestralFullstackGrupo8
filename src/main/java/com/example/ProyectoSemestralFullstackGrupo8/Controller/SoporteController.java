package com.example.ProyectoSemestralFullstackGrupo8.Controller;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Soporte;
import com.example.ProyectoSemestralFullstackGrupo8.Service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/soportes")

public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @GetMapping
    public String getAllSoporte(){
        return soporteService.getAllSoportes();
    }

    @PostMapping
    public String addSoporte(@RequestBody Soporte soporte){
        return soporteService.addSoporte(soporte);
    }

    @GetMapping("/{id}")
    public String getSoporteById(@PathVariable int id){
        return soporteService.getSoporteById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteSoporte(@PathVariable int id){
        return soporteService.deleteSoporte(id);
    }

    @PutMapping("/{id}")
    public String updateSoporte(@PathVariable int id, @RequestBody Soporte soporte){
        return soporteService.updateSoporte(id, soporte);
    }
}
