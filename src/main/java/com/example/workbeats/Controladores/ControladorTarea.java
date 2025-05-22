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

    /**
     * @param tarea la info de la clase de tarea que se quiere crear
     * @return mensaje de que si se creo
     */
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

    /**
     * @return todas las tareas que tenemos en la base de datos
     */
    @GetMapping(path = "/ver/lista")
    public @ResponseBody Iterable<Tarea> getTareas() {
        return repositorioTarea.findAll();
    }


    /**
     * @param idUsuario el usuario del cual queremos buscar las tareas
     * @return las tareas del usuario que le mandamos
     */
    @GetMapping(path = "/ver/{idUsuario}")
    public @ResponseBody List<Tarea> getTareasPorUsuario(@PathVariable(name= "idUsuario") Long idUsuario){
        return repositorioTarea.findAll()
                .stream()
                .filter(t -> t.getUsuario() != null && t.getUsuario().getId_usuario().equals(idUsuario))
                .toList();
    }


    /**
     * @param id de la tarea que le queremos cambiar el estado de FALSE a TRUE
     * @return un mensaje de que se actualice la tarea
     */
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


    /**
     * @param id de la tarea que queremos eliminar
     * @return un mensaje de que la tarea se eliminó
     */
    @DeleteMapping(path = "/{id}")
    public @ResponseBody String eliminarTarea(@PathVariable(name = "id") Long id) {
        repositorioTarea.deleteById(id);
        return "Tarea eliminada correctamente";
    }

}

