package com.pokedex.gui;

import com.pokedex.model.Pokemon;
import com.pokedex.model.Stats;
import com.pokedex.parser.PokedexParser;
import com.pokedex.parser.XMLValidator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import javafx.scene.chart.PieChart;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PokedexGUI extends Application {

    private TableView<Pokemon> tableView;
    private ObservableList<Pokemon> pokemonList;
    private BarChart<String, Number> statsChart;
    private PieChart pieChart;

    @Override
    public void start(Stage primaryStage) {
        // Parsiranje XML-a
        List<Pokemon> pokemons = PokedexParser.parsePokedex("src/resources/pokedex.xml");
        pokemonList = FXCollections.observableArrayList(pokemons);

        // Glavni layout
        BorderPane root = new BorderPane();

        // Gornji dio (header + search)
        VBox topBox = new VBox(10);
        Label header = new Label("Pokedex");
        header.setStyle("-fx-font-size: 24px; -fx-padding: 10px;");

        TextField searchField = new TextField();
        searchField.setPromptText("Search Pokémon...");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterPokemonList(newValue));

        topBox.getChildren().addAll(header, searchField);
        root.setTop(topBox);

        // Lijevi dio (popis Pokémona)
        tableView = new TableView<>();
        setupTableView();
        tableView.setItems(pokemonList);
        root.setCenter(tableView);

        // Desni dio (detalji i grafovi)
        VBox detailsBox = new VBox(10);
        detailsBox.setStyle("-fx-padding: 10px;");
        detailsBox.setPrefWidth(500);

        Label nameLabel = new Label("Name: ");
        Label typeLabel = new Label("Type: ");
        Label categoryLabel = new Label("Category: ");
        Label heightLabel = new Label("Height: ");
        Label weightLabel = new Label("Weight: ");
        Label legendaryLabel = new Label("Legendary: ");

        // Postavljanje bold stila za labele
        nameLabel.setStyle("-fx-font-weight: bold;");
        typeLabel.setStyle("-fx-font-weight: bold;");
        categoryLabel.setStyle("-fx-font-weight: bold;");
        heightLabel.setStyle("-fx-font-weight: bold;");
        weightLabel.setStyle("-fx-font-weight: bold;");
        legendaryLabel.setStyle("-fx-font-weight: bold;");
        
        Label nameValue = new Label();
        Label typeValue = new Label();
        Label categoryValue = new Label();
        Label heightValue = new Label();
        Label weightValue = new Label();
        Label legendaryValue = new Label();

        HBox nameBox = new HBox(nameLabel, nameValue);
        HBox typeBox = new HBox(typeLabel, typeValue);
        HBox categoryBox = new HBox(categoryLabel, categoryValue);
        HBox heightBox = new HBox(heightLabel, heightValue);
        HBox weightBox = new HBox(weightLabel, weightValue);
        HBox legendaryBox = new HBox(legendaryLabel, legendaryValue);

        initializeCharts();
        detailsBox.getChildren().addAll(
            nameBox,
            typeBox,
            categoryBox,
            heightBox,
            weightBox,
            legendaryBox,
            statsChart,
            pieChart
        );
        root.setRight(detailsBox);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameValue.setText(newValue.getName());
                typeValue.setText(newValue.getType());
                categoryValue.setText(newValue.getCategory());
                heightValue.setText(String.format("%.2f m", newValue.getHeight()));
                weightValue.setText(String.format("%.2f kg", newValue.getWeight()));
                legendaryValue.setText(newValue.isIsLegendary() ? "Yes" : "No");
                updateStatsChart(newValue);
                updatePieChart(newValue);
            }
        });

        // Donji dio (akcije)
        HBox actionBox = new HBox(10);
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        Button validateButton = new Button("Validate XML");
        Button exportButton = new Button("Export to CSV");        
        actionBox.getChildren().addAll(addButton, editButton, deleteButton, validateButton, exportButton);
        actionBox.setStyle("-fx-padding: 10px;");
        root.setBottom(actionBox);

        // Eventi za tipke
        addButton.setOnAction(event -> addPokemon());
        editButton.setOnAction(event -> editPokemon());
        deleteButton.setOnAction(event -> deletePokemon());
        exportButton.setOnAction(event -> exportToCSV());
        validateButton.setOnAction(event -> validateXML());

        // Postavljanje Scene i prikaz prozora
        Scene scene = new Scene(root, 1500, 800); // Povećana širina prozora
        primaryStage.setTitle("Pokedex");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
    private void setupTableView() {
        TableColumn<Pokemon, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Pokemon, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        TableColumn<Pokemon, String> secondaryTypeColumn = new TableColumn<>("Secondary Type");
        secondaryTypeColumn.setCellValueFactory(cellData -> cellData.getValue().secondaryTypeProperty());

        TableColumn<Pokemon, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());

        TableColumn<Pokemon, Number> heightColumn = new TableColumn<>("Height (m)");
        heightColumn.setCellValueFactory(cellData -> cellData.getValue().heightProperty());

        TableColumn<Pokemon, Number> weightColumn = new TableColumn<>("Weight (kg)");
        weightColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty());

        TableColumn<Pokemon, Boolean> legendaryColumn = new TableColumn<>("Legendary");
        legendaryColumn.setCellValueFactory(cellData -> cellData.getValue().isLegendaryProperty());

        TableColumn<Pokemon, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        // Statistike
        TableColumn<Pokemon, Number> hpColumn = new TableColumn<>("HP");
        hpColumn.setCellValueFactory(cellData -> cellData.getValue().statsProperty().get().hpProperty());

        TableColumn<Pokemon, Number> attackColumn = new TableColumn<>("Attack");
        attackColumn.setCellValueFactory(cellData -> cellData.getValue().statsProperty().get().attackProperty());

        TableColumn<Pokemon, Number> defenseColumn = new TableColumn<>("Defense");
        defenseColumn.setCellValueFactory(cellData -> cellData.getValue().statsProperty().get().defenseProperty());

        TableColumn<Pokemon, Number> spAttackColumn = new TableColumn<>("Sp. Attack");
        spAttackColumn.setCellValueFactory(cellData -> cellData.getValue().statsProperty().get().specialAttackProperty());

        TableColumn<Pokemon, Number> spDefenseColumn = new TableColumn<>("Sp. Defense");
        spDefenseColumn.setCellValueFactory(cellData -> cellData.getValue().statsProperty().get().specialDefenseProperty());

        TableColumn<Pokemon, Number> speedColumn = new TableColumn<>("Speed");
        speedColumn.setCellValueFactory(cellData -> cellData.getValue().statsProperty().get().speedProperty());

        TableColumn<Pokemon, Number> totalStatsColumn = new TableColumn<>("Total");
        totalStatsColumn.setCellValueFactory(cellData -> cellData.getValue().statsProperty().get().totalProperty());

        // Gender Ratio
        TableColumn<Pokemon, Number> maleRatioColumn = new TableColumn<>("Male %");
        maleRatioColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty().get().malePercentageProperty());

        TableColumn<Pokemon, Number> femaleRatioColumn = new TableColumn<>("Female %");
        femaleRatioColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty().get().femalePercentageProperty());

        // Evolution
        TableColumn<Pokemon, String> evolvesToColumn = new TableColumn<>("Evolves To");
        evolvesToColumn.setCellValueFactory(cellData -> cellData.getValue().evolvesToProperty());

        // Capture Rate
        TableColumn<Pokemon, Number> captureRateColumn = new TableColumn<>("Capture Rate");
        captureRateColumn.setCellValueFactory(cellData -> cellData.getValue().captureRateProperty());

        // Base Experience
        TableColumn<Pokemon, Number> baseExpColumn = new TableColumn<>("Base Exp");
        baseExpColumn.setCellValueFactory(cellData -> cellData.getValue().baseExperienceProperty());

        // Generation
        TableColumn<Pokemon, Number> generationColumn = new TableColumn<>("Generation  ");
        generationColumn.setCellValueFactory(cellData -> cellData.getValue().generationProperty());

        // Dodaj sve kolone u TableView
        tableView.getColumns().addAll(
            nameColumn, typeColumn, secondaryTypeColumn, categoryColumn, 
            heightColumn, weightColumn, legendaryColumn, descriptionColumn,
            hpColumn, attackColumn, defenseColumn, spAttackColumn, spDefenseColumn, speedColumn, totalStatsColumn,
            maleRatioColumn, femaleRatioColumn,
            evolvesToColumn, captureRateColumn, baseExpColumn, generationColumn
        );
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void addPokemon() {
        showPokemonForm(null, false);
    }

    private void editPokemon() {
        // Uređivanje selektiranog Pokémona
        Pokemon selectedPokemon = tableView.getSelectionModel().getSelectedItem();
        if (selectedPokemon != null) {
            showPokemonForm(selectedPokemon, true);
        } else {
            showAlert("No Pokémon Selected", "Please select a Pokémon to edit.");
        }
    }

    private void deletePokemon() {
        // Brisanje selektiranog Pokémona
        Pokemon selectedPokemon = tableView.getSelectionModel().getSelectedItem();
        if (selectedPokemon != null) {
            pokemonList.remove(selectedPokemon);
            PokedexParser.savePokedex("src/resources/pokedex.xml", pokemonList);
        } else {
            showAlert("No Pokémon Selected", "Please select a Pokémon to delete.");
        }
    }

    private void showPokemonForm(Pokemon pokemon, boolean isEditMode) {
        Stage formStage = new Stage();
        formStage.setTitle(isEditMode ? "Edit Pokémon" : "Add Pokémon");

        GridPane formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.setStyle("-fx-padding: 20px;");

        // Labele
        Label nameLabel = new Label("Name:");
        Label typeLabel = new Label("Type:");
        Label secondaryTypeLabel = new Label("Secondary Type:");
        Label categoryLabel = new Label("Category:");
        Label descriptionLabel = new Label("Description:");
        Label heightLabel = new Label("Height (m):");
        Label weightLabel = new Label("Weight (kg):");
        Label hpLabel = new Label("HP:");
        Label attackLabel = new Label("Attack:");
        Label defenseLabel = new Label("Defense:");
        Label specialAttackLabel = new Label("Special Attack:");
        Label specialDefenseLabel = new Label("Special Defense:");
        Label speedLabel = new Label("Speed:");
        Label maleLabel = new Label("Male %:");
        Label femaleLabel = new Label("Female %:");
        Label evolvesToLabel = new Label("Evolves To (ID):");
        Label captureRateLabel = new Label("Capture Rate:");
        Label baseExperienceLabel = new Label("Base Experience:");
        Label generationLabel = new Label("Generation:");

        nameLabel.setMinWidth(120);
        typeLabel.setMinWidth(120);
        secondaryTypeLabel.setMinWidth(120);
        categoryLabel.setMinWidth(120);
        descriptionLabel.setMinWidth(120);
        heightLabel.setMinWidth(120);
        weightLabel.setMinWidth(120);
        hpLabel.setMinWidth(120);
        attackLabel.setMinWidth(120);
        defenseLabel.setMinWidth(120);
        specialAttackLabel.setMinWidth(120);
        specialDefenseLabel.setMinWidth(120);
        speedLabel.setMinWidth(120);
        maleLabel.setMinWidth(120);
        femaleLabel.setMinWidth(120);
        evolvesToLabel.setMinWidth(120);
        captureRateLabel.setMinWidth(120);
        baseExperienceLabel.setMinWidth(120);
        generationLabel.setMinWidth(120);
        
        // Polja za unos
        TextField nameField = new TextField(pokemon != null ? pokemon.getName() : "");
        TextField typeField = new TextField(pokemon != null ? pokemon.getType() : "");
        TextField secondaryTypeField = new TextField(pokemon != null ? pokemon.getSecondaryType() : "");
        TextField categoryField = new TextField(pokemon != null ? pokemon.getCategory() : "");
        TextArea descriptionArea = new TextArea(pokemon != null ? pokemon.getDescription() : "");
        descriptionArea.setPrefRowCount(3);
        TextField heightField = new TextField(pokemon != null ? String.valueOf(pokemon.getHeight()) : "");
        TextField weightField = new TextField(pokemon != null ? String.valueOf(pokemon.getWeight()) : "");

        // Statistike
        TextField hpField = new TextField(pokemon != null ? String.valueOf(pokemon.getStats().hpProperty().get()) : "");
        TextField attackField = new TextField(pokemon != null ? String.valueOf(pokemon.getStats().getAttack()) : "");
        TextField defenseField = new TextField(pokemon != null ? String.valueOf(pokemon.getStats().getDefense()) : "");
        TextField specialAttackField = new TextField(pokemon != null ? String.valueOf(pokemon.getStats().getSpecialAttack()) : "");
        TextField specialDefenseField = new TextField(pokemon != null ? String.valueOf(pokemon.getStats().getSpecialDefense()) : "");
        TextField speedField = new TextField(pokemon != null ? String.valueOf(pokemon.getStats().getSpeed()) : "");

        // Spol
        TextField maleField = new TextField(pokemon != null ? String.valueOf(pokemon.getGender().getMalePercentage()) : "");
        TextField femaleField = new TextField(pokemon != null ? String.valueOf(pokemon.getGender().getFemalePercentage()) : "");

        // Evolucija
        TextField evolvesToField = new TextField(pokemon != null ? pokemon.getEvolvesTo().getName() : "");

        // Ostali podaci
        TextField captureRateField = new TextField(pokemon != null ? String.valueOf(pokemon.getCaptureRate()) : "");
        TextField baseExperienceField = new TextField(pokemon != null ? String.valueOf(pokemon.getBaseExperience()) : "");
        TextField generationField = new TextField(pokemon != null ? String.valueOf(pokemon.getGeneration()) : "");

        CheckBox legendaryCheckBox = new CheckBox("Legendary");
        legendaryCheckBox.setSelected(pokemon != null && pokemon.isIsLegendary());

        // Postavljanje layouta
        int row = 0;
        formLayout.addRow(row++, nameLabel, nameField);
        formLayout.addRow(row++, typeLabel, typeField);
        formLayout.addRow(row++, secondaryTypeLabel, secondaryTypeField);
        formLayout.addRow(row++, categoryLabel, categoryField);
        formLayout.addRow(row++, descriptionLabel, descriptionArea);
        formLayout.addRow(row++, heightLabel, heightField);
        formLayout.addRow(row++, weightLabel, weightField);
        formLayout.addRow(row++, hpLabel, hpField);
        formLayout.addRow(row++, attackLabel, attackField);
        formLayout.addRow(row++, defenseLabel, defenseField);
        formLayout.addRow(row++, specialAttackLabel, specialAttackField);
        formLayout.addRow(row++, specialDefenseLabel, specialDefenseField);
        formLayout.addRow(row++, speedLabel, speedField);
        formLayout.addRow(row++, maleLabel, maleField);
        formLayout.addRow(row++, femaleLabel, femaleField);
        formLayout.addRow(row++, evolvesToLabel, evolvesToField);
        formLayout.addRow(row++, captureRateLabel, captureRateField);
        formLayout.addRow(row++, baseExperienceLabel, baseExperienceField);
        formLayout.addRow(row++, generationLabel, generationField);
        formLayout.addRow(row++, legendaryCheckBox);

        // Gumbi za spremanje i otkazivanje
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        formLayout.add(buttonBox, 1, row++);

        saveButton.setOnAction(event -> {
            try {
                String name = nameField.getText().trim();
                String type = typeField.getText().trim();
                String category = categoryField.getText().trim();
                String description = descriptionArea.getText().trim();
                String secondaryType = secondaryTypeField.getText().trim();
                int generation = Integer.valueOf(generationField.getText().trim());
                int baseExperience = Integer.valueOf(baseExperienceField.getText().trim());
                int captureRate = Integer.valueOf(captureRateField.getText().trim());
                double height = Double.parseDouble(heightField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());
                int hp = Integer.parseInt(hpField.getText().trim());
                int attack = Integer.parseInt(attackField.getText().trim());
                int defense = Integer.parseInt(defenseField.getText().trim());
                int specialAttack = Integer.parseInt(specialAttackField.getText().trim());
                int specialDefense = Integer.parseInt(specialDefenseField.getText().trim());
                int speed = Integer.parseInt(speedField.getText().trim());
                boolean isLegendary = legendaryCheckBox.isSelected();
                
                if (name.isEmpty() || type.isEmpty() || category.isEmpty()) {
                    showAlert("Validation Error", "Name, Type, and Category cannot be empty.");
                    return;
                }

                // Dodavanje u listu i spremanje
                if (isEditMode && pokemon != null) {
                    pokemon.setName(name);
                    pokemon.setType(type);
                    pokemon.setHeight(height);
                    pokemon.setWeight(weight);                   
                    pokemon.getStats().setHp(hp);
                    pokemon.getStats().setAttack(attack);
                    pokemon.getStats().setDefense(defense);
                    pokemon.getStats().setSpecialAttack(specialAttack);
                    pokemon.getStats().setSpecialDefense(specialDefense);
                    pokemon.getStats().setSpeed(speed);                    
                    pokemon.setIsLegendary(isLegendary);
                    pokemon.setDescription(description);
                    pokemon.setCategory(category);
                    pokemon.setSecondaryType(secondaryType);
                    pokemon.setGeneration(generation);
                    pokemon.setCaptureRate(captureRate);
                    
                    tableView.refresh();
                } else {
                    pokemonList.add(new Pokemon(name, type, height, weight, new Stats(hp, attack, defense, specialAttack, specialDefense, speed), isLegendary, description, category, secondaryType, generation, baseExperience, captureRate));
                }

                PokedexParser.savePokedex("src/resources/pokedex.xml", pokemonList);
                formStage.close();
            } catch (NumberFormatException e) {
                showAlert("Validation Error", "Numerička polja moraju biti ispravna.");
            }
        });

        cancelButton.setOnAction(event -> formStage.close());
        formStage.setScene(new Scene(formLayout, 800, 900));
        formStage.show();
    }
                   
    private void updateStatsChart(Pokemon pokemon) {
        statsChart.getData().clear();

        XYChart.Series<String, Number> hpSeries = new XYChart.Series<>();
        hpSeries.setName("HP");
        hpSeries.getData().add(new XYChart.Data<>("HP", pokemon.getStats().getHp()));

        XYChart.Series<String, Number> attackSeries = new XYChart.Series<>();
        attackSeries.setName("Attack");
        attackSeries.getData().add(new XYChart.Data<>("Attack", pokemon.getStats().getAttack()));

        XYChart.Series<String, Number> defenseSeries = new XYChart.Series<>();
        defenseSeries.setName("Defense");
        defenseSeries.getData().add(new XYChart.Data<>("Defense", pokemon.getStats().getDefense()));

        XYChart.Series<String, Number> speedSeries = new XYChart.Series<>();
        speedSeries.setName("Speed");
        speedSeries.getData().add(new XYChart.Data<>("Speed", pokemon.getStats().getSpeed()));

        statsChart.getData().addAll(hpSeries, attackSeries, defenseSeries, speedSeries);
    }
        
    private void updatePieChart(Pokemon pokemon) {
        pieChart.getData().clear();
        pieChart.getData().add(new PieChart.Data("Male", pokemon.getGender().getMalePercentage()));
        pieChart.getData().add(new PieChart.Data("Female", pokemon.getGender().getFemalePercentage()));
    }
    
    private void initializeCharts() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        statsChart = new BarChart<>(xAxis, yAxis);
        statsChart.setTitle("Pokemon Stats");
        xAxis.setLabel("Stats");
        yAxis.setLabel("Value");

        pieChart = new PieChart();
        pieChart.setTitle("Gender Distribution");
    }
    
    private void filterPokemonList(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            tableView.setItems(pokemonList);
        } else {
            ObservableList<Pokemon> filteredList = FXCollections.observableArrayList();
            for (Pokemon p : pokemonList) {
                if (p.getName().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(p);
                }
            }
            tableView.setItems(filteredList);
        }
    }
    
    private void exportToCSV() {
        File file = new File("src/resources/pokedex.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Name,Type,Secondary Type,Category,Height,Weight,Legendary\n");
            for (Pokemon p : pokemonList) {
                writer.write(p.getName() + "," +
                             p.getType() + "," +
                             (p.getSecondaryType() != null ? p.getSecondaryType() : "") + "," +
                             p.getCategory() + "," +
                             p.getHeight() + "," +
                             p.getWeight() + "," +
                             (p.isIsLegendary() ? "Yes" : "No") + "\n");
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Export successful! Saved to src/resources/pokedex.csv", ButtonType.OK);
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Export failed: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
    
    public void validateXML() {
        String xmlPath = "src/resources/pokedex.xml";
        String xsdPath = "src/resources/pokedex.xsd";

        String validationMessage = XMLValidator.validateXML(xmlPath, xsdPath);

        Alert alert;
        if (validationMessage.equals("XML is valid!")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
        }

        alert.setHeaderText(null);
        alert.setContentText(validationMessage);

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}