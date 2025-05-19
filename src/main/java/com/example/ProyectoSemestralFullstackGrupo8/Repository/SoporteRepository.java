package com.example.ProyectoSemestralFullstackGrupo8.Repository;
import com.example.ProyectoSemestralFullstackGrupo8.Model.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoporteRepository extends JpaRepository<Soporte, Integer> {
}
