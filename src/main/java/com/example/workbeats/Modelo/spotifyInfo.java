package com.example.workbeats.Modelo;

public class spotifyInfo {
    /**
     * son los datos que sacaremos del API de Spotify,
     * el link es lo que guardaremos en la tarea
     * el nombre(titulo de cancion) y el artista (nombre del artista) que es lo que mostraremos en la pantalla
     */
    private String nombre;
    private String artista;
    private String link;


    public spotifyInfo(String nombre, String artista, String link) {
        this.nombre = nombre;
        this.artista = artista;
        this.link = link;

    }

    /*Getters y Setters*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
