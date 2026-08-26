# Sistema de Gestión de Tutorías

**Repositorio GitHub:** [https://github.com/infamedtecnologia/sistema-tutorias](https://github.com/infamedtecnologia/sistema-tutorias)

Proyecto académico de la actividad **Ae1 · Diseño orientado a objetos de un
sistema** (UCOM0310 · Diseño de Software · UEES). Modela, mediante
principios de orientación a objetos, el proceso de solicitud, confirmación,
cancelación y reprogramación de tutorías entre estudiantes y docentes.

## Descripción del problema

El sistema debe permitir que:
- los **docentes** publiquen horarios disponibles para tutorías;
- los **estudiantes** soliciten (reserven) uno de esos horarios;
- cada **reserva** siga un ciclo de vida controlado (pendiente → confirmada
  / cancelada / reprogramada);
- los eventos relevantes (creación, confirmación, cancelación,
  reprogramación) se comuniquen a los usuarios sin acoplar la lógica de
  dominio a un canal de notificación específico;
- la información se persista sin acoplar el dominio a una tecnología de
  almacenamiento concreta.

## Clases principales y responsabilidades

| Clase / Interfaz | Responsabilidad |
|---|---|
| `Usuario` (abstracta) | Identidad y datos de contacto comunes a estudiantes y docentes. |
| `Estudiante` | Representa al solicitante de una tutoría. |
| `Docente` | Publica horarios y protege que no se solapen entre sí. |
| `Horario` | Protege su propio estado de disponibilidad (`DISPONIBLE` / `RESERVADO`). |
| `Reserva` | Controla las transiciones válidas de su ciclo de vida. |
| `Notificador` (interfaz) | Abstracción para comunicar eventos de una reserva. |
| `NotificadorEmail` / `NotificadorConsola` | Implementaciones concretas del canal de notificación. |
| `RepositorioReservas` (interfaz) | Abstracción de persistencia de reservas. |
| `RepositorioReservasEnMemoria` | Implementación de persistencia usada en desarrollo/pruebas. |
| `ServicioReservas` | Orquesta el caso de uso: crear, confirmar, cancelar y reprogramar reservas. |

## Decisiones de diseño relevantes

- **Encapsulación real, no solo getters/setters:** `Horario` no expone un
  `setEstado()`; solo permite `marcarComoReservado()` y `liberar()`, que
  validan la transición. Lo mismo ocurre con `Reserva` y sus estados.
- **Composición sobre herencia** entre `ServicioReservas` y sus
  colaboradores (`RepositorioReservas`, `Notificador`): se inyectan por
  constructor en lugar de heredar comportamiento.
- **Herencia solo donde hay una relación "es-un" real:** `Estudiante` y
  `Docente` heredan de `Usuario` porque ambos son, genuinamente, un tipo de
  usuario del sistema con identidad y contacto comunes — no se usó herencia
  como atajo para reutilizar código entre clases sin relación conceptual.

## Principios SOLID aplicados

- **DIP (Dependency Inversion Principle):** `ServicioReservas` depende de
  las interfaces `Notificador` y `RepositorioReservas`, nunca de una
  implementación concreta. Esto permite cambiar el canal de notificación o
  el mecanismo de persistencia sin tocar la lógica de negocio.
- **SRP (Single Responsibility Principle):** `ServicioReservas` solo
  orquesta el ciclo de vida de la reserva; `Docente` solo administra sus
  horarios; `Notificador` solo sabe comunicar eventos. Ningún componente
  mezcla dos razones de cambio distintas.
- **OCP (Open/Closed Principle):** agregar un canal de notificación nuevo
  (por ejemplo `NotificadorConsola`, ya incluido) no requiere modificar
  `Notificador` ni `ServicioReservas`.
- **ISP (Interface Segregation Principle):** `Notificador` expone un único
  método (`notificar`), evitando forzar a las implementaciones a resolver
  responsabilidades que no les corresponden.

Ver justificación ampliada en el PDF de la actividad, sección 4.

## Diagrama UML

Fuente editable: [`docs/modelo-clases.puml`](docs/modelo-clases.puml)
Imagen: [`docs/modelo-clases.png`](docs/modelo-clases.png)

## Requisitos para ejecutar el proyecto

- JDK 17 o superior
- Maven 3.8+

## Compilación

```bash
mvn clean compile
```

## Pruebas

```bash
mvn clean test
```

## Ejecución de la demostración

```bash
mvn compile exec:java -Dexec.mainClass="edu.uees.tutorias.Main"
```

## Estructura del repositorio

```
sistema-tutorias/
├── README.md
├── pom.xml
├── docs/
│   ├── modelo-clases.puml
│   └── modelo-clases.png
└── src/
    ├── main/java/edu/uees/tutorias/
    │   ├── Main.java
    │   ├── domain/
    │   ├── service/
    │   ├── notification/
    │   └── repository/
    └── test/java/edu/uees/tutorias/service/
```

## Declaración de uso de inteligencia artificial

Durante el desarrollo de esta actividad utilicé herramientas de
inteligencia artificial. Las utilicé para: organizar el análisis del
dominio, revisar la consistencia entre el diagrama UML y el código Java, y
recibir retroalimentación sobre la aplicación de principios SOLID. Verifiqué
y adapté las respuestas obtenidas, y puedo explicar y justificar el código
y las decisiones presentadas.
