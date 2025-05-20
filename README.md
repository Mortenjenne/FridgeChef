# 🧑‍🍳 FridgeChef

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

- MySQL via Aiven

- Spoonacular API til opskriftsdata

- JUnit5 til test

---

## 🏗️ Arkitektur og design
Projektet anvender Facade Design Pattern med AppManager som central facade, der håndterer data og kommunikation mellem UI og API.

- AppManager: Facadeklasse, der styrer hovedlogik og koordinerer appens funktioner.,
- SceneNavigator: Ansvarlig for navigation mellem views i JavaFX.,
- Model: Datarepræsentation via klasser som Account, Dish og Ingredient.,
  - Account implementerer Singleton Design Pattern for at sikre én instans af en brugers lister. Dette forbedrer data-konsistens og forenkler interaktion med databasen.
- Controller: Håndterer brugerinteraktion og opdaterer UI baseret på modellens data.
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

---

# Functional Demands for Recipe and Grocery List App

Introducing an easy-to-use recipe and grocery list app designed to simplify meal planning and shopping. Users can input their ingredients, find matching recipes, and get a grocery list for missing items. The app also includes features like dietary preference filters and saving favorite recipes, all while offering a smooth user experience.

## User Input
The app should allow users to input a list of ingredients they already have. It must provide a user-friendly interface for adding, editing, and removing these ingredients to ensure ease of use and accuracy.

## Recipe Matching
The core function of the app is to match user-inputted ingredients with a vast database of recipes. It should offer recipes that can be made with the given ingredients and highlight those that require minimal additional items. Additionally, the app must offer filtering options for dietary preferences, various cuisines, and different meal types, catering to diverse user needs.

## Grocery List Generation
Upon selecting recipes, the app should generate a grocery list of needed items. Users should be able to manually add or edit this list. Furthermore, it should categorize grocery items by type, such as produce or dairy, to streamline the shopping process.

## Additional Features
- The app should enable users to save their favorite recipes for future reference.
- The app must provide nutritional information for recipes.
- Offer step-by-step cooking instructions, complete with timers and tips.

## Technical Requirements
- The app must include user sign-up and login features to manage user data.
- Real-time database synchronization is necessary to keep data accurate and up-to-date.

## User Experience
A key aspect is designing a simple, intuitive, and visually appealing user interface.
