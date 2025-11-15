# API de Productos

Esta guía te permitirá utilizar la API RESTful de productos. En este lugar podrás encontrar indicaciones, acciones disponibles, ejemplos, tipos de respuesta y tecnologías detrás de la API para la integración en tus proyectos sea sencilla y sin dolores de cabeza.

---

## Alojamiento de la API

La API se encuentra disponible en:

```
http://localhost:8081
```

---

## Seguridad y Autenticación

Esta API está protegida usando **Spring Security**. Para consumir los endpoints, se requiere **Autenticación Básica**.

Las credenciales de prueba configuradas son:

* **Usuario:** `jleon`
* **Contraseña:** `GTICS_L9`

### ¿Cómo autenticarse?

Para autenticarse el cliente debe enviar un encabezado HTTP `Authorization` en cada solicitud. Este encabezado se construye combinando el usuario y la contraseña con un dos puntos (`:`), codificando el resultado en **Base64**, y añadiendo el prefijo `Basic `.

1.  **Combinar:** `jleon:GTICS_L9`
2.  **Codificar en Base64:** `amxlb246bGFib3JhdG9yaW9fOQ==`
3.  **Encabezado final:**

```http
Authorization: Basic amxlb246bGFib3JhdG9yaW9fOQ==
```

## Acciones disponibles

### 1. Obtener todos los productos
- **GET** `/api/product`
- **Retorna** Una lista de productos con toda la información relevante.

### 2. Buscar un producto por su ID
- **GET** `/api/product/{id}`
- **Se requiere** El ID del producto.
- **Retorna** El producto si existe, o un mensaje claro si no.

### 3. Crear un producto nuevo
- **POST** `/api/product`
- **Se envía** Un JSON con los datos del producto (nombre, precio, etc.).
- **Se recibe** Confirmación y el producto creado.

### 4. Actualizar un producto existente
- **PUT** `/api/product`
- **Se envía** Un JSON con el ID y los nuevos datos del producto.
- **Se recibe** Mensaje de éxito o error si el producto no existe.

### 5. Eliminar un producto
- **DELETE** `/api/product/{id}`
- **Se requiere** El ID del producto a eliminar.
- **Se retorna** Confirmación de eliminación o aviso si no existe.

---

## Ejemplo de producto

```JSON
{
  "id": 1,
  "productName": "Chais",
  "supplier": {
    "id": 1,
    "supplierName": "Exotic Liquid",
    "contactName": "Charlotte Cooper",
    "address": "49 Gilbert St.",
    "city": "Londona",
    "postalCode": "EC1 4SD",
    "country": "UK",
    "phone": "(171) 555-2222"
  },
  "category": {
    "id": 1,
    "categoryName": "Beverages",
    "description": "Soft drinks, coffees, teas, beers, and ales"
  },
  "unit": "10 boxes x 20 bags",
  "price": 18
}
```

---

## Respuestas y posibles errores

- **200 OK**: Todo bien.
- **401 Unauthorized**: Autenticación fallida. Credenciales incorrectas o no proporcionadas.
- **404 Not Found**: No se encontró el producto.
- **500 Internal Server Error**: Algo falló en el servidor.

---

## Tecnologías detrás de la API

- Java 17
- Spring Boot
- JPA
- MySQL
- Maven
- Spring Security

---

## Creado por jesus.oropezal@pucp.edu.pe

Desarrollada para facilitar la gestión de productos y una fácil integración en tus proyectos
