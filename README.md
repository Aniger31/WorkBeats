WORKBEATS

Documentacion:
ControladorUsuarios:

	- Un POST: aquí seria el caso cuando hace el login en donde agrega(nombre, email, contraseña) en el caso de la lista de tareas es null cuando se crea.
 
	- Un GET: mostrar la info del usuario. (Tenemos otro que es un get que muestra todos los usuarios pero lo tenemos para las pruebas de postman).
 
	- Los PUTS que tenemos son:
	                actualizarDatosUsuario -> aquí solo actualiza el nombre y el email.
		 	actualizarContrasenaUsuario -> cambio de contraseña.
	-Un DELETE: en este caso solo es borrar completamente la info del usuario junto con las tareas que tenga vinculada sin eliminar las recetas (nada mas se desvinculan)


ControladorTareas:
	-Un POST: aquí da los datos básicos de la tarea que seria titulo y descripción, en completada se le pone false ya que cuando crea una tarea todavía no la completa.
	- GET: Por id (muestra la info de la tarea) y una lista ( la cual son todas las tareas)
	-Los PUTS son:
	                estadoDeTarea-> aquí cambiamos el completada a true
					asignarRecetaTarea ("/{idTarea}/asignar-receta/{idReceta}")) -> mandamos el id de la tarea y el id de la receta para que se asigne la receta a la tarea (se busca tanto la tarea como la receta y despues se hace la conexion y se guardan los datos)

	-Un DELETE: en este solo se elimina la tarea

ControladorRecetaCafe:
Tenemos recetas creadas si tener una conexión automática con una tarea
	-Un POST -> mandamos la receta a guardar
	-Un GET: por id y otro de todas las recetas
	- Un PUT:  actualizar los pasos de la receta
	- Un DELETE: solo borra la receta

ControladorSpotify:
Aqui llamamos al servicio de Recomendacion Spotify y al Repositorio tarea
    -Un GET: en el cual busca las canciones del genero especifico que queremos
    -Un PUT: en el cual se agrega el link elegido a la tarea elegida


PAGINAS

- login: -> {baseurl}/usuarios/login
aquí solo ingresas el nombre y contraseña para entrar al index
	en el login hay un botón que puedes presionar si no tienes cuenta el cual te manda a:

	->	registro: -> {baseurl}/registro
		aquí debes ingresar los datos del usuario (nombre, email, password) que es POST de  crearUsuario que tenemos en el ControladorUsuarios y el botón de al final de la pagina nos manda a index.

después de hacer cualquiera de las dos te manda a:
	-> pagina de inicio: ->{baseurl}/index


	POR PARTE DE TAREAS:

	dentro de esta pagina en el Dropdown de TAREAS ->Lista te va a mandar a:

		Lista tareas: -> {baseurl}/tareas/ver/lista
		                la cual es el GET de ver todas las tareas

		    en el cual veras una tabla con todas las tareas y en cada una tendrá un botón de ver el cual te mandara a:

			    Ver Tarea id : -> {baseurl}/tareas/detalle/{id}
			                    La cual es el GET por id.
			                    La cual Mostrará toda la info de la Tarea seleccionada.
			                    Tendrá un botón para volver a (Lista tareas)

		    o puedes seleccionar el botón de volver para volver al index.

	Hay un botón en la parte de tareas que es para agregar una tarea, al seleccionarlo te manda a:

		agregar tarea: -> {baseurl}/tareas/crear
		    La cual es un POST.
		    En el cual mandaremos todos los datos agregados a la tarea con el botón crear la manda y además te regresa al index.

	Hay otro boton en la parte de tareas que es para eliminar la tarea la cual solo llama al DELETE por id.

	Cada una de las tareas tiene un Checkbox
	    el cual al seleccionarlo actualiza el estado de la tarea con el PUT de estadoTarea que tenemos por id.

	POR PARTE DE SPOTIFY:
	Nos muestra 5 canciones (en un table) del genero que mandemos( este ya esta definido en el back no lo cambiaremos)
	    tendremos por cancion un Dropdown con cada una de las tareas que están activas para que seleccione una y a lado tenemos un botón Agregar
	    que llama al PUT de agregarRecomendacion(id, link) que tenemos en el Controlador de Spotify el cual agrega el link que eligió a la tarea seleccionada.

	POR PARTE DE RECETAS:
	Tendremos un acordion con recomendaciones de recetas de cafe la cual  tendrá el nombre en el encabezado y al abrirlo veras la descripción de la receta
	    Al final un Dropdown con cada una de las tareas que están activas para que seleccione una y a lado tenemos un botón Agregar
	        ya que llamamos al PUT asignarRecetaTarea(idTarea, idReceta) que tenemos en el ControladorTarea  para que se guarde la receta en la tarea que desea el usuario.

	POR PARTE DE USUARIO:
	tenemos un Dropdown que nos da las opciones:
		-Ver usuario:
			aquí nos manda a -> {baseurl}/usuario/id
			    en el cual nos aparece la información del usuario y solo en los campos de nombre, email y contraseña tendrá el botón para editarlo pero solo puede hacerse de la siguiente forma:
                    nombre y email ó contraseña porque son los puts que tenemos
                    nos aparecerá el botón guardar el cual guarda los cambios y nos regresará al index.
		-Salir: nos mandara a la pagina del login.


en la carpeta resources
                -> static: van los CSS, JS, imagenes
                -> templates: los HTML son las paginas
en la carpeta test
    estan todos los unit test por controlador implementé los test que vi que eran necesarios
