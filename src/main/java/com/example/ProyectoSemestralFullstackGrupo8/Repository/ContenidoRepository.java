package com.example.ProyectoSemestralFullstackGrupo8.Repository;

import com.example.ProyectoSemestralFullstackGrupo8.Model.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Integer> {
}
