package com.example.workbeats.Controladores;

import com.example.workbeats.Modelo.RecetaCafe;
import com.example.workbeats.Repositorios.RepositorioReceta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/recetas")
public class ControladorRecetaCafe {
    @Autowired
    private RepositorioReceta repositorioReceta;

    @PostMapping(path = "/crear")
    public String crearRecetaCafe(@RequestBody RecetaCafe recetaCafe) {
        repositorioReceta.save(recetaCafe);
        return "Receta Cafe Creada";
    }
    //GET ALL
    @GetMapping(path = "/ver")
    public @ResponseBody Iterable<RecetaCafe> getRecetasCafe() {
        return repositorioReceta.findAll();
    }
    @GetMapping(path = "/{id}")
    public @ResponseBody RecetaCafe getRecetas(@PathVariable(name = "id") Long id){
        return repositorioReceta.findById(id).orElseThrow();
    }

    @PutMapping(path = "/{id}/pasos")
    public @ResponseBody String pasosReceta(@PathVariable(name = "id") Long id, @RequestBody RecetaCafe recetaCafe) {
        RecetaCafe recetaCafe1 = repositorioReceta.findById(id).orElseThrow();
        recetaCafe1.setPasos(recetaCafe.getPasos());
        repositorioReceta.save(recetaCafe1);
        return "Pasos de receta actualizada";
    }
    @DeleteMapping(path = "/{id}/borrar")
    public @ResponseBody String eliminarReceta(@PathVariable(name = "id") Long id) {
        repositorioReceta.deleteById(id);
        return "Receta de cafe eliminada";
    }

}
/*
en recetaCafe seria:
creo que podríamos tener recetas creadas si tener una conexión automática con una tarea
	-Un POST ->
	-Un GET
	- Un PUT de actualizar los pasos
	- Un DELETE
 */
