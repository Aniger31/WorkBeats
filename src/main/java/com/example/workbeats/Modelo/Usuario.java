package com.example.workbeats.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    /**
     * La primaryKey de la tabla de usuarios es el id_usuario
     * tenemos relaciones Uno a Muchos entre usuario y tareas
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //genera la primary key de manera automatica
    private Long id_usuario;

    private String nombre;
    private String email;
    private String password;

    //Establecer relacion entre el usuario y sus tareas (1:M)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Tarea> tareas;
    //cascade = CascadeType.ALL: al guardar, actualizar o eliminar un usuario, hace lo mismo con sus tareas.
    //
    //orphanRemoval = true: si eliminas una tarea de la lista tareas, también se borra de la BD.

    /*Getters y Setters*/

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }


}
