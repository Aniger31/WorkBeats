WORKBEATS

Documentacion:

ControladorUsuarios:
- Un POST: aquí seria el caso cuando hace el login en donde agrega(nombre, email, contraseña) en el caso de la lista de tareas es null cuando se crea.
- Un GET: mostrar la info del usuario. (Tenemos otro que es un get que muestra todos los usuarios pero lo tenemos para las pruebas de postman).
- Los PUTS que tenemos son:
	actualizarDatosUsuario -> aquí solo actualiza el nombre y el email.
	actualizarContrasenaUsuario -> cambio de contraseña.
-Un DELETE: en este caso solo es borrar completamente la info del usuario junto con las tareas que tenga vinculada sin eliminar las recetas (nada mas se desvinculan).

ControladorTareas:
- POST: aquí da los datos básicos de la tarea que seria titulo y descripción, en completada se le pone false ya que cuando crea una tarea todavía no la completa.
- GET: Por id (muestra la info de la tarea) y una lista ( la cual son todas las tareas)
- Los PUTS son:
	estadoDeTarea-> aquí cambiamos el completada a true
	asignarRecetaTarea ("/{idTarea}/asignar-receta/{idReceta}")) -> mandamos el id de la tarea y el id de la receta para que se asigne la receta a la tarea (se busca tanto la tarea como la receta y despues se hace la conexion y se guardan los datos).
-Un DELETE: en este solo se elimina la tarea.

ControladorRecetaCafe: Tenemos recetas creadas si tener una conexión automática con una tarea
- Un POST -> mandamos la receta a guardar
- Un GET: por id y otro de todas las recetas
- Un PUT:  actualizar los pasos de la receta
- Un DELETE: solo borra la receta

ControladorSpotify: Aqui llamamos al servicio de Recomendacion Spotify y al Repositorio tarea
-Un GET: en el cual busca las canciones del genero especifico que queremos.
-Un PUT: en el cual se agrega el link elegido a la tarea elegida

en la carpeta resources
-> styles: son los archivos css que son los estilos que se utilizan 
-> scripts: son los archivos js que son los scripts para el manejo de los datos mediante el fetch() para usar el crud que tenemos en los controladores
estan los HTMLs de las paginas que utilizamos 

en la carpeta test
    estan todos los unit test por controlador implementé los test que vi que eran necesarios
