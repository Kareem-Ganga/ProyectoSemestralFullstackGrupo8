package com.example.ProyectoSemestralFullstackGrupo8.Service;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Contenido;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContenidoService {

    @Autowired
    ContenidoRepository contenidoRepository;

    public List<Contenido> getAllContenidos(){
        return contenidoRepository.findAll();
    }

    public Contenido getContenidoById(int id){
        return contenidoRepository.findById(id).get();
    }

    public Contenido addContenido(Contenido contenido){
        return contenidoRepository.save(contenido);
    }

    public Contenido updateContenido(int id, Contenido contenido){
        Contenido con = contenidoRepository.findById(id).get();
        con.setTitulo(contenido.getTitulo());
        con.setDescripcion(contenido.getDescripcion());
        con.setUrlMaterial(contenido.getUrlMaterial());
        contenidoRepository.save(con);
        return con;
    }


    public void deleteContenido(int id){
        contenidoRepository.deleteById(id);
    }
}
