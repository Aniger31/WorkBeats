package com.example.workbeats.Repositorios;

import com.example.workbeats.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String nombre);
}
