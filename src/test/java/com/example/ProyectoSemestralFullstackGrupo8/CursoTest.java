package com.example.ProyectoSemestralFullstackGrupo8;


import com.example.ProyectoSemestralFullstackGrupo8.Model.Curso;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.CursoRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.CursoService;
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
public class CursoTest {
    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CursoService cursoService;

    @Test
    void FindAllCursos(){
        List<Curso> curso = cursoRepository.findAll();
        assertNotNull(curso);
        assertEquals(1,curso.size());
    }

    @Test
    void checkNameCurso(){
        Curso curso = cursoRepository.findById(1).get();
        assertNotNull(curso);
        assertEquals(1,curso.getId());
    }

    @Test
    void checkGetAllCursosService(){
        Mockito.when(cursoService.getAllCursos()).thenReturn(List.of(new Curso()));
        try{
            mockMvc.perform(get("/cursos")).andExpect(status().isOk()).andExpect(content().string("Retorna lista completa de cursos"));
        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }
}
