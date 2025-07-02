package com.example.ProyectoSemestralFullstackGrupo8;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Evaluacion;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.EvaluacionRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.EvaluacionService;
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
public class EvaluacionTest {
    @Autowired
    EvaluacionRepository evaluacionRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    EvaluacionService evaluacionService;

    @Test
    void FindAllEvaluaciones(){
        List<Evaluacion> evaluaciones = evaluacionRepository.findAll();
        assertNotNull(evaluaciones);
        assertEquals(1, evaluaciones.size());
    }

    @Test
    void checkEvaluaciones(){
        Evaluacion evaluacion = evaluacionRepository.findById(1).get();
        assertNotNull(evaluacion);
        assertEquals("Prueba1_Mate",evaluacion.getTitulo());
    }

    @Test
    void checkGetEvaluaciones(){
        Mockito.when(evaluacionService.getAllEvaluaciones()).thenReturn(List.of());
        try{
            mockMvc.perform(get("/evaluaciones")).andExpect(status().isOk()).andExpect(content().string("Retorna lista completa de evaluaciones"));
        }catch (Exception e){
            System.out.println("Error al obtener evaluaciones"+e.getMessage());
            fail();
        }
    }
}
