package com.example.workbeats.Repositorios;

import com.example.workbeats.Modelo.Tarea;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * es el repositorio para el manejo de las tareas
 */
public interface RepositorioTarea extends JpaRepository<Tarea, Long> {

}
