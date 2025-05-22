package com.example.workbeats.Repositorios;

import com.example.workbeats.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Es la interface para manejar los usuarios
 */
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    /**
     * @param nombre es el nombre que vaa buscar
     * @return   la info del Usuario
     */
    Optional<Usuario> findByNombre(String nombre);
}
