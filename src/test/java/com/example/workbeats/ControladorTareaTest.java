package com.example.workbeats;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.workbeats.Modelo.RecetaCafe;
import com.example.workbeats.Modelo.Tarea;
import com.example.workbeats.Modelo.Usuario;
import com.example.workbeats.Repositorios.RepositorioReceta;
import com.example.workbeats.Repositorios.RepositorioTarea;
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

@SpringBootTest
@AutoConfigureMockMvc
public class ControladorTareaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositorioTarea repositorioTarea;

    @MockBean
    private RepositorioReceta repositorioReceta;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("POST /tareas/crear - Crear tarea")
    public void crearTareaTest() throws Exception {
        Tarea tarea = new Tarea();
        tarea.setId_Tarea(1L);
        tarea.setCompletada(false);

        when(repositorioTarea.save(any(Tarea.class))).thenReturn(tarea);

        mockMvc.perform(post("/tareas/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarea)))
                .andExpect(status().isOk())
                .andExpect(content().string("Tarea creada"));
    }

    @Test
    @DisplayName("PUT /tareas/{id}/estado - Actualizar estado de tarea")
    public void actualizarEstadoTareaTest() throws Exception {
        Tarea tarea = new Tarea();
        tarea.setId_Tarea(1L);
        tarea.setCompletada(false);

        when(repositorioTarea.findById(1L)).thenReturn(Optional.of(tarea));
        when(repositorioTarea.save(any(Tarea.class))).thenReturn(tarea);

        mockMvc.perform(put("/tareas/1/estado"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tarea actualizada"));
    }

    @Test
    @DisplayName("DELETE /tareas/{id} - Eliminar tarea")
    public void eliminarTareaTest() throws Exception {
        mockMvc.perform(delete("/tareas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tarea eliminada correctamente"));
    }
}
