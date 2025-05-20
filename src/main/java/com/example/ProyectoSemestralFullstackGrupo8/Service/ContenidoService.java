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
    private ContenidoRepository contenidoRepository;

    public List<Contenido> getAllContenidos() {
        return contenidoRepository.findAll();
    }

    public Optional<Contenido> getContenidoById(int id) {
        return contenidoRepository.findById(id);
    }

    public Contenido addContenido(Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    public Contenido updateContenido(int id, Contenido contenidoNuevo) {
        Optional<Contenido> opt = contenidoRepository.findById(id);
        if(opt.isPresent()) {
            Contenido contenido = opt.get();
            contenido.setTitulo(contenidoNuevo.getTitulo());
            contenido.setDescripcion(contenidoNuevo.getDescripcion());
            contenido.setUrlMaterial(contenidoNuevo.getUrlMaterial());
            contenido.setPublicado(contenidoNuevo.isPublicado());
            contenido.setCurso(contenidoNuevo.getCurso());
            contenido.setProfesor(contenidoNuevo.getProfesor());
            return contenidoRepository.save(contenido);
        } else {
            return null;
        }
    }

    public boolean deleteContenido(int id) {
        if(contenidoRepository.existsById(id)) {
            contenidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
