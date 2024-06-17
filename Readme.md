# Aplicación AlmFit

AlmFit es una aplicación web diseñada para ayudar a los usuarios a gestionar su nutrición diaria y su ingesta de alimentos. La aplicación utiliza tecnologías modernas como Kubernetes, React JS, OAuth 2.0 y Spring Boot para proporcionar una solución robusta y escalable.

## Tabla de Contenidos

- [Introducción](#introducción)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Contribuir](#contribuir)
- [Licencia](#licencia)

## Introducción

Estas instrucciones te ayudarán a configurar y ejecutar el proyecto en tu máquina local para fines de desarrollo y pruebas.

## Requisitos Previos

Antes de comenzar, asegúrate de tener el siguiente software instalado en tu sistema:

- Docker
- Docker Compose

## Instalación

1. **Clonar el repositorio:**

   ```sh
   git clone https://github.com/valentintic/almfit.git
   cd almfit
    ```
   
2. **Construir e iniciar la aplicación:**

    ```sh
    Copiar código
    docker-compose up --build
    Uso
    Una vez que la aplicación esté en funcionamiento, puedes acceder a ella en http://localhost:3000.
    ```
   
3. **Iniciar sesión en la aplicación:**
    
        ```
        Para iniciar sesión en la aplicación, puedes utilizar las siguientes credenciales:
        Usuario: user
        Contraseña: password

        ```
4. **FrontEnd**

    ```
    acceder al front end realizar el comando npm run dev para lanzar React JS
   Acceder atraves de al url 'http://localhost:3000'
    ```

Tecnologías Utilizadas
FrontEnd:

React JS
JSX
AXIOS para solicitudes HTTP
BackEnd:

Spring Boot
Spring Security
Spring JPA
JWT Token para autenticación
OAuth 2.0 para autenticación de terceros
Base de Datos:


Contribuir
Damos la bienvenida a contribuciones al proyecto AlmFit. Para contribuir:

Haz un fork del repositorio.
Crea una nueva rama.
Realiza tus cambios.
Envía un pull request.
Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

