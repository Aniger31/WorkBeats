# ✅ Checklist de mejoras para la app (Spring Boot + MySQL + HTML/CSS/JS)

Este documento sirve como guía para revisar y mejorar la aplicación.  
Se hicieron los issues para cada uno de los pasos a seguir.

Organizar con: **Project Board** con columnas: `Pendiente`, `En progreso`, `Hecho`.

---

## 🔍 1. Diagnóstico y organización
- [ ] Ejecutar la app y probar **como usuaria** cada pantalla (anotar errores o cosas incómodas).  
- [ ] Crear en GitHub un **Project board** con columnas: `Pendiente`, `En progreso`, `Hecho`.  
- [ ] Hacer una lista de **bugs conocidos** (ejemplo: botones que no funcionan, estilos rotos, errores al guardar datos).  

---

## 🛠 2. Backend (Spring Boot + MySQL)
- [ ] Revisar si las **entidades** de la base de datos están bien modeladas (nombres claros, relaciones correctas).  
- [ ] Verificar que los **controladores** cumplen con lo necesario y no tienen lógica duplicada.  
- [ ] Revisar si los **servicios** están separados de los controladores (buena práctica).  
- [ ] Actualizar dependencias de Maven (Spring Boot, MySQL Connector, etc.).  
- [ ] Probar todas las operaciones CRUD con Postman.  

---

## 🎨 3. Frontend (HTML, CSS, JS)
- [ ] Revisar cada página y apuntar qué estilos no son consistentes (botones, tipografía, colores).  
- [ ] Unificar tipografía y colores en un solo archivo CSS global.  
- [ ] Mejorar la **responsividad** con media queries (que se vea bien en móvil y escritorio).  
- [ ] Limpiar el código HTML (quitar etiquetas duplicadas o sin cerrar).  
- [ ] Revisar funciones JS:  
   - [ ] Validación de formularios.  
   - [ ] Actualización de datos (fetch o AJAX).  
   - [ ] Manejo de errores en la UI.  

---

## ✅ 4. Pruebas y estabilidad
- [ ] Probar con **datos vacíos o incorrectos** (ejemplo: campos sin llenar, texto en vez de números).  
- [ ] Revisar **mensajes de error** → que sean claros para el usuario.  
- [ ] Crear al menos **2-3 pruebas unitarias** en el backend con JUnit (ejemplo: guardar un usuario, validar login).  
- [ ] Probar la app en distintos navegadores (Chrome, Edge, Firefox).  

---

## 🚀 5. Mejoras opcionales
- [ ] Implementar notificaciones o mensajes de éxito/error más bonitos (por ejemplo con librerías JS como SweetAlert2).  
- [ ] Optimizar consultas a la base de datos (usar `@Query` o revisar índices si la app crece).  
- [ ] Mejorar la seguridad mínima (enmascarar contraseñas, validar inputs en backend).  
- [ ] Agregar documentación básica (`README.md` en GitHub con pasos para correr el proyecto).  

---

## 🎯 Recomendación
Empieza cerrando las tareas de **Backend y Frontend esenciales**, luego pasa a las pruebas, y al final las mejoras opcionales.
