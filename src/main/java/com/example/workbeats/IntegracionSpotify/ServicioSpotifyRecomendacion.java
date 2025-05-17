package com.example.workbeats.IntegracionSpotify;

import com.example.workbeats.Modelo.spotifyInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/*
En vez de dar recomendaciones va a buscar canciones por genero ya que cuando intente
lo de recomendaciones no se podia(era problema del endpoint) pero con la busqueda si funciona
 */
@Service
public class ServicioSpotifyRecomendacion {
    private final String clienteId = "8e83f1ee4818499f87b4d129ae359894";
    private final String clienteSecret= "52819844792247c38812f4aae1140f65";

    private final RestTemplate restTemplate = new RestTemplate();

    // Obtener Access Token desde Spotify
    private String getAccessToken() {
        /*
        Spotify necesita una autenticacion que se hace cliente_id:cliente_secret codificados con Base64
        es una de las especifiaciones que se ve en la documentacion de la API
         */
        String auth = clienteId + ":" + clienteSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        /*
        Aqui se preparan los encabezados requeridos por Spotify
        que es la Authorization con el texto Basic el token codificado
        y el ContentType como lo exige Spotify
         */
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedAuth);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        /*
        El body especifico que pide Spotify para obtener un token con el metodo client_credentials
         */
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        //se agrupan los headers y el body para hacer el POST
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        //hace la solicitud
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://accounts.spotify.com/api/token",
                request,
                Map.class
        );
        //regresa el token

        return response.getBody().get("access_token").toString();
    }

    // Obtener recomendaciones de canciones
    public List<spotifyInfo> buscarCanciones(String query) {
        String token = getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // URL para buscar canciones según lo que escriba el usuario
        String url = "https://api.spotify.com/v1/search?q=" +query+ "&type=track&limit=5";

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );

        List<spotifyInfo> resultados = new ArrayList<>();
        Map<String, Object> tracksData = (Map<String, Object>) response.getBody().get("tracks");
        List<Map<String, Object>> tracks = (List<Map<String, Object>>) tracksData.get("items");

        for (Map<String, Object> track : tracks) {
            String nombre = (String) track.get("name");
            List<Map<String,Object>> artistas = (List<Map<String, Object>>) track.get("artists");
            String artista = artistas != null && !artistas.isEmpty() ? (String) artistas.get(0).get("name") : "Desconocido";

            Map<String, Object> externalUrls = (Map<String, Object>) track.get("external_urls");
            String link = (String) externalUrls.get("spotify");


            resultados.add(new spotifyInfo(nombre, artista, link));
        }


        return resultados;
    }

}
