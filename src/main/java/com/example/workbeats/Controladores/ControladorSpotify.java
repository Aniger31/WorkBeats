package com.example.workbeats.Controladores;

import com.example.workbeats.IntegracionSpotify.ServicioSpotifyRecomendacion;
import com.example.workbeats.Modelo.Tarea;
import com.example.workbeats.Modelo.spotifyInfo;
import com.example.workbeats.Repositorios.RepositorioTarea;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spotify")
public class ControladorSpotify {

    private final ServicioSpotifyRecomendacion servicioSpotifyRecomendacion;
    private final RepositorioTarea repositorioTarea;

    public ControladorSpotify(RepositorioTarea repositorioTarea, ServicioSpotifyRecomendacion servicioSpotifyRecomendacion) {
        this.repositorioTarea = repositorioTarea;
        this.servicioSpotifyRecomendacion = servicioSpotifyRecomendacion;
    }

    @GetMapping("/buscar")
    public List<spotifyInfo> obtenerRecomendaciones(@RequestParam String query){
        return servicioSpotifyRecomendacion.buscarCanciones(query);
    }

    //agregar el link de la cancion a la tarea que elija
    @PutMapping(path = "/{id}/agregar")
    public @ResponseBody String agregarRecomendacion(@PathVariable(name = "id")Long id, @RequestBody String link){

        Tarea tarea = repositorioTarea.findById(id).orElseThrow();
        tarea.setLinkSpotify(link.replace("\"", "").trim()); //ademas de agregarlo limpia si tiene \ de mas
        repositorioTarea.save(tarea);
        return "El link se a agregado correctamente a la tarea";
    }

}
