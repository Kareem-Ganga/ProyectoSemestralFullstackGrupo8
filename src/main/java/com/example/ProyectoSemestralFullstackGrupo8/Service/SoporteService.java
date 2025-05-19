package com.example.ProyectoSemestralFullstackGrupo8.Service;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Soporte;
import com.example.ProyectoSemestralFullstackGrupo8.Repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    public String getAllSoportes(){
        String output="";

        for(Soporte soporte:soporteRepository.findAll()){
            output+="Id del Soporte es: "+soporte.getId()+"\n";
            output+="El Nombre del Soporte es: "+soporte.getNombre()+"\n";
            output+="El correo del Soporte es: "+soporte.getCorreo()+"\n";
        }
        if(output.isEmpty()){
            return "No existen soportes";
        }else {
            return output;
        }
    }

    public String getSoporteById(int id){
        String output="";
        if (soporteRepository.existsById(id)){
            Soporte soport=soporteRepository.findById(id).get();
            output+="El id del Soporte es: "+soport.getId()+"\n";
            output+="El Nombre del Soporte es: "+soport.getNombre()+"\n";
            output+="El correo del Soporte es: "+soport.getCorreo()+"\n";
            return output;
        }else{
            return "El Id del soporte no existe";
        }
    }

    public String addSoporte(Soporte soporte){

        if(!soporteRepository.existsById(soporte.getId())){
            soporteRepository.save(soporte);
            return "Nuevo soporte añadido correctamente";
        }else {
            return "El soporte ya existe";
        }
    }

    public String deleteSoporte(int id){
        if (soporteRepository.existsById(id)){
            soporteRepository.deleteById(id);
            return "Se revocaron los permisos del soporte";
        }else{
            return "Soporte no existente";
        }
    }


    public String updateSoporte(int id, Soporte soporte){
        if (soporteRepository.existsById(id)){
            Soporte soport=soporteRepository.findById(id).get();
            soport.setNombre(soporte.getNombre());
            soport.setCorreo(soporte.getCorreo());
            soporteRepository.save(soport);
            return "Estudiante Actualizado correctamente";
        }else {
            return "No se pudo encontrar al estudiante";
        }
    }

}
