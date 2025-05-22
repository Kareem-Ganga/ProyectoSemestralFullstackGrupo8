package com.example.ProyectoSemestralFullstackGrupo8.Controller;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Evaluacion;
import com.example.ProyectoSemestralFullstackGrupo8.Service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public List<Evaluacion> getAllEvaluaciones() {
        return evaluacionService.getAllEvaluaciones();
    }

    @GetMapping("/{id}")
    public Optional<Evaluacion> getEvaluacionById(@PathVariable int id) {
        return evaluacionService.getEvaluacionById(id);
    }

    @PostMapping
    public Evaluacion addEvaluacion(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.addEvaluacion(evaluacion);
    }

    @PutMapping("/{id}")
    public Evaluacion updateEvaluacion(@PathVariable int id, @RequestBody Evaluacion evaluacion) {
        return evaluacionService.updateEvaluacion(id, evaluacion);
    }

    @DeleteMapping("/{id}")
    public String deleteEvaluacion(@PathVariable int id) {
        if (evaluacionService.deleteEvaluacion(id)) {
            return "Evaluación eliminada correctamente";
        } else {
            return "No se encontró evaluación con id " + id;
        }
    }

    @GetMapping("/estudiante/{idEstudiante}")
    public List<Evaluacion> getEvaluacionesPorEstudiante(@PathVariable int idEstudiante) {
        return evaluacionService.getEvaluacionesPorEstudiante(idEstudiante);
    }       
}
