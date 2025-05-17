package com.example.workbeats.Modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_Tarea;

    private String titulo;

    private Boolean completada; //True = completada y False = Incompleta
    private String linkSpotify;

    @ManyToOne //(M:1)
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "tarea") //una tarea una receta (1:1=
    @JsonManagedReference
    private List<RecetaCafe> recetaCafes;
    //Si eliminas una tarea, sus recetas también se eliminan de la base de datos.
    //
    //Si eliminas una receta de la lista recetaCafes, también desaparece de la base de datos.

    public Long getId_Tarea() {
        return id_Tarea;
    }

    public void setId_Tarea(Long id_Tarea) {
        this.id_Tarea = id_Tarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Boolean getCompletada() {
        return completada;
    }

    public void setCompletada(Boolean completada) {
        this.completada = completada;
    }

    public String getLinkSpotify() {
        return linkSpotify;
    }

    public void setLinkSpotify(String linkSpotify) {
        this.linkSpotify = linkSpotify;
    }

    public List<RecetaCafe> getRecetaCafes() {
        return recetaCafes;
    }

    public void setRecetaCafes(List<RecetaCafe> recetaCafes) {
        this.recetaCafes = recetaCafes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
