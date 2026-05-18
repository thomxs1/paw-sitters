# 🐾 Pawsitters - Pet Holiday Platform

DHBW Projektarbeit. Plattform, die Tierhalter mit Gastgebern verbindet, die
deren Haustiere während eines Urlaubs gegen Bezahlung betreuen.

## Aktueller Stand: Projekt-Setup

Initiales Projekt-Setup mit Spring Boot und Maven. Die Anwendung startet,
zeigt eine Willkommensseite und die CI-Pipeline ist eingerichtet.

## Geplante Funktionalitäten

Laut Aufgabenstellung sollen folgende Features umgesetzt werden:

- [ ] Profile für Tierhalter
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
- JUnit 5

## Voraussetzungen

- JDK 17+
- Maven 3.6+

## Setup & Start

```bash
mvn spring-boot:run
```

Anschließend Browser öffnen: <http://localhost:8080>

## Tests ausführen

```bash
mvn test
```
