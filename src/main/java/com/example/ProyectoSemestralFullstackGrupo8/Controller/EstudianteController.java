package com.example.ProyectoSemestralFullstackGrupo8.Controller;



import com.example.ProyectoSemestralFullstackGrupo8.Model.Estudiante;
import com.example.ProyectoSemestralFullstackGrupo8.Service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/estudiantes")
@Tag(name="Estudiantes",description = "Servicio de gestion de estudiantes")

public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    @Operation(summary = "Obtener estudiantes", description = "Servicio GET para obtener la lista completa de estudiantes ingresados")
    public String getAllEstudiantes(){
        return estudianteService.getAllEstudiantes();
    }

    @PostMapping
    @Operation(summary = "Agregar Estudiante", description = "Servicio POST para agregar nuevos estudiantes en la lista")
    public String addEstudiante(@RequestBody Estudiante estudiante){
        return estudianteService.addEstudiante(estudiante);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener estudiante por ID",description = "Servicio GET para obtener un estudiante por su ID")
    public String getEstudianteById(@PathVariable int id){
        return estudianteService.getEstudianteById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estudiante mediante su ID", description = "Servicio DELETE a un estudiante por su ID")
    public String deleteEstudiante(@PathVariable int id){
        return estudianteService.deleteEstudiante(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estudiante", description = "Servicio UPDATE para actualizar a un estudiante")
    public String updateEstudiante(@PathVariable int id, @RequestBody Estudiante estudiante){
        return estudianteService.updateEstudiante(id, estudiante);
    }
}
