package com.example.ProyectoSemestralFullstackGrupo8;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Contenido;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.ContenidoRepository;
import com.example.ProyectoSemestralFullstackGrupo8.Service.ContenidoService;
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
public class ContenidoTest {

    @Autowired
    ContenidoRepository contenidoRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ContenidoService contenidoService;

    @Test
    void FindAllContenidos(){
        List<Contenido> contenidos = contenidoRepository.findAll();
        assertNotNull(contenidos);
        assertEquals(1,contenidos.size());
    }

    @Test
    void checkNameContenido(){
        Contenido contenido = contenidoRepository.findById(1).get();
        assertNotNull(contenido);
        assertEquals("Matematicas",contenido.getTitulo());
    }

    @Test
    void checkGetAllContenidoService(){
        Mockito.when(contenidoService.getAllContenidos()).thenReturn(List.of(new Contenido()));
        try{
            mockMvc.perform(get("/contenidos")).andExpect(status().isOk()).andExpect(content().string("Retorna lista completa de contenidos"));
        } catch (Exception e) {
            System.out.println("Error al obtener la lista completa de contenidos"+e.getMessage());
            fail();
        }
    }
}
