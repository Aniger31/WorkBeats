package com.example.workbeats.Modelo;

public class spotifyInfo {
    private String nombre;
    private String artista;
    private String link;


    public spotifyInfo(String nombre, String artista, String link) {
        this.nombre = nombre;
        this.artista = artista;
        this.link = link;

    }

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
