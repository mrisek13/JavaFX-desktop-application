package com.pokedex.model;

import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Pokedex")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pokedex {
    @XmlElement(name = "Pokemon")
    private List<Pokemon> pokemons;

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public Pokedex() {
    }
    
}