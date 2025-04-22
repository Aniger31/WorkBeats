package com.example.workbeats.Modelo;

import jakarta.persistence.*;

@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_Tarea;

    private String titulo;
    private String descripcion;
    private Boolean completada; //True = completada y False = Incompleta
    private String linkSpotify;
    @ManyToOne
    @JoinColumn(name = "receta_cafe_id_receta_cafe")
    private RecetaCafe recetaCafe;
    @ManyToOne
    @JoinColumn(name = "usuario_id_usuario")
    private Usuario usuario;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public RecetaCafe getRecetaCafe() {
        return recetaCafe;
    }

    public void setRecetaCafe(RecetaCafe recetaCafe) {
        this.recetaCafe = recetaCafe;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
