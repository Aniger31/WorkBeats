package com.example.workbeats.Repositorios;

import com.example.workbeats.Modelo.RecetaCafe;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * es el repositorio para el manejo de las recetas
 */
public interface RepositorioReceta extends JpaRepository<RecetaCafe, Long> {
}
