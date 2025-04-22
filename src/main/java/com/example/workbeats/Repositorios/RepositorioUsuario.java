package com.example.workbeats.Repositorios;

import com.example.workbeats.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
}
