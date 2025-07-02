package com.example.ProyectoSemestralFullstackGrupo8;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Profesor;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.ProfesorRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.ProfesorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfesorTest {
    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProfesorService profesorService;

    @Test
    void FindAllProfesores(){
        List<Profesor> profesores = profesorRepository.findAll();
        assertNotNull(profesores);
        assertEquals(1, profesores.size());
    }

    @Test
    void checkNameProfesor(){
        Profesor profesor = profesorRepository.findById(1).get();
        assertNotNull(profesor);
        assertEquals("Ignacio",profesor.getNombre());
    }

    @Test
    void checkGetAllProfesorService(){
        Mockito.when(profesorService.getAllProfesores()).thenReturn(List.of());
        try {
            mockMvc.perform(get("/profesores")).andExpect(status().isOk()).andExpect(content().string("Retorna la lista completa de Profesores"));
        } catch (Exception e) {
            System.out.println("Error al obtener la lista completa de Profesores"+e.getMessage());
            fail();
        }
    }

}
