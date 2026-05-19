# 🐾 Pawsitters - Pet Holiday Platform

DHBW Projektarbeit. Plattform, die Tierhalter mit Gastgebern verbindet.

## Aktueller Stand: Stammdaten komplett

Alle Stammdaten-Entitäten und ihre Verwaltung sind umgesetzt: Tierhalter,
Gastgeber und Haustiere. Die Schichtenarchitektur ist etabliert, jede
Entität hat ihre Service- und Repository-Klasse mit Unit-Tests.

## Fortschritt der Funktionalitäten

- [x] Profile für Tierhalter
- [x] Profile für Gastgeber
- [x] Registrierung von Haustieren
- [ ] Erstellung einer Betreuungsanfrage (Zeitraum)
- [ ] Anzeige passender Angebote
- [ ] Versenden von Angeboten durch Gastgeber
- [ ] Annahme eines Angebots durch den Tierhalter
- [ ] Ablehnung weiterer Angebote
- [ ] Aktualisierung des Status einer Anfrage

## Technologie-Stack

- Java 17, Spring Boot 3.3.4, Maven
- H2 In-Memory Datenbank, Thymeleaf
- JUnit 5 + Mockito

## Setup & Start

```bash
mvn spring-boot:run
```

Browser: <http://localhost:8080>

## Tests ausführen

```bash
mvn test
```

Aktuell: **10 Tests** (1 Smoke + 3 PetOwner + 3 Host + 3 Pet).
