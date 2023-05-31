package org.pokedex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.pokemon.Pokemon;
import org.pokemon.PokemonController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class pokedexScreenController {

    @FXML
    private ImageView backgroundImageView;

    @FXML
    private Button rightButton;

    @FXML
    private Button leftButton;
    @FXML
    private Button shinyButtons;

    @FXML
    private Button startButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label typeOneLabel;
    @FXML
    private Label typeTwoLabel;
    @FXML
    private Pane paneBackground;
    @FXML
    private ImageView photoFrame;
    @FXML
    private ImageView typeIconOne;
    @FXML
    private ImageView typeIconTwo;
    @FXML
    private Label waeknessesLabel;
    @FXML
    private Label weaknessesLabelOne;

    @FXML
    private Label weaknessesLabelTwo;

    @FXML
    private Label weaknessesLabelThree;

    @FXML
    private Label weaknessesLabelFour;

    @FXML
    private Label weaknessesLabelFive;

    @FXML
    private Label weaknessesLabelSix;
    @FXML
    private ImageView weaknessesIconOne;
    @FXML
    private ImageView weaknessesIconTwo;

    @FXML
    private ImageView weaknessesIconThree;

    @FXML
    private ImageView weaknessesIconFour;

    @FXML
    private ImageView weaknessesIconFive;

    @FXML
    private ImageView weaknessesIconSix;

    @FXML
    private Label regionLabel;

    @FXML
    private Label generationLabel;

    @FXML
    private Button getMegaListButton;

    @FXML
    private Button getMegaListButton2;

    @FXML
    private Button downMega;

    @FXML
    private Button upMega;

    @FXML
    private ImageView regionImage;

    @FXML
    private Label heightText;

    @FXML
    private Label weightText;

    @FXML
    private Label categoryText;

    @FXML
    private Label genderText;

    private List<Pokemon> pokemons;

    @FXML
    private Label heightLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label weightLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private ImageView genderIcon;

    @FXML
    private ImageView megaSymbol;

    @FXML
    private Button myButton;

    @FXML
    private TextField searchText;

    @FXML
    private Button search;

    @FXML
    private ImageView alternativeIcon;

    @FXML
    private Button tutorialButton;

    private PokemonController controller = new PokemonController();
    private Pokemon i;
    private int index = 0;
    private int megaIndex = 0;
    private String[] typesPart;
    private int shinyButtonMemory = 0;
    private List<Pokemon> megaPokemons;
    private ArrayList<Pokemon> matchingObjects = new ArrayList<Pokemon>();

    public void start(ActionEvent event) throws IOException {
        setButtons();
        pokemons = controller.createPokedexData();
        megaPokemons = controller.createMegaPokemonData();
        onlyNumbers();
        i = pokemons.get(index);
        updateLabels();
        imageUpdate();
    }

    public void setSearch(ActionEvent event) {
        String numero = searchText.getText();
        if (!numero.equals("")) {
            int n = Integer.parseInt(numero);
            if (n - 1 > 0 && n - 1 < pokemons.size()) {
                index = n - 1;
                i = pokemons.get(index);
                updateLabels();
                imageUpdate();
                cleanMega();
                megaIndex = 0;
                searchText.setText(numero);
            } else {
                searchText.setText("0");
            }
        }
    }

    public void setButtons() {
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        startButton.setVisible(false);
        waeknessesLabel.setVisible(true);
        shinyButtons.setVisible(true);
        regionImage.setVisible(true);
        heightText.setVisible(true);
        weightText.setVisible(true);
        categoryText.setVisible(true);
        genderText.setVisible(true);
        startButton.setVisible(false);
        myButton.setVisible(true);
        search.setVisible(true);
        searchText.setVisible(true);
        getMegaListButton.setVisible(true);
        getMegaListButton2.setVisible(true);
    }

    public void rightButton() {
        if (index < pokemons.size() - 1) {
            i = pokemons.get(index+=1);
            updateLabels();
            imageUpdate();
            cleanMega();
            megaIndex = 0;
        }
    }

    public void leftButton() {
        if (index > 0) {
            i = pokemons.get(index-=1);
            updateLabels();
            imageUpdate();
            cleanMega();
            megaIndex = 0;
        }
    }

    public void imageUpdate() {
        String photo = i.getImage();
        Image image = new Image(photo);
        photoFrame.setImage(image);
    }

    public void updateLabels() {
        nameLabel.setText(i.getName());
        verifyNameSize(i.getName());
        numberLabel.setText("N°" + i.getNumber());
        regionLabel.setText(i.getRegion());
        generationLabel.setText(i.getGeneration() + "° GENERATION");
        typeUpdade();
        weaknessesUpadate();
        paneBackground.setStyle("-fx-background-color: "+ i.getBackGroundColor() +";");
        heightLabel.setText(i.getHeight());
        weightLabel.setText(i.getWeight());
        categoryLabel.setText(i.getCategory());
        genderUpdate();
        megaIconUpdate();
    }

    public void verifyNameSize(String name) {
        if (name.length() > 19) {
            nameLabel.setLayoutX(449);
            nameLabel.setLayoutY(189);
            numberLabel.setLayoutX(632);
            numberLabel.setLayoutY(214);
        }else {
            nameLabel.setLayoutX(467);
            nameLabel.setLayoutY(196);
            numberLabel.setLayoutX(617);
            numberLabel.setLayoutY(196);
        }
    }

    public void typeUpdade() {
        String splitInTwo = i.getType().toString();
        splitInTwo = splitInTwo.replace("[", "");
        splitInTwo = splitInTwo.replace("]", "");
        typesPart = splitInTwo.split(", ", 2);
        if (typesPart.length == 1) {
            typeOneLabel.setText(typesPart[0]);
            ColorandIcon(typeOneLabel, typesPart[0], typeIconOne);
            typeIconTwo.setImage(null);
            typeTwoLabel.setText("");
        } else {
            typeOneLabel.setText(typesPart[0]);
            ColorandIcon(typeOneLabel, typesPart[0], typeIconOne);
            typeTwoLabel.setText(typesPart[1]);
            ColorandIcon(typeTwoLabel, typesPart[1], typeIconTwo);
        }
    }

    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void weaknessesUpadate() {
        String splitWeaknesses = i.getWeaknesses().toString();
        splitWeaknesses = splitWeaknesses.replace("[", "");
        splitWeaknesses = splitWeaknesses.replace("]", "");
        String[] weaknessesPart = splitWeaknesses.split(", ");
        if (weaknessesPart.length == 1) {
            clean();
            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorandIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            //Reposicionando label
            weaknessesLabelOne.setLayoutX(547);
            // Icon recebe a posição da label ao qual pertence + 6, para corrigir uma diferença no
            // posicionamento de um Label e ImageView.
            weaknessesIconOne.setLayoutX(547+6);

        } else if (weaknessesPart.length == 2) {
            clean();
            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorandIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            //Reposicionando label and icon
            weaknessesLabelOne.setLayoutX(522);
            weaknessesIconOne.setLayoutX(522+6);
            //Reposicionando label and icon
            weaknessesLabelTwo.setText(weaknessesPart[1]);
            ColorandIcon(weaknessesLabelTwo, weaknessesPart[1], weaknessesIconTwo);
            weaknessesIconTwo.setLayoutX(572+6);
            weaknessesLabelTwo.setLayoutX(572);

        } else if (weaknessesPart.length == 3) {
            clean();
            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorandIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            //Reposicionando label and icon
            weaknessesLabelOne.setLayoutX(507);
            weaknessesIconOne.setLayoutX(507+6);
            //Reposicionando label and icon
            weaknessesLabelTwo.setText(weaknessesPart[1]);
            weaknessesLabelTwo.setLayoutX(547);
            ColorandIcon(weaknessesLabelTwo, weaknessesPart[1], weaknessesIconTwo);
            weaknessesIconTwo.setLayoutX(547+6);
            //Reposicionando label and icon
            weaknessesLabelThree.setText(weaknessesPart[2]);
            ColorandIcon(weaknessesLabelThree, weaknessesPart[2], weaknessesIconThree);
            weaknessesIconThree.setLayoutX(590+6);
            weaknessesLabelThree.setLayoutX(590);

        } else if (weaknessesPart.length == 4) {
            clean();
            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorandIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            //Reposicionando label and icon
            weaknessesLabelOne.setLayoutX(473);
            weaknessesIconOne.setLayoutX(473+6);
            //Reposicionando label and icon
            weaknessesLabelTwo.setText(weaknessesPart[1]);
            weaknessesLabelTwo.setLayoutX(522);
            ColorandIcon(weaknessesLabelTwo, weaknessesPart[1], weaknessesIconTwo);
            weaknessesIconTwo.setLayoutX(522+6);
            //Reposicionando label and icon
            weaknessesLabelThree.setText(weaknessesPart[2]);
            weaknessesLabelThree.setLayoutX(572);
            ColorandIcon(weaknessesLabelThree, weaknessesPart[2], weaknessesIconThree);
            weaknessesIconThree.setLayoutX(572+6);
            //Reposicionando label and icon
            weaknessesLabelFour.setText(weaknessesPart[3]);
            weaknessesLabelFour.setLayoutX(621);
            ColorandIcon(weaknessesLabelFour, weaknessesPart[3], weaknessesIconFour);
            weaknessesIconFour.setLayoutX(621+6);

        } else if (weaknessesPart.length == 5) {
            clean();
            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorandIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            //Reposicionando label and icon
            weaknessesLabelOne.setLayoutX(462);
            weaknessesIconOne.setLayoutX(462+6);
            //Reposicionando label and icon
            weaknessesLabelTwo.setText(weaknessesPart[1]);
            weaknessesLabelTwo.setLayoutX(505);
            ColorandIcon(weaknessesLabelTwo, weaknessesPart[1], weaknessesIconTwo);
            weaknessesIconTwo.setLayoutX(505+6);
            //Reposicionando label and icon
            weaknessesLabelThree.setText(weaknessesPart[2]);
            weaknessesLabelThree.setLayoutX(547);
            ColorandIcon(weaknessesLabelThree, weaknessesPart[2], weaknessesIconThree);
            weaknessesIconThree.setLayoutX(547+6);
            //Reposicionando label and icon
            weaknessesLabelFour.setText(weaknessesPart[3]);
            weaknessesLabelFour.setLayoutX(590);
            ColorandIcon(weaknessesLabelFour, weaknessesPart[3], weaknessesIconFour);
            weaknessesIconFour.setLayoutX(590+6);
            //Reposicionando label and icon
            weaknessesLabelFive.setText(weaknessesPart[4]);
            weaknessesLabelFive.setLayoutX(633);
            ColorandIcon(weaknessesLabelFive, weaknessesPart[4], weaknessesIconFive);
            weaknessesIconFive.setLayoutX(633+6);

        } else if (weaknessesPart.length == 6) {
            clean();
            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorandIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            //Reposicionando label and icon
            weaknessesLabelOne.setLayoutX(447);
            weaknessesIconOne.setLayoutX(447+6);
            //Reposicionando label and icon
            weaknessesLabelTwo.setText(weaknessesPart[1]);
            weaknessesLabelTwo.setLayoutX(486);
            ColorandIcon(weaknessesLabelTwo, weaknessesPart[1], weaknessesIconTwo);
            weaknessesIconTwo.setLayoutX(486+6);
            //Reposicionando label and icon
            weaknessesLabelThree.setText(weaknessesPart[2]);
            weaknessesLabelThree.setLayoutX(526);
            ColorandIcon(weaknessesLabelThree, weaknessesPart[2], weaknessesIconThree);
            weaknessesIconThree.setLayoutX(526+6);
            //Reposicionando label and icon
            weaknessesLabelFour.setText(weaknessesPart[3]);
            weaknessesLabelFour.setLayoutX(567);
            ColorandIcon(weaknessesLabelFour, weaknessesPart[3], weaknessesIconFour);
            weaknessesIconFour.setLayoutX(567+6);
            //Reposicionando label and icon
            weaknessesLabelFive.setText(weaknessesPart[4]);
            weaknessesLabelFive.setLayoutX(608);
            ColorandIcon(weaknessesLabelFive, weaknessesPart[4], weaknessesIconFive);
            weaknessesIconFive.setLayoutX(608+6);
            //Reposicionando label and icon
            weaknessesLabelSix.setText(weaknessesPart[5]);
            weaknessesLabelSix.setLayoutX(647);
            ColorandIcon(weaknessesLabelSix, weaknessesPart[5], weaknessesIconSix);
            weaknessesIconSix.setLayoutX(647+6);
        }
    }
    public void ColorandIcon(Label label, String color, ImageView image) {
        switch (color) {
            case "Fire":
                label.setStyle("-fx-text-fill: #ff9c54;");
                image.setImage(new Image("/typeIcon/Fire_icon.png"));
                break;
            case "Grass":
                label.setStyle("-fx-text-fill: #63bb5b;");
                image.setImage(new Image("/typeIcon/Grass_icon.png"));
                break;
            case "Poison":
                label.setStyle("-fx-text-fill: #ab6ac8;");
                image.setImage(new Image("/typeIcon/Poison_icon.png"));
                break;
            case "Flying":
                label.setStyle("-fx-text-fill: #8fa8dd;");
                image.setImage(new Image("/typeIcon/Flying_icon.png"));
                break;
            case "Bug":
                label.setStyle("-fx-text-fill: #90c12c;");
                image.setImage(new Image("/typeIcon/Bug_icon.png"));
                break;
            case "Dark":
                label.setStyle("-fx-text-fill: #5a5366;");
                image.setImage(new Image("/typeIcon/Dark_icon.png"));
                break;
            case "Dragon":
                label.setStyle("-fx-text-fill: #0a6dc4;");
                image.setImage(new Image("/typeIcon/Dragon_icon.png"));
                break;
            case "Electric":
                label.setStyle("-fx-text-fill: #f3d23b;");
                image.setImage(new Image("/typeIcon/Electric_icon.png"));
                break;
            case "Ghost":
                label.setStyle("-fx-text-fill: #5269ac;");
                image.setImage(new Image("/typeIcon/Ghost_icon.png"));
                break;
            case "Ground":
                label.setStyle("-fx-text-fill: #d97746;");
                image.setImage(new Image("/typeIcon/Ground_icon.png"));
                break;
            case "Ice":
                label.setStyle("-fx-text-fill: #74cec0;");
                image.setImage(new Image("/typeIcon/Ice_icon.png"));
                break;
            case "Normal":
                label.setStyle("-fx-text-fill: #9099a1;");
                image.setImage(new Image("/typeIcon/Normal_icon.png"));
                break;
            case "Psychic":
                label.setStyle("-fx-text-fill: #f97176;");
                image.setImage(new Image("/typeIcon/Psychic_icon.png"));
                break;
            case "Rock":
                label.setStyle("-fx-text-fill: #c7b78b;");
                image.setImage(new Image("/typeIcon/Rock_icon.png"));
                break;
            case "Steel":
                label.setStyle("-fx-text-fill: #5a8ea1;");
                image.setImage(new Image("/typeIcon/Steel_icon.png"));
                break;
            case "Water":
                label.setStyle("-fx-text-fill: #4d90d5;");
                image.setImage(new Image("/typeIcon/Water_icon.png"));
                break;
            case "Fighting":
                label.setStyle("-fx-text-fill: #ce4069;");
                image.setImage(new Image("/typeIcon/Fighting_icon.png"));
                break;
            case "Fairy":
                label.setStyle("-fx-text-fill: #ec8fe6;");
                image.setImage(new Image("/typeIcon/Fairy_icon.png"));
                break;
        }
    }

    public void clean() {
        weaknessesLabelOne.setText("");
        weaknessesLabelTwo.setText("");
        weaknessesLabelThree.setText("");
        weaknessesLabelFour.setText("");
        weaknessesLabelFive.setText("");
        weaknessesLabelSix.setText("");
        weaknessesIconOne.setImage(null);
        weaknessesIconTwo.setImage(null);
        weaknessesIconThree.setImage(null);
        weaknessesIconFour.setImage(null);
        weaknessesIconFive.setImage(null);
        weaknessesIconSix.setImage(null);
    }

    public void shinyUpdate(ActionEvent event) {
        if (shinyButtonMemory == 0) {
            String photo = i.getShiny();
            Image image = new Image(photo);
            photoFrame.setImage(image);
            shinyButtonMemory++;
        } else {
            imageUpdate();
            shinyButtonMemory = 0;
        }
    }

    public void matchingIds() {
        for (Pokemon obj2 : megaPokemons) {
            if (i.getId() == obj2.getId()) {
                matchingObjects.add(obj2);
            }
        }
    }

    public void megaUp() {
        if (megaIndex < matchingObjects.size() - 1) {
            i = matchingObjects.get(megaIndex+=1);
            updateLabels();
            imageUpdate();
        }
    }

    public void megadown() {
        if (megaIndex > 0) {
            i = matchingObjects.get(megaIndex-=1);
            updateLabels();
            imageUpdate();
        }
    }

    public void getMegaList() {
        matchingIds();
        if (matchingObjects.size() != 0) {
            getMegaListButton.setVisible(false);
            getMegaListButton2.setVisible(false);
            upMega.setVisible(true);
            downMega.setVisible(true);
            i = matchingObjects.get(megaIndex);
            updateLabels();
            imageUpdate();
        }
    }

    public void cleanMega() {
        getMegaListButton.setVisible(true);
        getMegaListButton2.setVisible(true);
        upMega.setVisible(false);
        downMega.setVisible(false);
        matchingObjects.clear();
    }

    public void genderUpdate() {
        String gender = i.getGender();
        if (gender.equals("both")) {
            genderLabel.setText("");
            genderIcon.setImage(new Image("/UIassets/dual.png"));
        } else if (gender.equals("male")) {
            genderLabel.setText("");
            genderIcon.setImage(new Image("/UIassets/male.png"));
        } else if (gender.equals("female")) {
            genderLabel.setText("");
            genderIcon.setImage(new Image("/UIassets/female.png"));
        } else if (gender.equals("unknown")) {
            genderIcon.setImage(null);
            genderLabel.setText(i.getGender());
        }
    }

    public void megaIconUpdate() {
        if (i.isMega()) {
            megaSymbol.setImage(new Image("/UIassets/megaIcon.png"));
        } else {
            megaSymbol.setImage(null);
        }
        if (i.isAlternativeVersion()) {
            alternativeIcon.setImage(new Image("/UIassets/alternativeIcon.png"));
        } else {
            alternativeIcon.setImage(null);
        }
    }

    public void onlyNumbers() {
        String pattern = "\\d*";
        TextFormatter<Integer> textFormatter = new TextFormatter<>(
                new IntegerStringConverter(),
                0,
                c -> {
                    if (c.getControlNewText().matches(pattern)) {
                        return c;
                    } else {
                        return null;
                    }
                });
        searchText.setTextFormatter(textFormatter);
    }

    @FXML
    void setTutorialButton(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlFiles/tutorialScreen.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/UIassets/icon.png"));
        stage.setResizable(false);
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
