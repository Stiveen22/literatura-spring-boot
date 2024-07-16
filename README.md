# Proyecto LiterAlura - Sistema de Gestión de Libros

Este proyecto utiliza Spring Boot y Java para gestionar y mostrar información sobre libros utilizando la API Gutendex, un catálogo de más de 70,000 libros de Project Gutenberg.

## Descripción

El sistema permite realizar consultas a la API Gutendex para obtener información detallada sobre libros, incluyendo títulos, autores, temas, formatos y más. Utiliza Spring Boot para la configuración del backend y Gson para el manejo de datos en formato JSON.

## Configuración del Entorno de Desarrollo

### Requisitos Previos

- Java JDK 11 o superior
- Maven 3.x
- IDE compatible con Java (Eclipse, IntelliJ IDEA, etc.)

### Dependencias en pom.xml

Asegúrate de tener las siguientes dependencias en tu archivo `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Gson -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>

    <!-- Jackson -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.16.0</version>
    </dependency>

    <!-- HttpClient -->
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.13</version>
    </dependency>
</dependencies>
