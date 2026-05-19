# 🐾 Pawsitters - Pet Holiday Platform

DHBW Projektarbeit. Plattform, die Tierhalter mit Gastgebern verbindet, die
deren Haustiere während eines Urlaubs gegen Bezahlung betreuen.

## Inhaltsverzeichnis

- [Funktionale Anforderungen](#funktionale-anforderungen)
- [Technologie-Stack](#technologie-stack)
- [Voraussetzungen](#voraussetzungen)
- [Setup & Start](#setup--start)
- [Projektstruktur](#projektstruktur)
- [Benutzung](#benutzung)
- [Tests ausführen](#tests-ausführen)
- [Weitere Dokumentation](#weitere-dokumentation)

## Funktionale Anforderungen

Alle Anforderungen aus der Aufgabenstellung sind umgesetzt:

| # | Anforderung | Umsetzung |
|---|---|---|
| 1 | Profile für Tierhalter | `PetOwner` + `/owners` |
| 2 | Profile für Gastgeber | `Host` + `/hosts` |
| 3 | Registrierung von Haustieren | `Pet` + `/pets` |
| 4 | Erstellung einer Betreuungsanfrage (Zeitraum) | `CareRequest` + `/requests` |
| 5 | Anzeige passender Angebote | `/requests/{id}` (Match-Logik + Angebotsliste) |
| 6 | Versenden von Angeboten durch Gastgeber | `OfferController.create` |
| 7 | Annahme eines Angebots | `OfferService.acceptOffer` |
| 8 | Ablehnung weiterer Angebote | Geschieht automatisch bei Annahme, oder manuell |
| 9 | Aktualisierung des Anfragestatus | `OPEN → IN_PROGRESS → MATCHED / CANCELLED` |

## Technologie-Stack

- **Java 17**
- **Spring Boot 3.3.4** (Web, JPA, Validation, Thymeleaf)
- **Maven** (Build)
- **H2** (In-Memory-Datenbank, keine Installation nötig)
- **Thymeleaf** (HTML-Templates)
- **JUnit 5 + Mockito** (Tests)

## Voraussetzungen

Auf dem Entwicklungsrechner installiert sein müssen:

- **JDK 17 oder neuer** — Prüfung: `java -version`
  Falls nicht: [Eclipse Temurin 17](https://adoptium.net/) herunterladen
- **Maven 3.6+** — Prüfung: `mvn -version`
  Bei Windows: `winget install Apache.Maven`
- **Git**

VS Code mit dem **Extension Pack for Java** und der **Spring Boot Extension**
genügt; eine extra IDE ist nicht nötig. H2 läuft im Speicher und braucht
keinen DB-Server.

## Setup & Start

```bash
# Repository klonen
git clone <repo-url>
cd pawsitters

# Anwendung starten
mvn spring-boot:run
```

Anschließend Browser öffnen: <http://localhost:8080>

Optionale Datenbank-Konsole (zur Inspektion): <http://localhost:8080/h2-console>
- JDBC URL: `jdbc:h2:mem:pawsitters`
- User: `sa` (kein Passwort)

## Projektstruktur

```
pawsitters/
├── pom.xml                          Maven Build-Konfiguration
├── README.md                        Diese Datei
├── ARCHITECTURE.md                  Architekturdokumentation
├── TEST_DOCUMENTATION.md            Testdokumentation
├── SECURITY_CONCEPT.md              Security-Konzept + Shift Security Left
├── DEVELOPMENT_PROCESS.md           Team-Organisation & Reflexion
├── KI_PROMPTS.md                    Eingesetzte KI-Tools & Prompts
├── .github/workflows/ci.yml         CI-Pipeline (führt Tests bei Push aus)
├── .gitignore
└── src/
    ├── main/
    │   ├── java/com/pawsitters/
    │   │   ├── PawsittersApplication.java   Spring-Boot-Einstiegspunkt
    │   │   ├── model/                        JPA-Entities + Enums
    │   │   │   ├── PetOwner.java
    │   │   │   ├── Host.java
    │   │   │   ├── Pet.java
    │   │   │   ├── CareRequest.java
    │   │   │   ├── Offer.java
    │   │   │   ├── AnimalType.java
    │   │   │   ├── RequestStatus.java
    │   │   │   └── OfferStatus.java
    │   │   ├── repository/                   Spring Data JPA Repositories
    │   │   ├── service/                      Geschäftslogik
    │   │   └── controller/                   Spring MVC Controller
    │   └── resources/
    │       ├── application.properties
    │       ├── static/css/style.css
    │       └── templates/                    Thymeleaf HTML
    │           ├── index.html
    │           ├── owners/
    │           ├── hosts/
    │           ├── pets/
    │           ├── requests/
    │           └── offers/
    └── test/
        └── java/com/pawsitters/
            ├── service/                      Unit-Tests (17)
            └── integration/                  Integrationstests (2)
```

## Benutzung

Typischer Ablauf in der Anwendung:

1. **Tierhalter anlegen** unter `/owners/new`
2. **Haustier registrieren** unter `/pets/new` (Owner auswählen)
3. **Gastgeber anlegen** unter `/hosts/new` (Tierarten, Verfügbarkeit, Preis/Woche)
4. **Anfrage erstellen** unter `/requests/new` (Haustier + Zeitraum)
5. Anfrage öffnen → **passende Gastgeber** werden gelistet
6. **Angebot senden** (`Angebot senden` Button)
7. In der Detailansicht der Anfrage **Angebot annehmen** —
   alle anderen werden automatisch als REJECTED markiert

## Tests ausführen

```bash
# Alle Tests
mvn test

# Nur Unit-Tests
mvn test -Dtest='*ServiceTest'

# Nur Integrationstests
mvn test -Dtest='*IntegrationTest'
```

Erwartung: **19 Tests, 0 Fehler** (17 Unit + 2 Integrationstests).
Details in [TEST_DOCUMENTATION.md](TEST_DOCUMENTATION.md).

## Weitere Dokumentation

- [ARCHITECTURE.md](ARCHITECTURE.md) — Begründung der Schichtenarchitektur
- [TEST_DOCUMENTATION.md](TEST_DOCUMENTATION.md) — alle Tests im Detail
- [SECURITY_CONCEPT.md](SECURITY_CONCEPT.md) — Sicherheitskonzept + Shift Security Left
- [DEVELOPMENT_PROCESS.md](DEVELOPMENT_PROCESS.md) — Zusammenarbeit, Git-Workflow, Reflexion
- [KI_PROMPTS.md](KI_PROMPTS.md) — eingesetzte KI-Tools
