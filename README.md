# Sistema de Gestión de Empleados

## Descripción

Este proyecto es una aplicación de consola para la gestión de empleados que permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) utilizando JPA para la persistencia de datos.

## Requisitos Previos

- Java JDK 8 o superior
- MySQL Server
- Maven

## Configuración de la Base de Datos

1. Se adjunta una base de datos MySQL llamada `empleados`
2. El usuario por defecto es `root` sin contraseña
3. Si necesitas modificar las credenciales, puedes hacerlo en el archivo `src/main/resources/META-INF/persistence.xml`

## Instalación

1. Clonar el repositorio:
   ```
   git clone https://github.com/rosmander/BlancoPerez_pruebatec1.git
   ```
2. Navegar hasta el directorio del proyecto:
   ```
   cd BlancoPerez_pruebatec1
   ```
3. Ejecutar el comando `mvn clean install` para compilar y ejecutar el proyecto.
   ```
   mvn clean install
   ```
4. Ejecutar el comando `java -jar src/main/java/org/example/Main.jar
` para ejecutar el proyecto.
   `    java -jar src/main/java/org/example/Main.jar
   `

## Funcionalidades

La aplicación permite:

1. Agregar nuevos empleados
2. Listar todos los empleados
3. Actualizar información de empleados existentes
4. Eliminar empleados
5. Buscar empleados por cargo

## Validaciones Implementadas

- Nombres, apellidos y cargos: solo letras y espacios
- Salarios: solo valores numéricos
- Fechas: formato dd/mm/yyyy con validación de días y meses válidos
- IDs: verificación de existencia antes de actualizar o eliminar

## Supuestos Considerados

1. **Base de Datos:**

   - Se asume que el servidor MySQL está corriendo en localhost:3306
   - La base de datos se crea automáticamente si no existe

2. **Datos de Empleados:**

   - Los nombres, apellidos y cargos pueden contener caracteres especiales (á, é, í, ó, ú, ñ)
   - No se permiten caracteres numéricos en nombres, apellidos y cargos
   - El salario se asume como un valor decimal positivo
   - La fecha de inicio no puede ser validada contra fechas futuras o pasadas específicas

3. **Operaciones:**

   - No se implementó un límite de intentos para las validaciones de entrada
   - La búsqueda por cargo es case-sensitive
   - No se implementó un sistema de autenticación de usuarios
   - Los IDs son autogenerados y no pueden ser modificados manualmente

4. **Persistencia:**
   - Se utiliza la estrategia de generación de ID AUTO_INCREMENT
   - La conexión a la base de datos se cierra solo al salir de la aplicación

## Estructura del Proyecto
sql/
└── empleados.sql
src/
├── main/
│ ├── java/
│ │ └── org/example/
│ │ ├── logica/
│ │ │ └── Empleado.java
│ │ ├── persistencia/
│ │ │ └── ControladoraPersistencia.java
│ │ └── Main.java
│ └── resources/
│ └── META-INF/
│ └── persistence.xml

## Manejo de Errores

- Validación de entrada de datos
- Verificación de existencia de registros
- Manejo de conexiones a base de datos
- Validación de formatos de fecha

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue primero para discutir los cambios que te gustaría realizar.
