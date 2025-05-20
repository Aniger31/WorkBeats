async function cargarTareas() {
    const usuarioId = localStorage.getItem("id_usuario");
    const res = await fetch(`http://localhost:8080/tareas/ver/${usuarioId}`);
    return await res.json();
}

async function renderTareas() {
    const tareas = await cargarTareas();
    console.log(tareas);

    const contenedor = document.getElementById("detalles-container");
    contenedor.innerHTML = "";

    tareas.forEach(tarea => {
        let recetasHtml = "";
        if (tarea.recetaCafes.length > 0) {
            recetasHtml = tarea.recetaCafes.map(r => `
            <div class="receta">
            <p><strong>Nombre:</strong> ${r.nombre}</p>
            <p><strong>Tipo:</strong> ${r.tipo}</p>
            <p><strong>Pasos:</strong> ${r.pasos}</p>
            <p><strong>Tiempo estimado:</strong> ${r.tiempoMin} min</p>
            <hr>
        </div>
    `).join("");
        } else {
            recetasHtml = "<p>No hay recetas asociadas</p>";
        }

        const item = document.createElement("div");
        item.classList.add("accordion-item");

        const header = document.createElement("div");
        header.classList.add("accordion-header");
        header.textContent = tarea.titulo;

        const body = document.createElement("div");
        body.classList.add("accordion-body");
        body.innerHTML = `
            <p><strong>Estado:</strong> ${tarea.completada ? "Completada" : "Pendiente"}</p>
            <p><strong>Link de Spotify:</strong> ${tarea.linkSpotify}</p>
            <p><strong>Receta Cafe:</strong></p>
            ${recetasHtml}
        `;
        body.style.display = "none"; // Oculta inicialmente

        // Evento para toggle
        header.addEventListener("click", () => {
            body.style.display = (body.style.display === "none") ? "block" : "none";
        });

        item.appendChild(header);
        item.appendChild(body);
        contenedor.appendChild(item);
    });
}

renderTareas();
