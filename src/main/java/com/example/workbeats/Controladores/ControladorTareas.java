package com.example.workbeats.Controladores;
//VOLVER HACER PORQUE FALTA LOGICA EN EL CASO DE LAS RECETAS DEL CAFE


//Controlador de las tareas del usuario

import com.example.workbeats.Modelo.Tarea;
import com.example.workbeats.Repositorios.RepositorioTarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api")
public class ControladorTareas {
    @Autowired
    private RepositorioTarea repositorioTarea;

    //READ
    @GetMapping(path = "/tareas")
    public @ResponseBody Iterable<Tarea> listaTareas() {
        return repositorioTarea.findAll();
    }

    //CREATE
    @PostMapping(path = "/tareas")
    public @ResponseBody String addTarea(@RequestBody Tarea tarea) {
        repositorioTarea.save(tarea);
        return "Tarea agregada con exito";
    }

    //UPDATE
    @PutMapping(path = "/tareas/{id}")
    public @ResponseBody String updateTarea(@RequestBody Tarea tarea, @PathVariable(name= "id") Long id) {
        Tarea t_viejo = repositorioTarea.getReferenceById(id);

        t_viejo.setTitulo(tarea.getTitulo());
        t_viejo.setDescripcion(tarea.getDescripcion());
        t_viejo.setLinkSpotify(tarea.getLinkSpotify());
        t_viejo.setRecetaCafe(tarea.getRecetaCafe());
        t_viejo.setCompletada(tarea.getCompletada());
        repositorioTarea.save(t_viejo);
        return "Tarea actualizada con exito";
    }

    //DELETE
    @DeleteMapping(path ="/tareas/{id}" )
    public @ResponseBody String deleteTarea(@PathVariable(name = "id") Long id) {
        repositorioTarea.deleteById(id);
        return "Tarea eliminada con exito";
    }

}
