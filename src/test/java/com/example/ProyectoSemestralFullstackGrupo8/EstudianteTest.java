package com.example.ProyectoSemestralFullstackGrupo8;


import com.example.ProyectoSemestralFullstackGrupo8.Model.Estudiante;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.EstudianteRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.EstudianteService;
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
public class EstudianteTest {

    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EstudianteService estudianteService;

    @Test
    void FindAllEstudiantes(){
        List<Estudiante> estudiantes = estudianteRepository.findAll();

        assertNotNull(estudiantes);
        assertEquals(1,estudiantes.size());

    }

    @Test
    void checkNameEstudiante(){
        Estudiante estudiante = estudianteRepository.findById(1).get();
        assertNotNull(estudiante);
        assertEquals("Jose",estudiante.getUsername());
    }

    @Test
    void checkGetAllEstudianteService(){
        Mockito.when(estudianteService.getAllEstudiantes()).thenReturn(List.of(new Estudiante(1, "Jose","12345","jose@gmail.com")));

        try {
            mockMvc.perform(get("/estudiantes"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Retorna lista completa de estudiantes"));
        } catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

}
