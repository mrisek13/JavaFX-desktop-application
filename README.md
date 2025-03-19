# Pokédex GUI - JavaFX desktop application

![JavaFX](https://img.shields.io/badge/JavaFX-UI-blue) ![XML](https://img.shields.io/badge/XML-Data-orange) ![License](https://img.shields.io/badge/license-MIT-green)

A **Pokédex** application built using **JavaFX** for the graphical user interface and **XML** for data storage and parsing.

## 🛠️ Technologies Used
- **JavaFX**: Provides an interactive UI for viewing Pokémon data.
- **XML**: Stores Pokémon data and is parsed for reading and writing.
- **FXML**: Used for UI structure and design.
- **CSV Export**: Allows exporting Pokémon data to CSV files.
- **Java Collections (ObservableList)**: Manages Pokémon data dynamically.

## 📂 Project Structure

- 📦 **src/**
  - 📂 **com.pokedex.gui/** – JavaFX GUI logic
  - 📂 **com.pokedex.model/** – Pokémon data model
  - 📂 **com.pokedex.parser/** – XML parsing & CSV export
  - 📂 **resources/** – XML data storage
  - 📜 **pokedex.xml** – Pokémon dataset

## 🖥️ Features
✔ **Pokémon List**: Browse all Pokémon from `pokedex.xml`  
✔ **Search Functionality**: Find Pokémon by name  
✔ **Detailed View**: View all stats, abilities, height, weight, and evolution chain  
✔ **Charts & Visuals**: Bar Chart for stats, Pie Chart for gender distribution  
✔ **Add/Edit/Delete Pokémon**: Modify XML data dynamically  
✔ **CSV Export**: Save Pokémon list as a CSV file  

## 📜 XML Data Format
Each Pokémon is stored in `pokedex.xml` in the following structure:

```xml
<Pokemon id="004">
    <Name>Charmander</Name>
    <Type>Fire</Type>
    <Category>Lizard Pokémon</Category>
    <Stats>
        <HP>39</HP>
        <Attack>52</Attack>
        <Defense>43</Defense>
        <Speed>65</Speed>
        <SpecialAttack>60</SpecialAttack>
        <SpecialDefense>50</SpecialDefense>
        <Total>309</Total>
    </Stats>
    <Abilities>
        <Ability>Blaze</Ability>
        <HiddenAbility>Solar Power</HiddenAbility>
    </Abilities>
    <Height>0.6</Height>
    <Weight>8.5</Weight>
    <Gender>
        <Male>60.5</Male>
        <Female>39.5</Female>
    </Gender>
    <EvolvesTo id="005">Charmeleon</EvolvesTo>
    <CaptureRate>45</CaptureRate>
    <BaseExperience>62</BaseExperience>
    <Generation>1</Generation>
    <IsLegendary>false</IsLegendary>
</Pokemon>
```

## 📤 CSV Export Format

Pokémon data can be exported to CSV using the built-in **Export to CSV** feature:

```csv
Name,Type,Secondary Type,Category,Height,Weight,Legendary
Charmander,Fire,,Lizard Pokémon,0.6,8.5,No
Bulbasaur,Grass,Poison,Seed Pokémon,0.7,6.9,No
```

## 🚀 Getting Started

Clone the repository:

```sh
git clone https://github.com/yourusername/pokedex-gui.git
```

## 🚀 Getting Started

### 📂 Open the Project
1. Open the project in **IntelliJ IDEA** or **Eclipse**.
2. Ensure that **JavaFX** is properly configured in your IDE.
3. Open the `PokedexGUI.java` file in the `com.pokedex.gui` package.
4. Run the application and start exploring your **Pokédex**! 🎉

---

## 📄 License

This project is licensed under the **MIT License** – feel free to use, modify, and contribute.  

⭐ **Enjoy exploring the Pokédex with JavaFX & XML!** 🏆
