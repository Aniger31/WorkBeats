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

    /**
     * @param recetaCafe la info de la clase de la receta que queremos agregar
     * @return un mensaje de quela receta se agregó correctamente
     */
    @PostMapping(path = "/crear")
    public String crearRecetaCafe(@RequestBody RecetaCafe recetaCafe) {
        repositorioReceta.save(recetaCafe);
        return "Receta Cafe Creada";
    }


    /**
     * @return las recetas de café
     */
    //GET ALL
    @GetMapping(path = "/ver")
    public @ResponseBody Iterable<RecetaCafe> getRecetasCafe() {
        return repositorioReceta.findAll();
    }


    /**
     * @param id buscar una receta en especifico
     * @return la receta encontrada
     */
    @GetMapping(path = "/{id}")
    public @ResponseBody RecetaCafe getRecetas(@PathVariable(name = "id") Long id){
        return repositorioReceta.findById(id).orElseThrow();
    }


    /**
     * @param id de la receta a actualizar con los datos de
     * @param recetaCafe que son los datos a actualizar
     * @return el mensaje de que la receta se actualizó
     */
    @PutMapping(path = "/{id}/pasos")
    public @ResponseBody String pasosReceta(@PathVariable(name = "id") Long id, @RequestBody RecetaCafe recetaCafe) {
        RecetaCafe recetaCafe1 = repositorioReceta.findById(id).orElseThrow();
        recetaCafe1.setPasos(recetaCafe.getPasos());
        repositorioReceta.save(recetaCafe1);
        return "Pasos de receta actualizada";
    }


    /**
     * @param id de la receta que queremos eliminar
     * @return un mensaje de que se eliminó correctamente
     */
    @DeleteMapping(path = "/{id}/borrar")
    public @ResponseBody String eliminarReceta(@PathVariable(name = "id") Long id) {
        repositorioReceta.deleteById(id);
        return "Receta de cafe eliminada";
    }

}
