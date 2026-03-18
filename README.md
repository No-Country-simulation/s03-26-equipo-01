# 🤓 Testimonial CMS - Backend API

Este es el esqueleto inicial para el backend del gestor de testimonios. Está construido siguiendo una arquitectura de capas (Controller, Service, Repository, Entity) para facilitar la división de tareas en el equipo.

## 🛠️ Tecnologías utilizadas
- **Java** - **Spring Boot**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **Lombok**

## 🚀 Estado Actual (Setup Inicial)
- [x] Configuración del proyecto base en Spring Initializr.
- [x] Conexión a la base de datos MySQL configurada.
- [x] Entidad principal `Testimonial` creada con manejo de estados (`PENDING`, `PUBLICADO`, `RECHAZADO`).
- [x] Endpoints básicos creados (`POST` y `GET` en `/api/testimonials`).
- [x] Spring Security desactivado temporalmente para facilitar las pruebas de desarrollo del equipo Frontend.

## ⚙️ Cómo correr el proyecto en local

1. **Crear la Base de Datos:**
   Abre tu consola de MySQL o Workbench y ejecuta este comando para crear el contenedor vacío:
   ```sql
   CREATE DATABASE testimonial_cms_db;
