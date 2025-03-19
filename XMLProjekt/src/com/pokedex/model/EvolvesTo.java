package com.pokedex.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class EvolvesTo {
    @XmlAttribute(name = "id")
    private String id;

    @XmlValue
    private String name;

    // Getteri i setteri

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

    public EvolvesTo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public EvolvesTo() {
    }
}
