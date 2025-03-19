package com.pokedex.parser;

import com.pokedex.model.PokedexWrapper;
import com.pokedex.model.Pokemon;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import javax.xml.bind.Unmarshaller;

public class PokedexParser {

    public static List<Pokemon> parsePokedex(String xmlFilePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(PokedexWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Parsiranje XML-a u Java objekte
            PokedexWrapper wrapper = (PokedexWrapper) unmarshaller.unmarshal(new File(xmlFilePath));
            return wrapper.getPokemonList();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void savePokedex(String filePath, List<Pokemon> pokemons) {
        try {
            JAXBContext context = JAXBContext.newInstance(PokedexWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PokedexWrapper wrapper = new PokedexWrapper();
            wrapper.setPokemonList(pokemons);

            marshaller.marshal(wrapper, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

