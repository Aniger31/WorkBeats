/*funcion para ver las tareas que puede seleccionar*/
async function cargarTareas(){
    const usuarioId =localStorage.getItem("id_usuario");
    const  res = await fetch(`http://localhost:8080/tareas/ver/${usuarioId}`);
    return await res.json();
}

async function cargarRecetas(){
    const res = await fetch("http://localhost:8080/recetas/ver");
    return await res.json();
}

async function renderRecetas(){
    const recetas = await cargarRecetas();
    const tareas = await cargarTareas();

    console.log(recetas);
    console.log(tareas);

    const contenedor = document.getElementById("recetas-container");
    contenedor.innerHTML ="";

    recetas.forEach(receta => {
        const item = document.createElement("div");
        item.classList.add("accordion-item");

        item.innerHTML =`
            <div class="accordion-header">${receta.nombre}</div>
            <div class="accordion-body">
                <p><strong>Tipo:</strong> ${receta.tipo}</p>
                <p><strong>Pasos:</strong> ${receta.pasos}</p>
                <p><strong>Tiempo de preparacion:</strong> ${receta.tiempoMin} minutos</p>
                
                
                <label>Asignar a tarea: </label>
                <select class="dropdown-tareas">
                    ${tareas.map(t => `<option value="${t.id_Tarea}">${t.titulo}</option>`).join("")}
                </select>
                <button class="agregar-btn">Agregar a tarea</button>
            </div>
        `;

        const header = item.querySelector(".accordion-header");
        const body = item.querySelector(".accordion-body");
        const boton = item.querySelector(".agregar-btn");
        const dropdown = item.querySelector(".dropdown-tareas");

        header.addEventListener("click", () => {
            body.style.display = body.style.display === "block" ? "none" : "block";
        });
        boton.addEventListener("click", () => {
            const idTarea = dropdown.value;
            const idReceta= receta.id_recetaCafe;


            fetch(`http://localhost:8080/tareas/${idTarea}/asignar-receta/${idReceta}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(receta)

            })
                .then(res => res.text())
                .then(msg=> alert("Receta asignada correctamente"))
                .catch(err => alert("Error: " + err));
        });

        contenedor.appendChild(item);
    });
}

// Ejecuta al cargar
renderRecetas();