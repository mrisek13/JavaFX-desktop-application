package com.pokedex.viewmodel;

import com.pokedex.model.*;
import javafx.beans.property.*;

public class PokemonViewModel {

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty type;
    private final StringProperty secondaryType;
    private final StringProperty category;
    private final StringProperty description;
    private final ObjectProperty<Stats> stats;
    private final ObjectProperty<Abilities> abilities;
    private final DoubleProperty height;
    private final DoubleProperty weight;
    private final ObjectProperty<Gender> gender;
    private final ObjectProperty<EvolvesTo> evolvesTo;
    private final IntegerProperty captureRate;
    private final IntegerProperty baseExperience;
    private final IntegerProperty generation;
    private final BooleanProperty isLegendary;

    public PokemonViewModel(Pokemon pokemon) {
        this.id = new SimpleStringProperty(pokemon.getId());
        this.name = new SimpleStringProperty(pokemon.getName());
        this.type = new SimpleStringProperty(pokemon.getType());
        this.secondaryType = new SimpleStringProperty(pokemon.getSecondaryType());
        this.category = new SimpleStringProperty(pokemon.getCategory());
        this.description = new SimpleStringProperty(pokemon.getDescription());
        this.stats = new SimpleObjectProperty<>(pokemon.getStats());
        this.abilities = new SimpleObjectProperty<>(pokemon.getAbilities());
        this.height = new SimpleDoubleProperty(pokemon.getHeight());
        this.weight = new SimpleDoubleProperty(pokemon.getWeight());
        this.gender = new SimpleObjectProperty<>(pokemon.getGender());
        this.evolvesTo = new SimpleObjectProperty<>(pokemon.getEvolvesTo());
        this.captureRate = new SimpleIntegerProperty(pokemon.getCaptureRate());
        this.baseExperience = new SimpleIntegerProperty(pokemon.getBaseExperience());
        this.generation = new SimpleIntegerProperty(pokemon.getGeneration());
        this.isLegendary = new SimpleBooleanProperty(pokemon.isIsLegendary());
    }

    // Property getteri za JavaFX binding
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty secondaryTypeProperty() {
        return secondaryType;
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public ObjectProperty<Stats> statsProperty() {
        return stats;
    }

    public ObjectProperty<Abilities> abilitiesProperty() {
        return abilities;
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    public ObjectProperty<EvolvesTo> evolvesToProperty() {
        return evolvesTo;
    }

    public IntegerProperty captureRateProperty() {
        return captureRate;
    }

    public IntegerProperty baseExperienceProperty() {
        return baseExperience;
    }

    public IntegerProperty generationProperty() {
        return generation;
    }

    public BooleanProperty isLegendaryProperty() {
        return isLegendary;
    }

    // Standardni getteri za povrat podataka
    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getType() {
        return type.get();
    }

    public String getSecondaryType() {
        return secondaryType.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getDescription() {
        return description.get();
    }

    public Stats getStats() {
        return stats.get();
    }

    public Abilities getAbilities() {
        return abilities.get();
    }

    public double getHeight() {
        return height.get();
    }

    public double getWeight() {
        return weight.get();
    }

    public Gender getGender() {
        return gender.get();
    }

    public EvolvesTo getEvolvesTo() {
        return evolvesTo.get();
    }

    public int getCaptureRate() {
        return captureRate.get();
    }

    public int getBaseExperience() {
        return baseExperience.get();
    }

    public int getGeneration() {
        return generation.get();
    }

    public boolean isLegendary() {
        return isLegendary.get();
    }

    // Standardni setteri za ažuriranje podataka
    public void setId(String id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public void setSecondaryType(String secondaryType) {
        this.secondaryType.set(secondaryType);
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setStats(Stats stats) {
        this.stats.set(stats);
    }

    public void setAbilities(Abilities abilities) {
        this.abilities.set(abilities);
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    public void setGender(Gender gender) {
        this.gender.set(gender);
    }

    public void setEvolvesTo(EvolvesTo evolvesTo) {
        this.evolvesTo.set(evolvesTo);
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate.set(captureRate);
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience.set(baseExperience);
    }

    public void setGeneration(int generation) {
        this.generation.set(generation);
    }

    public void setIsLegendary(boolean isLegendary) {
        this.isLegendary.set(isLegendary);
    }
}
