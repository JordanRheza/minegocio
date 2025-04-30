# Documentación del Proyecto

> **Este proyecto utiliza Java 17 y PostgreSQL 16 como base.**

## Características implementadas

- *Swagger:* Se implementó Swagger para interactuar fácilmente con el API.  
  URL de acceso: [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)

---

## Base de datos

1. Crear una base de datos en PostgreSQL con el nombre *prueba*.
2. Las credenciales predeterminadas que utiliza el API son:  
   - *Usuario:* postgres  
   - *Contraseña:* admin  

   > *Nota:* Se recomienda cambiar estas credenciales en caso de ser necesario.

---

## Importación desde Postman

Se agregó al proyecto un archivo con los endpoints y los requests, que se puede importar directamente desde Postman para realizar pruebas y consultas al API de manera sencilla.  
Se encuentra en `rosources/static`.

---

## Tabla sucursal

- Se añadió el campo *matriz* (tipo boolean) en la tabla sucursal, con la siguiente lógica:
  - *true*: Identifica la matriz (siempre será el primer registro de la sucursal del cliente).
  - *false*: Identifica las sucursales adicionales del cliente.

---

## Eliminación en cascada

Al eliminar un cliente, se eliminan automáticamente todas las sucursales asociadas a ese cliente.
