package com.pokedex.model;

import javafx.beans.property.*;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Pokemon {
    @XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Type")
    private String type;

    @XmlElement(name = "SecondaryType")
    private String secondaryType;

    @XmlElement(name = "Category")
    private String category;

    @XmlElement(name = "Description")
    private String description;

    @XmlElement(name = "Stats")
    private Stats stats;

    @XmlElement(name = "Abilities")
    private Abilities abilities;

    @XmlElement(name = "Height")
    private double height;

    @XmlElement(name = "Weight")
    private double weight;

    @XmlElement(name = "Gender")
    private Gender gender;

    @XmlElement(name = "EvolvesTo")
    private EvolvesTo evolvesTo;

    @XmlElement(name = "CaptureRate")
    private int captureRate;

    @XmlElement(name = "BaseExperience")
    private int baseExperience;

    @XmlElement(name = "Generation")
    private int generation;

    @XmlElement(name = "IsLegendary")
    private boolean isLegendary;

    public Pokemon() {
        
    }

    // JavaFX Properties for TableView
    public StringProperty idProperty() {
        return new SimpleStringProperty(id);
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public StringProperty typeProperty() {
        return new SimpleStringProperty(type);
    }

    public StringProperty secondaryTypeProperty() {
        return new SimpleStringProperty(secondaryType);
    }

    public StringProperty categoryProperty() {
        return new SimpleStringProperty(category);
    }

    public StringProperty descriptionProperty() {
        return new SimpleStringProperty(description);
    }

    public DoubleProperty heightProperty() {
        return new SimpleDoubleProperty(height);
    }

    public DoubleProperty weightProperty() {
        return new SimpleDoubleProperty(weight);
    }

    public IntegerProperty captureRateProperty() {
        return new SimpleIntegerProperty(captureRate);
    }

    public IntegerProperty baseExperienceProperty() {
        return new SimpleIntegerProperty(baseExperience);
    }

    public IntegerProperty generationProperty() {
        return new SimpleIntegerProperty(generation);
    }

    public BooleanProperty isLegendaryProperty() {
        return new SimpleBooleanProperty(isLegendary);
    }

    // Getteri i setteri (ne diramo postojeći kod)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSecondaryType() {        
        return secondaryType;
    }

    public void setSecondaryType(String secondaryType) {
        this.secondaryType = secondaryType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public EvolvesTo getEvolvesTo() {
        return evolvesTo;
    }

    public void setEvolvesTo(EvolvesTo evolvesTo) {
        this.evolvesTo = evolvesTo;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public boolean isIsLegendary() {
        return isLegendary;
    }

    public void setIsLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
    }
    
    public Pokemon(String id, String name, String type, String secondaryType, String category, String description,
               Stats stats, Abilities abilities, double height, double weight, Gender gender,
               EvolvesTo evolvesTo, int captureRate, int baseExperience, int generation, boolean isLegendary) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.secondaryType = secondaryType;
        this.category = category;
        this.description = description;
        this.stats = stats;
        this.abilities = abilities;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.evolvesTo = evolvesTo;
        this.captureRate = captureRate;
        this.baseExperience = baseExperience;
        this.generation = generation;
        this.isLegendary = isLegendary;
    }

    public Pokemon(String name, double height, double weight, double male, double female, 
               int hp, int attack, int defense, int speed, EvolvesTo evolvesTo) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.gender = new Gender(male, female);
        this.stats = new Stats(hp, attack, defense, 0, 0, speed);
        this.evolvesTo = evolvesTo;
    }
    
    public ObjectProperty<Stats> statsProperty() {
        return new SimpleObjectProperty<>(stats);
    }

    public ObjectProperty<Abilities> abilitiesProperty() {
        return new SimpleObjectProperty<>(abilities);
    }

    public ObjectProperty<Gender> genderProperty() {
        return new SimpleObjectProperty<>(gender);
    }

    public StringProperty evolvesToProperty() {
        return new SimpleStringProperty(evolvesTo != null ? evolvesTo.getName() : "None");
    }

    public Pokemon(String name, String type, double height, double weight, Stats stats, boolean isLegendary,
            String description, String category, String secondaryType, Integer generation, Integer baseExperience,
            Integer captureRate) {
        this.name = name;
        this.type = type;
        this.height = height;
        this.weight = weight;
        this.stats = stats;
        this.isLegendary = isLegendary;
        this.secondaryType = secondaryType;
        this.category = category;
        this.description = description;
        this.gender = new Gender(50.0, 50.0);
        this.captureRate = captureRate;
        this.baseExperience = baseExperience;
        this.generation = generation;        
    }
}
