package com.example.ProyectoSemestralFullstackGrupo8;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Estudiante;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.EstudianteRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.EstudianteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstudianteTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    private Estudiante estudianteMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        estudianteMock = new Estudiante();
        estudianteMock.setId(1);
        estudianteMock.setUsername("flieguen");
        estudianteMock.setContraseña("1234");
        estudianteMock.setCorreo("flieguen@example.com");
    }

    @Test
    void testGetAllEstudiantes() {
        when(estudianteRepository.findAll()).thenReturn(List.of(estudianteMock));

        List<Estudiante> estudiantes = estudianteService.getAllEstudiantes();

        assertNotNull(estudiantes);
        assertEquals(1, estudiantes.size());
        assertEquals("flieguen", estudiantes.get(0).getUsername());
    }

    @Test
    void testGetEstudianteById() {
        when(estudianteRepository.findById(1)).thenReturn(Optional.of(estudianteMock));

        Estudiante estudiante = estudianteService.getEstudianteById(1);

        assertNotNull(estudiante);
        assertEquals("flieguen", estudiante.getUsername());
    }

    @Test
    void testAddEstudiante() {
        when(estudianteRepository.save(estudianteMock)).thenReturn(estudianteMock);

        Estudiante nuevoEstudiante = estudianteService.addEstudiante(estudianteMock);

        assertNotNull(nuevoEstudiante);
        assertEquals("flieguen", nuevoEstudiante.getUsername());
        verify(estudianteRepository, times(1)).save(estudianteMock);
    }

    @Test
    void testDeleteEstudiante() {
        doNothing().when(estudianteRepository).deleteById(1);

        estudianteService.deleteEstudiante(1);

        verify(estudianteRepository, times(1)).deleteById(1);
    }
}
