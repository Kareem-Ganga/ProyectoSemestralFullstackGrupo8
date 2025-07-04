package com.example.ProyectoSemestralFullstackGrupo8;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Contenido;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.ContenidoRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.ContenidoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ContenidoTest {

    @Mock
    private ContenidoRepository contenidoRepository;

    @InjectMocks
    private ContenidoService contenidoService;

    private Contenido contenidoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contenidoMock = new Contenido();
        contenidoMock.setId(1);
        contenidoMock.setTitulo("Matematicas");
        contenidoMock.setDescripcion("Clase de álgebra");
        contenidoMock.setUrlMaterial("http://example.com/matematicas");
    }

    @Test
    void testGetAllContenidos() {
        when(contenidoRepository.findAll()).thenReturn(List.of(contenidoMock));

        List<Contenido> contenidos = contenidoService.getAllContenidos();

        assertNotNull(contenidos);
        assertEquals(1, contenidos.size());
        assertEquals("Matematicas", contenidos.get(0).getTitulo());
    }

    @Test
    void testGetContenidoById() {
        when(contenidoRepository.findById(1)).thenReturn(Optional.of(contenidoMock));

        Contenido contenido = contenidoService.getContenidoById(1);

        assertNotNull(contenido);
        assertEquals("Matematicas", contenido.getTitulo());
    }

    @Test
    void testAddContenido() {
        when(contenidoRepository.save(contenidoMock)).thenReturn(contenidoMock);

        Contenido resultado = contenidoService.addContenido(contenidoMock);

        assertNotNull(resultado);
        assertEquals("Matematicas", resultado.getTitulo());
        verify(contenidoRepository, times(1)).save(contenidoMock);
    }

    @Test
    void testDeleteContenido() {
        doNothing().when(contenidoRepository).deleteById(1);

        contenidoService.deleteContenido(1);

        verify(contenidoRepository, times(1)).deleteById(1);
    }
}
