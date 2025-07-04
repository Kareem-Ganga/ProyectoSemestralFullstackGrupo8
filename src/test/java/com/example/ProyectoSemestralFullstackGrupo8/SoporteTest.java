package com.example.ProyectoSemestralFullstackGrupo8;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Soporte;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.SoporteRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.SoporteService;
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
public class SoporteTest {
    @Autowired
    SoporteRepository soporteRepository;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SoporteService soporteService;

    @Test
    void FindAllSoporte(){
        List<Soporte> soportes=soporteRepository.findAll();
        assertNotNull(soportes);
        assertEquals(1,soportes.size());
    }

    @Test
    void checkNameSoporte(){
        Soporte soporte=soporteRepository.findById(1).get();
        assertNotNull(soporte);
        assertEquals("Pepito",soporte.getNombre());
    }

    @Test
    void checkGetAllSoporteService(){
        Mockito.when(soporteService.getAllSoportes()).thenReturn(List.of(new Soporte()));

        try{
            mockMvc.perform(get("/soportes")).andExpect(status().isOk()).andExpect(content().string("Retorna lista completa de soportes"));
        } catch (Exception e) {
            System.out.println("Error al obtener la lista completa de soportes"+e.getMessage());
            fail();
        }
    }
}
