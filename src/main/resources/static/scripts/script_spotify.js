/*funcion para ver las tareas que puede seleccionar*/
async function cargarTareas(){
    const usuarioId =localStorage.getItem("id_usuario");
    const  res = await fetch(`http://localhost:8080/tareas/ver/${usuarioId}`);
    return await res.json();
}

/*funcion para buscar canciones*/
async function buscarCanciones(query){
    const res = await fetch("http://localhost:8080/spotify/buscar?query=" +encodeURIComponent(query));
    return await res.json(); //lista de canciones
}

async function renderCanciones(query){
    console.log("renderCanciones llamada con query:", query);
    const canciones = await buscarCanciones(query);
    const tareas =await cargarTareas();
    console.log(canciones); // Verifica las canciones obtenidas
    console.log(tareas); // Verifica las tareas obtenidas

    const contenedor = document.getElementById("spotify-container");
    contenedor.innerHTML="";
    canciones.forEach(cancion =>{
        const card = document.createElement("div");
        card.classList.add("spotify-card");
        card.innerHTML =`
        <h3>${cancion.nombre}</h3>
        <p><strong>Artista:</strong> ${cancion.artista}</p>
        <a href="${cancion.link}" target="_blank">Escuchar en Spotify</a><br><br>
        
        
        <label>Asignar a tarea: </label>
        <select class="dropdown-tareas">
            ${tareas.map(t=> `<option value="${t.id_Tarea}">${t.titulo}</option>`).join("")}
        </select>
        <button class="agregar-btn">Agregar a tarea</button>
      `;
        const botonAgregar = card.querySelector(".agregar-btn");
        const dropdown = card.querySelector(".dropdown-tareas");


        botonAgregar.addEventListener("click", () => {
            const idtarea = dropdown.value;
            console.log("ID de tarea seleccionado:", idtarea); // Verifica el valor
            console.log("Opciones en el select:");
            [dropdown.options].forEach(opt => console.log(opt.value, opt.text));

            const link = cancion.link;
            fetch(`http://localhost:8080/spotify/${idtarea}/agregar`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(link)
            })
                .then(res => res.text())
                .then(msg => alert("Agregada: " + msg))
                .catch(err => alert("Error: " + err));
        });
        contenedor.appendChild(card);
    });
}

// /*Aqui podemos cambiar el query que se mandará*/

renderCanciones("kpop")

