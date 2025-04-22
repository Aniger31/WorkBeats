package com.example.workbeats.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RecetaCafe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_recetaCafe;

    private String nombre;
    private String tipo; // fria o caliente frappe
    private String pasos;
    private int tiempoMin; // el tiempo que te tardas en hacer la receta

    public Long getId_recetaCafe() {
        return id_recetaCafe;
    }

    public void setId_recetaCafe(Long id_recetaCafe) {
        this.id_recetaCafe = id_recetaCafe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public int getTiempoMin() {
        return tiempoMin;
    }

    public void setTiempoMin(int tiempoMin) {
        this.tiempoMin = tiempoMin;
    }
}
