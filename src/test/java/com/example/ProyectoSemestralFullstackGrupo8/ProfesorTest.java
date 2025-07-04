package com.example.ProyectoSemestralFullstackGrupo8;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Profesor;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.ProfesorRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.ProfesorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfesorTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorService profesorService;

    private Profesor profesorMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        profesorMock = new Profesor();
        profesorMock.setId(1);
        profesorMock.setNombre("Juan Pérez");
        profesorMock.setCorreo("juan.perez@example.com");
        profesorMock.setContraseña("secure123");
    }

    @Test
    void testGetAllProfesores() {
        when(profesorRepository.findAll()).thenReturn(List.of(profesorMock));

        List<Profesor> profesores = profesorService.getAllProfesores();

        assertNotNull(profesores);
        assertEquals(1, profesores.size());
        assertEquals("Juan Pérez", profesores.get(0).getNombre());
    }

    @Test
    void testGetProfesorById() {
        when(profesorRepository.findById(1)).thenReturn(Optional.of(profesorMock));

        Profesor profesor = profesorService.getProfesorById(1);

        assertNotNull(profesor);
        assertEquals("juan.perez@example.com", profesor.getCorreo());
    }

    @Test
    void testAddProfesor() {
        when(profesorRepository.save(profesorMock)).thenReturn(profesorMock);

        Profesor nuevo = profesorService.addProfesor(profesorMock);

        assertNotNull(nuevo);
        assertEquals("Juan Pérez", nuevo.getNombre());
        verify(profesorRepository, times(1)).save(profesorMock);
    }

    @Test
    void testDeleteProfesorById() {
        doNothing().when(profesorRepository).deleteById(1);

        profesorService.deleteProfesorById(1);

        verify(profesorRepository, times(1)).deleteById(1);
    }
}