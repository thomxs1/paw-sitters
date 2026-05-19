# 🐾 Pawsitters - Pet Holiday Platform

DHBW Projektarbeit. Plattform, die Tierhalter mit Gastgebern verbindet.

## Aktueller Stand: Betreuungsanfragen

Tierhalter können nun Betreuungsanfragen mit Zeitraum erstellen.
Das System zeigt passende Gastgeber an (Match nach Tierart und
Verfügbarkeit). Status-Workflow ist implementiert (OPEN → CANCELLED).
Architekturdokumentation ergänzt.

## Fortschritt der Funktionalitäten

- [x] Profile für Tierhalter
- [x] Profile für Gastgeber
- [x] Registrierung von Haustieren
- [x] Erstellung einer Betreuungsanfrage (Zeitraum)
- [x] Anzeige passender Angebote *(passende Gastgeber)*
- [ ] Versenden von Angeboten durch Gastgeber
- [ ] Annahme eines Angebots durch den Tierhalter
- [ ] Ablehnung weiterer Angebote
- [x] Aktualisierung des Status einer Anfrage *(OPEN, CANCELLED)*

## Setup & Start

```bash
mvn spring-boot:run
```

Browser: <http://localhost:8080>

## Tests

```bash
mvn test
```

Aktuell: **14 Tests** (1 Smoke + 3 PetOwner + 3 Host + 3 Pet + 4 CareRequest).

## Dokumentation

- [ARCHITECTURE.md](ARCHITECTURE.md) — Architektur und Designentscheidungen
