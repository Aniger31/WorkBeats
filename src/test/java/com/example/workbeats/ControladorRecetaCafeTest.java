package com.example.workbeats;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.workbeats.Modelo.RecetaCafe;
import com.example.workbeats.Repositorios.RepositorioReceta;
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
public class ControladorRecetaCafeTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositorioReceta repositorioReceta;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("POST /recetas/crear - Crear receta café")
    public void crearRecetaCafeTest() throws Exception {
        RecetaCafe receta = new RecetaCafe();
        receta.setId_recetaCafe(1L);
        receta.setPasos("Paso 1, Paso 2");

        when(repositorioReceta.save(any(RecetaCafe.class))).thenReturn(receta);

        mockMvc.perform(post("/recetas/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receta)))
                .andExpect(status().isOk())
                .andExpect(content().string("Receta Cafe Creada"));
    }



    @Test
    @DisplayName("PUT /recetas/{id}/pasos - Actualizar pasos de receta")
    public void actualizarPasosRecetaTest() throws Exception {
        RecetaCafe recetaExistente = new RecetaCafe();
        recetaExistente.setId_recetaCafe(1L);
        recetaExistente.setPasos("Pasos viejos");

        RecetaCafe recetaActualizada = new RecetaCafe();
        recetaActualizada.setPasos("Pasos nuevos");

        when(repositorioReceta.findById(1L)).thenReturn(Optional.of(recetaExistente));
        when(repositorioReceta.save(any(RecetaCafe.class))).thenReturn(recetaExistente);

        mockMvc.perform(put("/recetas/1/pasos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recetaActualizada)))
                .andExpect(status().isOk())
                .andExpect(content().string("Pasos de receta actualizada"));
    }

    @Test
    @DisplayName("DELETE /recetas/{id}/borrar - Eliminar receta")
    public void eliminarRecetaTest() throws Exception {
        mockMvc.perform(delete("/recetas/1/borrar"))
                .andExpect(status().isOk())
                .andExpect(content().string("Receta de cafe eliminada"));
    }
}
