package com.example.workbeats;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.workbeats.IntegracionSpotify.ServicioSpotifyRecomendacion;
import com.example.workbeats.Modelo.Tarea;
import com.example.workbeats.Modelo.spotifyInfo;
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

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class ControladorSpotifyTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositorioTarea repositorioTarea;



    @Test
    @DisplayName("PUT /spotify/{id}/agregar - Agregar link de recomendación a tarea")
    public void agregarRecomendacionTest() throws Exception {
        Long idTarea = 1L;
        String link = "https://link-a-cancion.com";

        Tarea tarea = new Tarea();
        tarea.setId_Tarea(idTarea);

        when(repositorioTarea.findById(idTarea)).thenReturn(Optional.of(tarea));
        when(repositorioTarea.save(any(Tarea.class))).thenReturn(tarea);

        mockMvc.perform(put("/spotify/1/agregar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"" + link + "\"")) // Envolviendo el link en comillas
                .andExpect(status().isOk())
                .andExpect(content().string("El link se a agregado correctamente a la tarea"));
    }
}

