package com.example.workbeats;

import com.example.workbeats.Modelo.Usuario;
import com.example.workbeats.Repositorios.RepositorioReceta;
import com.example.workbeats.Repositorios.RepositorioUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControladorUsuariosTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositorioUsuario repositorioUsuario;

    @MockBean
    private RepositorioReceta repositorioReceta;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("POST /usuarios/crear - Crear usuario correctamente")
    public void crearUsuarioTest() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1L);
        usuario.setNombre("testUser");
        usuario.setPassword("1234");
        usuario.setEmail("test@test.com");
        usuario.setTareas(new ArrayList<>());

        when(repositorioUsuario.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/usuarios/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario creado correctamente"));
    }

    @Test
    @DisplayName("POST /usuarios/login - Login exitoso")
    public void loginExitosoTest() throws Exception {
        String nombre = "testUser";
        String password = "1234";

        Usuario usuario = new Usuario();
        usuario.setId_usuario(1L);
        usuario.setNombre(nombre);
        usuario.setPassword(password);

        Map<String,String> loginInfo = new HashMap<>();
        loginInfo.put("nombre", nombre);
        loginInfo.put("password", password);

        when(repositorioUsuario.findByNombre(nombre)).thenReturn(Optional.of(usuario));

        mockMvc.perform(post("/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginInfo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Login exitoso"))
                .andExpect(jsonPath("$.id_usuario").value(1));
    }

    @Test
    @DisplayName("POST /usuarios/login - Credenciales inválidas")
    public void loginFallidoTest() throws Exception {
        String nombre = "testUser";
        String password = "wrongPassword";

        Usuario usuario = new Usuario();
        usuario.setId_usuario(1L);
        usuario.setNombre(nombre);
        usuario.setPassword("1234"); // password real is different

        Map<String,String> loginInfo = new HashMap<>();
        loginInfo.put("nombre", nombre);
        loginInfo.put("password", password);

        when(repositorioUsuario.findByNombre(nombre)).thenReturn(Optional.of(usuario));

        mockMvc.perform(post("/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginInfo)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.mensaje").value("Credenciales inválidas"));
    }

    @Test
    @DisplayName("GET /usuarios/{id} - Obtener usuario por ID")
    public void getUsuarioPorIdTest() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1L);
        usuario.setNombre("testUser");

        when(repositorioUsuario.findById(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("testUser"));
    }

    @Test
    @DisplayName("GET /usuarios/ver - Obtener todos los usuarios")
    public void getUsuariosTest() throws Exception {
        Usuario usuario1 = new Usuario();
        usuario1.setId_usuario(1L);
        usuario1.setNombre("User1");

        Usuario usuario2 = new Usuario();
        usuario2.setId_usuario(2L);
        usuario2.setNombre("User2");

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(repositorioUsuario.findAll()).thenReturn(usuarios);

        mockMvc.perform(get("/usuarios/ver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].nombre").value("User1"))
                .andExpect(jsonPath("$.[1].nombre").value("User2"));
    }

    @Test
    @DisplayName("PUT /usuarios/{id}/datos - Actualizar datos usuario")
    public void actualizarDatosUsuarioTest() throws Exception {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId_usuario(1L);
        usuarioExistente.setNombre("OldName");
        usuarioExistente.setEmail("old@example.com");

        Usuario datosActualizados = new Usuario();
        datosActualizados.setNombre("NewName");
        datosActualizados.setEmail("new@example.com");

        when(repositorioUsuario.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(repositorioUsuario.save(any(Usuario.class))).thenReturn(usuarioExistente);

        mockMvc.perform(put("/usuarios/1/datos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(datosActualizados)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario actualizado correctamente"));
    }

    @Test
    @DisplayName("PUT /usuarios/{id}/contrasenia - Actualizar contraseña usuario")
    public void actualizarContrasenaUsuarioTest() throws Exception {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId_usuario(1L);
        usuarioExistente.setPassword("oldpass");

        Usuario datosActualizados = new Usuario();
        datosActualizados.setPassword("newpass");

        when(repositorioUsuario.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(repositorioUsuario.save(any(Usuario.class))).thenReturn(usuarioExistente);

        mockMvc.perform(put("/usuarios/1/contrasenia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(datosActualizados)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario contraseña actualizado correctamente"));
    }

    @Test
    @DisplayName("DELETE /usuarios/{id} - Eliminar usuario")
    public void eliminarUsuarioTest() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1L);
        usuario.setTareas(new ArrayList<>());

        when(repositorioUsuario.findById(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario eliminado correctamente"));
    }

}

