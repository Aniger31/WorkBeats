# ☕ WorkBeats

Aplicación web desarrollada con **Java Spring Boot** para el backend y **HTML, CSS, JavaScript** para el frontend.  
La app utiliza **MySQL** como base de datos y permite gestionar información mediante operaciones CRUD.  

---

## 📌 Características principales
- Backend con Spring Boot y conexión a MySQL.  
- CRUD de entidades principales (usuarios, tareas, recetas, etc.).  
- Frontend con HTML, CSS y JS.  
- Interacción en tiempo real con datos usando JavaScript.  
- Diseño adaptable para dispositivos móviles y escritorio.  

---

## 🛠 Tecnologías usadas
- **Backend:** Java 17+, Spring Boot, Maven  
- **Base de datos:** MySQL  
- **Frontend:** HTML, CSS, JavaScript  
- **Pruebas:** JUnit 5 (para backend)  

---

## 🚀 Instalación y ejecución

### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/tu-repo.git
cd tu-repo
```

### 2️⃣ Configurar la base de datos MySQL
1. Crear una base de datos en MySQL (ejemplo: `mi_app`).  
2. Actualizar el archivo `application.properties` (o `application.yml`) con tus credenciales:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mi_app
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Ejecutar el backend
```bash
mvn spring-boot:run
```

El servidor se levantará en:  
👉 `http://localhost:8080`

### 4️⃣ Acceder al frontend
Abre el archivo `index.html` en tu navegador o configúralo para servir desde Spring Boot.  

---

## 📂 Estructura del proyecto
```
📦 proyecto
 ┣ 📂 src
 ┃ ┣ 📂 main
 ┃ ┃ ┣ 📂 java/com/tuapp   # Backend con Spring Boot
 ┃ ┃ ┣ 📂 resources
 ┃ ┃ ┃ ┣ application.properties
 ┃ ┃ ┃ ┗ templates/         # HTML
 ┃ ┃ ┃ ┗ static/            # CSS y JS
 ┃ ┣ 📂 test                 # Pruebas con JUnit
 ┣ 📜 pom.xml                # Dependencias Maven
 ┣ 📜 README.md              # Este archivo
 ┣ 📜 CHECKLIST.md           # Checklist de mejoras
```

---

## ✅ Checklist de mejoras
El progreso del proyecto se gestiona en el archivo [`CHECKLIST.md`](./CHECKLIST.md).  

---

## ✨ Próximas mejoras
- Mejorar diseño y responsividad (CSS unificado).  
- Implementar pruebas unitarias en el backend.  
- Documentación detallada de la API (Swagger o Postman).  
- Integración de notificaciones más visuales con JavaScript.  

---

## 👩‍💻 Autor
Desarrollado por [Regina Hernandez Rodriguez](https://github.com/Aniger31).  

---

## 📄 Licencia
Este proyecto está bajo la licencia MIT - consulta el archivo LICENSE para más detalles.  
