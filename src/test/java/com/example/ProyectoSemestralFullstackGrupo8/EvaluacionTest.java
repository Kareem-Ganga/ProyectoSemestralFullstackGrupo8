package com.example.ProyectoSemestralFullstackGrupo8;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Evaluacion;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.EvaluacionRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.EvaluacionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EvaluacionTest {

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @InjectMocks
    private EvaluacionService evaluacionService;

    private Evaluacion evaluacionMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        evaluacionMock = new Evaluacion();
        evaluacionMock.setId(1);
        evaluacionMock.setTitulo("Evaluación 1");
        evaluacionMock.setDescripcion("Primera evaluación del curso");
        evaluacionMock.setEstado("Pendiente");
        evaluacionMock.setRetroalimentacion("Buena presentación");
        evaluacionMock.setIdEstudiante(100);
    }

    @Test
    void testGetAllEvaluaciones() {
        when(evaluacionRepository.findAll()).thenReturn(List.of(evaluacionMock));

        List<Evaluacion> evaluaciones = evaluacionService.getAllEvaluaciones();

        assertNotNull(evaluaciones);
        assertEquals(1, evaluaciones.size());
        assertEquals("Evaluación 1", evaluaciones.get(0).getTitulo());
    }

    @Test
    void testGetEvaluacionById() {
        when(evaluacionRepository.findById(1)).thenReturn(Optional.of(evaluacionMock));

        Evaluacion evaluacion = evaluacionService.getEvaluacionById(1);

        assertNotNull(evaluacion);
        assertEquals("Pendiente", evaluacion.getEstado());
    }

    @Test
    void testAddEvaluacion() {
        when(evaluacionRepository.save(evaluacionMock)).thenReturn(evaluacionMock);

        Evaluacion nueva = evaluacionService.addEvaluacion(evaluacionMock);

        assertNotNull(nueva);
        assertEquals("Evaluación 1", nueva.getTitulo());
        verify(evaluacionRepository, times(1)).save(evaluacionMock);
    }

    @Test
    void testGetEvaluacionesPorEstudiante() {
        when(evaluacionRepository.findByIdEstudiante(100)).thenReturn(List.of(evaluacionMock));

        List<Evaluacion> lista = evaluacionService.getEvaluacionesPorEstudiante(100);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertEquals(100, lista.get(0).getIdEstudiante());
    }
}