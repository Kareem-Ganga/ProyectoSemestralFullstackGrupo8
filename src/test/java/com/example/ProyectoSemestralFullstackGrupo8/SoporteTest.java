package com.example.ProyectoSemestralFullstackGrupo8;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Soporte;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.SoporteRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.SoporteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SoporteTest {

    @Mock
    private SoporteRepository soporteRepository;

    @InjectMocks
    private SoporteService soporteService;

    private Soporte soporteMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        soporteMock = new Soporte();
        soporteMock.setId(1);
        soporteMock.setNombre("Soporte Admin");
        soporteMock.setCorreo("soporte@plataforma.com");
        soporteMock.setContraseña("password123");
    }

    @Test
    void testGetAllSoportes() {
        when(soporteRepository.findAll()).thenReturn(List.of(soporteMock));

        List<Soporte> soportes = soporteService.getAllSoportes();

        assertNotNull(soportes);
        assertEquals(1, soportes.size());
        assertEquals("Soporte Admin", soportes.get(0).getNombre());
    }

    @Test
    void testGetSoporteById() {
        when(soporteRepository.findById(1)).thenReturn(Optional.of(soporteMock));

        Soporte soporte = soporteService.getSoporteById(1);

        assertNotNull(soporte);
        assertEquals("soporte@plataforma.com", soporte.getCorreo());
    }

    @Test
    void testAddSoporte() {
        when(soporteRepository.save(soporteMock)).thenReturn(soporteMock);

        Soporte nuevo = soporteService.addSoporte(soporteMock);

        assertNotNull(nuevo);
        assertEquals("Soporte Admin", nuevo.getNombre());
        verify(soporteRepository, times(1)).save(soporteMock);
    }

    @Test
    void testDeleteSoporte() {
        doNothing().when(soporteRepository).deleteById(1);

        soporteService.deleteSoporte(1);

        verify(soporteRepository, times(1)).deleteById(1);
    }
}