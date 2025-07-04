package com.example.ProyectoSemestralFullstackGrupo8;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Curso;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.CursoRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.CursoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CursoTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    private Curso cursoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cursoMock = new Curso();
        cursoMock.setId(1);
        cursoMock.setTitulo("Programación");
        cursoMock.setDescripcion("Curso básico de Java");
        cursoMock.setReseña("Excelente introducción a la programación.");
    }

    @Test
    void testGetAllCursos() {
        when(cursoRepository.findAll()).thenReturn(List.of(cursoMock));

        List<Curso> cursos = cursoService.getAllCursos();

        assertNotNull(cursos);
        assertEquals(1, cursos.size());
        assertEquals("Programación", cursos.get(0).getTitulo());
    }

    @Test
    void testGetCursoById() {
        when(cursoRepository.findById(1)).thenReturn(Optional.of(cursoMock));

        Curso curso = cursoService.getCursoById(1);

        assertNotNull(curso);
        assertEquals("Programación", curso.getTitulo());
    }

    @Test
    void testAddCurso() {
        when(cursoRepository.save(cursoMock)).thenReturn(cursoMock);

        Curso nuevoCurso = cursoService.addCurso(cursoMock);

        assertNotNull(nuevoCurso);
        assertEquals("Programación", nuevoCurso.getTitulo());
        verify(cursoRepository, times(1)).save(cursoMock);
    }

    @Test
    void testDeleteCurso() {
        doNothing().when(cursoRepository).deleteById(1);

        cursoService.deleteCurso(1);

        verify(cursoRepository, times(1)).deleteById(1);
    }
}
