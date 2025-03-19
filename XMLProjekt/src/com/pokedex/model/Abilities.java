package com.pokedex.model;

import javax.xml.bind.annotation.*;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlAccessorType(XmlAccessType.FIELD)
public class Abilities {
    @XmlElement(name = "Ability")
    private List<String> abilities;

    @XmlElement(name = "HiddenAbility")
    private String hiddenAbility;

    Abilities(String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getteri i setteri

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public String getHiddenAbility() {
        return hiddenAbility;
    }

    public void setHiddenAbility(String hiddenAbility) {
        this.hiddenAbility = hiddenAbility;
    }

    public Abilities() {
    }
   
    public StringProperty regularAbilityProperty() {
        return new SimpleStringProperty(abilities.get(0));
    }

    public StringProperty hiddenAbilityProperty() {
        return new SimpleStringProperty(hiddenAbility);
    }
}
