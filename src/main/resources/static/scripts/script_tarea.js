async function CreateTodoItems(){
    const input = document.getElementById("todoText");
    const  texto = input.value.trim();

    if(!texto){
        document.getElementById("Alert").innerText = "Debes ingresar una tarea";
        return;
    }

    const usuarioId =localStorage.getItem("id_usuario");//obtiene el id del usuario que ingreso en el login
    const nuevaTarea ={
        titulo: texto,
        usuario:{
            id_usuario: usuarioId // se enlaza la tarea con el usuario
        }
    };

    const response = await  fetch("tareas/crear", {
        method: "POST",
        headers:{
            "Content-Type": "application/json"
        },
        body: JSON.stringify(nuevaTarea)
    });

    if(response.ok){
        input.value ="";
        document.getElementById("Alert").innerText = "";
        obtenerTareas();
    }else{
        document.getElementById("Alert").innerText = "Error al crear tarea"
    }
}

//Mostrar tareas /tareas/ver/lista
async function obtenerTareas(){
    const usuarioId =localStorage.getItem("id_usuario");//obtiene el id del usuario que ingreso en el login

    const response = await fetch(`tareas/ver/${usuarioId}`);
    const tareas = await response.json();

    console.log(tareas) // es para verificar si esta recibiendo las tareas correctamente
    const lista = document.getElementById("list-items");
    lista.innerHTML ="";

    tareas.forEach(tarea => {
        console.log(tareas) // es para verificar la tarea que se esta procesando

        const item = document.createElement("li");
        item.innerHTML =`
            <span style="text-decoration: ${tarea.completada ? 'line-through' : 'none'};">
                ${tarea.titulo}
            </span>
            <button onclick="completarTarea(${tarea.id_Tarea})"> Completar</button>
            <button onclick="eliminarTarea(${tarea.id_Tarea})"> Eliminar</button>
        `;
        lista.appendChild(item);
    });
}

//Marcar completada
async function completarTarea(id){
    await  fetch(`/tareas/${id}/estado`,{
        method: "PUT"
    });
    obtenerTareas();
}

//eliminar tarea
async function eliminarTarea(id){
    await fetch(`/tareas/${id}`,{
        method: "DELETE"
    });
    obtenerTareas();
}