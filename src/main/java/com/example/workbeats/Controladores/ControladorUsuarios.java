package com.example.workbeats.Controladores;

import com.example.workbeats.Modelo.RecetaCafe;
import com.example.workbeats.Modelo.Tarea;
import com.example.workbeats.Modelo.Usuario;
import com.example.workbeats.Repositorios.RepositorioReceta;
import com.example.workbeats.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path = "/usuarios")
public class ControladorUsuarios {
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioReceta repositorioReceta;

    /**
     * @param usuario  es toda la info del usuario a crear
     * @return un mensaje de que el usuario se creó correctamente
     */
    @PostMapping(path = "/crear")
    public String crearUsuario(@RequestBody Usuario usuario) {
        usuario.setTareas(new ArrayList<>()); //Inicializamos el arreglo de forma vacia
        repositorioUsuario.save(usuario);
        return "Usuario creado correctamente";
    }


    //*Mandamos la info que ingresa el nombre y la contraseña para verificar si existe en la base de datos
    // y que pueda ingresar a la pagina de home*/
    @PostMapping(path="/login")
    public ResponseEntity<Map<String,Object>> login( @RequestBody Map<String,String> loginInfo){
        String nombre = loginInfo.get("nombre");
        String password = loginInfo.get("password");

        Optional<Usuario> usOpt = repositorioUsuario.findByNombre(nombre);
        if(usOpt.isPresent()){
            Usuario usuario = usOpt.get();
            if(usuario.getPassword().equals(password)){
                Map<String,Object> respuesta= new HashMap<>();
                respuesta.put("mensaje","Login exitoso");
                //respuesta.put("id_usuario",usuario.getId_usuario()); //id que utilizaremos posteriormente
                return ResponseEntity.ok(respuesta);

            }
        }
        // creamos la respuesta si no son correctas los datos o credenciales y devuelve la respuesta HTTP
        // que es un estado 401 que es el de que no esta autorizado
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("mensaje", "Credenciales inválidas"));
    }


    // GET por id
    @GetMapping(path = "/{id}")
    public @ResponseBody Usuario getUsuario(@PathVariable(name = "id") Long id) {
        return repositorioUsuario.findById(id).orElseThrow();
    }

    //GET ALL
    @GetMapping(path = "/ver")
    public @ResponseBody Iterable<Usuario> getUsuarios() {
        return repositorioUsuario.findAll();
    }

    /**
     * @param id mandamos el id que queremos actualizar
     * @param usuario la info con el cual debemos actualizar el usuario del id
     * @return el mensaje  que el usuario se actualizo correctamente
     */
    @PutMapping(path = "/{id}/datos")
    public @ResponseBody String actualizarDatosUsuario(@PathVariable(name = "id") Long id, @RequestBody Usuario usuario) {
        Usuario usuario1 = repositorioUsuario.findById(id).orElseThrow();
        usuario1.setNombre(usuario.getNombre());
        usuario1.setEmail(usuario.getEmail());
        repositorioUsuario.save(usuario1);
        return "Usuario actualizado correctamente";
    }


    /**
     * @param id mandamos el id que queremos actualizar
     * @param usuario la info con el cual debemos actualizar el usuario del id
     * @return el mensaje  que el usuario se actualizo correctamente
     */
    @PutMapping(path = "/{id}/contrasenia")
    public @ResponseBody String actualizarContrasenaUsuario(@PathVariable(name = "id") Long id, @RequestBody Usuario usuario) {
        Usuario usuario1 = repositorioUsuario.findById(id).orElseThrow();
        usuario1.setPassword(usuario.getPassword());
        repositorioUsuario.save(usuario1);
        return "Usuario contraseña actualizado correctamente";
    }

    /**
     * @param id que queremos borrar
     * @return el mensaje de que el usuario se borro
     */
    @DeleteMapping(path = "/{id}")
    public @ResponseBody String eliminarUsuario(@PathVariable(name = "id") Long id) {
        Usuario usuario = repositorioUsuario.findById(id).orElseThrow();
        // vamos a desvincular las recetas de las tareas
        for (Tarea tarea : usuario.getTareas()) {
            for(RecetaCafe receta: tarea.getRecetaCafes()){
                receta.setTarea(null); // quitamos la conexion que tienen
                repositorioReceta.save(receta);
            }
        }
        repositorioUsuario.deleteById(id);
        return "Usuario eliminado correctamente";
    }

}
