package com.pokedex.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Gender {
    @XmlElement(name = "Male")
    private double malePercentage;

    @XmlElement(name = "Female")
    private double femalePercentage;

    // Getteri i setteri

    public double getMalePercentage() {
        return malePercentage;
    }

    public void setMalePercentage(double malePercentage) {
        this.malePercentage = malePercentage;
    }

    public double getFemalePercentage() {
        return femalePercentage;
    }

    public void setFemalePercentage(double femalePercentage) {
        this.femalePercentage = femalePercentage;
    }
    
    public Gender(double malePercentage, double femalePercentage) {
        this.malePercentage = malePercentage;
        this.femalePercentage = femalePercentage;
    }
    
    public Gender() {}
    
    public DoubleProperty malePercentageProperty() {
        return new SimpleDoubleProperty(malePercentage);
    }

    public DoubleProperty femalePercentageProperty() {
        return new SimpleDoubleProperty(femalePercentage);
    }
}
