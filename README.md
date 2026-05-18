# 🐾 Pawsitters - Pet Holiday Platform

DHBW Projektarbeit. Plattform, die Tierhalter mit Gastgebern verbindet.

## Aktueller Stand: Tierhalter-Verwaltung

Erste fachliche Iteration: Verwaltung von Tierhaltern komplett umgesetzt
inklusive Schichtenarchitektur (Controller → Service → Repository → Entity)
und Unit-Tests.

## Fortschritt der Funktionalitäten

- [x] Profile für Tierhalter
- [ ] Profile für Gastgeber
- [ ] Registrierung von Haustieren
- [ ] Erstellung einer Betreuungsanfrage (Zeitraum)
- [ ] Anzeige passender Angebote
- [ ] Versenden von Angeboten durch Gastgeber
- [ ] Annahme eines Angebots durch den Tierhalter
- [ ] Ablehnung weiterer Angebote
- [ ] Aktualisierung des Status einer Anfrage

## Technologie-Stack

- Java 17
- Spring Boot 3.3.4
- Maven
- H2 In-Memory Datenbank
- Thymeleaf
- JUnit 5 + Mockito

## Setup & Start

```bash
mvn spring-boot:run
```

Anschließend Browser öffnen: <http://localhost:8080>

## Tests ausführen

```bash
mvn test
```

Aktuell: **4 Tests** (1 Smoke-Test + 3 Unit-Tests für `PetOwnerService`).
