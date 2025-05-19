###   ‍🧑‍🍳 FridgeChef

**FridgeChef** er en brugervenlig JavaFX-applikation, som hjælper dig med at finde opskrifter baseret på ingredienserne i dit køleskab. Appen integrerer med Spoonacular API for at hente opskrifter og ingrediensinformation, og den understøtter brugerlogin, favoritter, og et virtuelt køleskab.

---

## 🔧 Funktioner

- Brugerhåndtering: Opret konto, log ind og administrer din profil.

- Virtuelt køleskab: Tilføj og fjern ingredienser, så appen kan foreslå opskrifter ud fra dine råvarer.

- Opskriftsøgning: Find opskrifter baseret på ingredienser, køkkentype, diætpræferencer (vegetar/vegan) og intolerancer.

- Detaljerede opskrifter: Se trin-for-trin instruktioner, ingredienser og billed af opskriften.

- Favoritter: Gem dine yndlingsopskrifter til nem adgang senere.

---

## 🛠️ Teknologier

- Java 23

- JavaFX til GUI

- Spoonacular API til opskriftsdata

- JUnit til test

---

## 🏗️ Arkitektur og design

- AppManager: Central logikklasse, der håndterer data og kommunikation mellem UI og API.

- SceneNavigator: Skifter mellem forskellige views i JavaFX.

- Model: Klasser som Account, Dish, Ingredient der repræsenterer data.

- Controller: Styrer brugerinteraktion og opdaterer views.

---

## 🚀 Kom godt i gang

- Klon projektet:
- git clone https://github.com/mortenjenne/fridgechef.git

- Konfigurer API-nøgler:
- Indsæt din Spoonacular API-nøgle i RecipeApiClient (feltet apiKeyInUse).

- Byg og kør:
- Kør appen via din IDE

- Brug appen:
- Opret konto, tilføj ingredienser og begynd at søge opskrifter!

---

## 📂 Mappe struktur

```text
src/
├── main/
│   ├── java/
│   │   └── io/github/mortenjenne/fridgechef/
│   │       ├── FridgeApp.java   # Main-klasse (starter applikationen)
│   │       ├── controller/      # JavaFX-controllere
│   │       ├── logic/           # Forretningslogik: AppManager til scenestyring og koordinering
│   │       ├── model/           # Datamodeller (Account, Recipe, Ingredient, osv.)
│   │       └── util/            # Databasehåndtering, API-klient og JSON-parser
│   └── resources/
│       └── io/github/mortenjenne/fridgechef/
│           ├── *.fxml           # JavaFX-layoutfiler og Billeder og ikoner
```

---

## 👥 Gruppemedlemmer

- Jesper Thouby Andersen

- Daniel Schibbye Hangaard

- Toby Alexander West Mietke Hartzberg

- Morten Jensen

---

## 📌 Status
Projektet er funktionsdygtigt og i løbende udvikling. Flere features og tests vil løbende blive tilføjet.