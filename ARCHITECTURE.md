# Architekturdokumentation

## Architekturentscheidung

Für Pawsitters wurde eine **klassische Schichtenarchitektur (MVC)** in einem
Monolith gewählt — keine Microservices.

### Begründung

| Kriterium | Schichtenarchitektur | Microservices |
|---|---|---|
| Team-Größe (3 Personen) | ✅ Gut handhabbar | ❌ Overhead zu hoch |
| Domänenkopplung | ✅ Entitäten stark verknüpft | ❌ Verteilte Transaktionen nötig |
| Projektdauer | ✅ Schnell aufzusetzen | ❌ Mehr Infrastruktur |
| CI-Aufwand | ✅ Eine Pipeline | ❌ Pro Service eine Pipeline |
| Deployment-Komplexität | ✅ Eine JAR | ❌ Mehrere Services + Orchestrierung |

Die Domäne ist stark relational: Owner besitzen Pets, Pets sind Subjekt von
Requests. Diese starken Beziehungen würden bei einer Microservice-Aufteilung
verteilte Transaktionen erzwingen — für ein Studienprojekt zu aufwändig.

## Schichten

```
Präsentation (Thymeleaf)
        ↑
Controller (Spring MVC)
        ↑
Service (Geschäftslogik)
        ↑
Repository (Spring Data JPA)
        ↑
Persistenz (H2 In-Memory)
```

## Domänenmodell (aktueller Stand)

```
        ┌──────────┐   1:N   ┌──────┐   N:1   ┌─────────────┐
        │ PetOwner │─────────│ Pet  │─────────│ CareRequest │
        └──────────┘         └──────┘         └─────────────┘

                              ┌──────┐
                              │ Host │       (noch ohne Verbindung
                              └──────┘        zu Anfragen)
```

### Entities

| Entity | Verantwortung |
|---|---|
| `PetOwner` | Tierhalter mit Profil |
| `Host` | Gastgeber mit akzeptierten Tierarten, Verfügbarkeit, Preis/Woche |
| `Pet` | Haustier, gehört zu einem Owner |
| `CareRequest` | Betreuungswunsch eines Owners für einen Zeitraum |

In der nächsten Iteration kommt `Offer` dazu — die Verbindung zwischen
`Host` und `CareRequest`.

## Designentscheidungen

### Constructor Injection statt Field-Injection

Alle Services verwenden Constructor Injection, damit Felder `final` sein
können und ohne Spring testbar bleiben.

### Matching-Logik in der Entity (`Host.canAccommodate`)

Die Prüfung „kann dieser Host die Tierart und den Zeitraum?" steht direkt
auf `Host`. Tell-Don't-Ask-Prinzip.

### H2 In-Memory

H2 läuft in derselben JVM, kein Setup nötig. Für Produktion würde man auf
PostgreSQL wechseln (nur `application.properties` ändern).

### Thymeleaf

Serverseitiges Rendering, keine zweite Codebase, Spring Boot integriert
es out-of-the-box.
