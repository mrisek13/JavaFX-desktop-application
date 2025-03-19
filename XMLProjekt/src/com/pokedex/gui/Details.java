
package com.pokedex.gui;

import com.pokedex.model.EvolvesTo;
import com.pokedex.model.Pokemon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Details extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Učitavanje XML-a
        Pokemon pokemon = loadPokemonFromXML("src/resources/pokedex.xml");

        // Kreiranje glavnog layouta
        VBox root = new VBox(10);
        
        // Labela s nazivom Pokémona
        Label nameLabel = new Label("Pokémon: " + pokemon.getName());
        nameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Pie Chart (Spol)
        PieChart genderChart = createGenderChart(pokemon);

        // Bar Chart (Visina i težina)
        BarChart<String, Number> barChart = createSizeChart(pokemon);

        // Radar Chart (Statistike)
        LineChart<String, Number> radarChart = createRadarChart(pokemon);

        // Evolucijski prikaz
        Label evolutionLabel = new Label("Evolves To: " + pokemon.getEvolvesTo().getName());

        // Dodavanje u layout
        root.getChildren().addAll(nameLabel, genderChart, barChart, radarChart, evolutionLabel);
        
        // Prikaz scene
        Scene scene = new Scene(root, 600, 700);
        primaryStage.setTitle("Pokémon Details");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Učitavanje podataka iz XML-a
    private Pokemon loadPokemonFromXML(String filename) {
        try {
            File file = new File(filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element pokemonElement = (Element) doc.getElementsByTagName("Pokemon").item(0);
            String name = pokemonElement.getElementsByTagName("Name").item(0).getTextContent();
            double height = Double.parseDouble(pokemonElement.getElementsByTagName("Height").item(0).getTextContent());
            double weight = Double.parseDouble(pokemonElement.getElementsByTagName("Weight").item(0).getTextContent());

            Element genderElement = (Element) pokemonElement.getElementsByTagName("Gender").item(0);
            double male = Double.parseDouble(genderElement.getElementsByTagName("Male").item(0).getTextContent());
            double female = Double.parseDouble(genderElement.getElementsByTagName("Female").item(0).getTextContent());

            Element statsElement = (Element) pokemonElement.getElementsByTagName("Stats").item(0);
            int hp = Integer.parseInt(statsElement.getElementsByTagName("HP").item(0).getTextContent());
            int attack = Integer.parseInt(statsElement.getElementsByTagName("Attack").item(0).getTextContent());
            int defense = Integer.parseInt(statsElement.getElementsByTagName("Defense").item(0).getTextContent());
            int speed = Integer.parseInt(statsElement.getElementsByTagName("Speed").item(0).getTextContent());

            String evolvesTo = pokemonElement.getElementsByTagName("EvolvesTo").item(0).getTextContent();
            EvolvesTo evo = new EvolvesTo("001", evolvesTo);

            return new Pokemon(name, height, weight, male, female, hp, attack, defense, speed, evo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Pie Chart (Spol)
    private PieChart createGenderChart(Pokemon pokemon) {
        PieChart chart = new PieChart();
        chart.getData().add(new PieChart.Data("Male", pokemon.getGender().getMalePercentage()));
        chart.getData().add(new PieChart.Data("Female", pokemon.getGender().getFemalePercentage()));
        chart.setTitle("Gender Distribution");
        return chart;
    }

    // Bar Chart (Visina i težina)
    private BarChart<String, Number> createSizeChart(Pokemon pokemon) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> heightSeries = new XYChart.Series<>();
        heightSeries.setName("Height");
        heightSeries.getData().add(new XYChart.Data<>("Bulbasaur", pokemon.getHeight()));

        XYChart.Series<String, Number> weightSeries = new XYChart.Series<>();
        weightSeries.setName("Weight");
        weightSeries.getData().add(new XYChart.Data<>("Bulbasaur", pokemon.getWeight()));

        chart.getData().addAll(heightSeries, weightSeries);
        chart.setTitle("Size Comparison");
        return chart;
    }

    // Line Chart (Statistike)
    private LineChart<String, Number> createRadarChart(Pokemon pokemon) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> statsSeries = new XYChart.Series<>();
        statsSeries.getData().add(new XYChart.Data<>("HP", pokemon.getStats().getHp()));
        statsSeries.getData().add(new XYChart.Data<>("Attack", pokemon.getStats().getAttack()));
        statsSeries.getData().add(new XYChart.Data<>("Defense", pokemon.getStats().getDefense()));
        statsSeries.getData().add(new XYChart.Data<>("Speed", pokemon.getStats().getSpeed()));

        chart.getData().add(statsSeries);
        chart.setTitle("Stats Overview");
        return chart;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
