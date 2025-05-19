package com.example.ProyectoSemestralFullstackGrupo8.Controller;



import com.example.ProyectoSemestralFullstackGrupo8.Model.Estudiante;
import com.example.ProyectoSemestralFullstackGrupo8.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/estudiantes")

public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public String getAllEstudiantes(){
        return estudianteService.getAllEstudiantes();
    }

    @PostMapping
    public String addEstudiante(@RequestBody Estudiante estudiante){
        return estudianteService.addEstudiante(estudiante);
    }

    @GetMapping("/{id}")
    public String getEstudianteById(@PathVariable int id){
        return estudianteService.getEstudianteById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEstudiante(@PathVariable int id){
        return estudianteService.deleteEstudiante(id);
    }

    @PutMapping("/{id}")
    public String updateEstudiante(@PathVariable int id, @RequestBody Estudiante estudiante){
        return estudianteService.updateEstudiante(id, estudiante);
    }
}
