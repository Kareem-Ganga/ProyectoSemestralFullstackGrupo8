package com.example.ProyectoSemestralFullstackGrupo8.Service;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Soporte;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoporteService {

    @Autowired
    SoporteRepository soporteRepository;

    public List<Soporte> getAllSoportes(){
        return soporteRepository.findAll();
    }

    public Soporte getSoporteById(int id){
        return soporteRepository.findById(id).get();
    }

    public Soporte addSoporte(Soporte soporte){
        return soporteRepository.save(soporte);
    }

    public void deleteSoporte(int id){
        soporteRepository.deleteById(id);
    }


    public Soporte updateSoporte(int id, Soporte soporte){
        Soporte sop = soporteRepository.findById(id).get();
        sop.setNombre(soporte.getNombre());
        sop.setContraseña(soporte.getContraseña());
        sop.setCorreo(soporte.getCorreo());
        soporteRepository.save(sop);
        return sop;
    }
}
