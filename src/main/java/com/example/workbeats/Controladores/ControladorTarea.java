package com.example.workbeats.Controladores;

import com.example.workbeats.Modelo.RecetaCafe;
import com.example.workbeats.Modelo.Tarea;
import com.example.workbeats.Repositorios.RepositorioReceta;
import com.example.workbeats.Repositorios.RepositorioTarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "/tareas")
public class ControladorTarea {
    @Autowired
    private RepositorioTarea repositorioTarea;

    @Autowired
    private RepositorioReceta repositorioReceta;

    @PostMapping(path = "/crear")
    public @ResponseBody String crearTarea(@RequestBody Tarea tarea){
        tarea.setCompletada(Boolean.FALSE);
        repositorioTarea.save(tarea);
        return "Tarea creada";
    }

    @GetMapping(path = "/detalle/{id}")
    public @ResponseBody Tarea getTarea(@PathVariable(name = "id") Long id){
        return repositorioTarea.findById(id).orElseThrow();
    }
    //GET ALL es para hacer las pruebas de postman

    @GetMapping(path = "/ver/lista")
    public @ResponseBody Iterable<Tarea> getTareas() {
        return repositorioTarea.findAll();
    }
    @GetMapping(path = "/ver/{idUsuario}")
    public @ResponseBody List<Tarea> getTareasPorUsuario(@PathVariable(name= "idUsuario") Long idUsuario){
        return repositorioTarea.findAll()
                .stream()
                .filter(t -> t.getUsuario() != null && t.getUsuario().getId_usuario().equals(idUsuario))
                .toList();
    }


    @PutMapping(path = "/{id}/estado")
    public @ResponseBody String estadoTarea(@PathVariable(name = "id") Long id){
        Tarea tarea = repositorioTarea.findById(id).orElseThrow();
        tarea.setCompletada(Boolean.TRUE);
        repositorioTarea.save(tarea);
        return "Tarea actualizada";
    }

    @PutMapping(path = "/{idTarea}/asignar-receta/{idReceta}")
    public @ResponseBody Tarea asignarRecetaTarea(@PathVariable(name = "idTarea") Long idTarea, @PathVariable(name = "idReceta") Long idReceta){
        Tarea tarea = repositorioTarea.findById(idTarea).orElseThrow(); //buscas la tarea
        RecetaCafe receta = repositorioReceta.findById(idReceta).orElseThrow(); //buscas la receta
        receta.setTarea(tarea); //haces conexion con la receta y tarea
        repositorioReceta.save(receta); //guardas la receta
        return tarea;
    }


    @DeleteMapping(path = "/{id}")
    public @ResponseBody String eliminarTarea(@PathVariable(name = "id") Long id) {
        repositorioTarea.deleteById(id);
        return "Tarea eliminada correctamente";
    }

}
/*
en tareas seria:
	-Un POST: aquí da los datos básicos de la tarea que seria titulo, en completada se le pone false ya que cuando crea una tarea todavía no la completa.
	- Un GET: muestra la info de la tarea.
	-Los PUTS que quiero son: 	actualizarEstadoDeTarea-> aquí cambiamos el completada a true
					agregarRecetaCafe-> igual que en el de agregarTareas en el controlador de usuarios tengo duda por el hecho de que puede crearse en REcetas directamente y hacer la actualización pero no se que me recomiendes.

	-Un DELETE: en este solo se elimina la tarea pero no se como seria para que no falle ya que tarea esta conectada con RecetaCafe pero lo único que quiero borrar es la tarea no la receta ya que esa misma puedes asignarla a otra tarea posteriormente.
 */