package com.pokedex.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Stats {
    @XmlElement(name = "HP")
    private int hp;

    @XmlElement(name = "Attack")
    private int attack;

    @XmlElement(name = "Defense")
    private int defense;

    @XmlElement(name = "SpecialAttack")
    private int specialAttack;

    @XmlElement(name = "SpecialDefense")
    private int specialDefense;

    @XmlElement(name = "Speed")
    private int speed;

    @XmlElement(name = "Total")
    private int total;

    // Getteri i setteri

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public Stats(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.total = hp + attack + defense + specialAttack + specialDefense + speed; // Automatski izračun ukupnog zbroja
    }
    
    public Stats() {}
    
    public IntegerProperty hpProperty() {
        return new SimpleIntegerProperty(hp);
    }

    public IntegerProperty attackProperty() {
        return new SimpleIntegerProperty(attack);
    }

    public IntegerProperty defenseProperty() {
        return new SimpleIntegerProperty(defense);
    }

    public IntegerProperty specialAttackProperty() {
        return new SimpleIntegerProperty(specialAttack);
    }

    public IntegerProperty specialDefenseProperty() {
        return new SimpleIntegerProperty(specialDefense);
    }

    public IntegerProperty speedProperty() {
        return new SimpleIntegerProperty(speed);
    }

    public IntegerProperty totalProperty() {
        return new SimpleIntegerProperty(total);
    }
    
}