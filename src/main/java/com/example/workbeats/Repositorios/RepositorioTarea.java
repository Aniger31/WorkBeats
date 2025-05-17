package com.example.workbeats.Repositorios;

import com.example.workbeats.Modelo.Tarea;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioTarea extends JpaRepository<Tarea, Long> {

}
