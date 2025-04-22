package com.example.workbeats.Repositorios;

import com.example.workbeats.Modelo.RecetaCafe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioReceta extends JpaRepository<RecetaCafe, Long> {
}
