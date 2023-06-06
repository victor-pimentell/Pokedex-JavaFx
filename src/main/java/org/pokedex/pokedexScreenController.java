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
    private Button rightButton;

    @FXML
    private Button leftButton;

    @FXML
    private Button shinyButton;

    @FXML
    private Button startButton;

    @FXML
    private Button getMegaListButton;

    @FXML
    private Button getMegaListButton2;

    @FXML
    private Button downMega;

    @FXML
    private Button upMega;

    @FXML
    private Button stopButton;

    @FXML
    private Button searchButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label typeOneLabel;

    @FXML
    private Label typeTwoLabel;

    @FXML
    private Label waeknessesText;

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
    private Label regionLabel;

    @FXML
    private Label generationLabel;

    @FXML
    private Label heightText;

    @FXML
    private Label weightText;

    @FXML
    private Label categoryText;

    @FXML
    private Label genderText;

    @FXML
    private Label heightLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label weightLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private ImageView photoFrame;

    @FXML
    private ImageView typeIconOne;

    @FXML
    private ImageView typeIconTwo;

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
    private ImageView regionImage;

    @FXML
    private ImageView genderIcon;

    @FXML
    private ImageView megaSymbol;

    @FXML
    private ImageView alternativeIcon;

    @FXML
    private ImageView startIcon;

    @FXML
    private ImageView stopIcon;

    @FXML
    private Pane paneBackground;

    @FXML
    private TextField searchTextField;

    private List<Pokemon> pokemons;

    private List<Pokemon> megaPokemons;

    private Pokemon i;

    private int index = 0;

    private int megaIndex = 0;

    private int shinyButtonMemory = 0;

    private ArrayList<Pokemon> matchingObjects = new ArrayList<Pokemon>();

    public void start(ActionEvent event) throws IOException {
        /*O método start inicia o progrma, utilizando de métodos em outra classe para extrair
        * dos arquivos JSON as informações dos Pokemons e as alocar em ArrayLists*/
        PokemonController controller = new PokemonController();
        pokemons = controller.createPokedexData();
        megaPokemons = controller.createMegaPokemonData();

        //Além disso, o método start utiliza os seguintes métodos para organizar a interface inicial.
        setButtons();
        onlyNumbers();

        i = pokemons.get(index);

        updateLabels();
        imageUpdate();
    }

    public void setSearch(ActionEvent event) {
        String numero = searchTextField.getText();
        if (!numero.equals("")) {
            int n = Integer.parseInt(numero);
            if (n - 1 > 0 && n - 1 < pokemons.size()) {
                index = n - 1;
                i = pokemons.get(index);
                updateLabels();
                imageUpdate();
                cleanMega();
                megaIndex = 0;
                searchTextField.setText(numero);
            } else {
                searchTextField.setText("0");
            }
        }
    }

    public void setButtons() {
        /* O método setButtons torna visivel os elementos da interface, como botões, labels e icones.
        * Além disso, ele torna o botão start invisível.*/
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        shinyButton.setVisible(true);

        regionImage.setVisible(true);

        heightText.setVisible(true);
        weightText.setVisible(true);
        categoryText.setVisible(true);
        genderText.setVisible(true);
        waeknessesText.setVisible(true);

        searchButton.setVisible(true);
        searchTextField.setVisible(true);

        // Esse dois botões tem o mesmo propósito, encontrar versões alternativas do pokemon mostrado.
        getMegaListButton.setVisible(true);
        getMegaListButton2.setVisible(true);

        stopButton.setVisible(true);
        startButton.setVisible(false);
        startIcon.setVisible(false);
        stopIcon.setVisible(true);
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
        //Esse método coloca as informações do pokemon em suas devidas Labels.
        nameLabel.setText(i.getName());
        numberLabel.setText("N°" + i.getNumber());
        regionLabel.setText(i.getRegion());
        generationLabel.setText(i.getGeneration() + "° GENERATION");
        paneBackground.setStyle("-fx-background-color: "+ i.getBackGroundColor() +";");
        heightLabel.setText(i.getHeight());
        weightLabel.setText(i.getWeight());
        categoryLabel.setText(i.getCategory());

        verifyNameSize(i.getName());
        typeUpdade();
        weaknessesUpadate();
        genderUpdate();
        megaIconUpdate();
    }

    public void verifyNameSize(String name) {
        /* Esse método verifica o tamanho do nome, caso ele seja muito grande a interface é ajustada,
        * para que as labels não fiquem uma em cima da outra.*/
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
        /* O método typeUpdate recebe uma string contendo o tipo do pokemon, separa o conteudo e o coloca
        * em um Array. Em seguida ele testa se o pokemon tem um ou dois tipos e insere o tipo em uma das
        * labels.*/
        String[] typesPart;
        String splitInTwo = i.getType().toString();
        splitInTwo = splitInTwo.replace("[", "");
        splitInTwo = splitInTwo.replace("]", "");
        typesPart = splitInTwo.split(", ", 2);
        if (typesPart.length == 1) {
            typeOneLabel.setText(typesPart[0]);
            ColorAndIcon(typeOneLabel, typesPart[0], typeIconOne);

            typeIconTwo.setImage(null);
            typeTwoLabel.setText("");
        } else {
            typeOneLabel.setText(typesPart[0]);
            ColorAndIcon(typeOneLabel, typesPart[0], typeIconOne);

            typeTwoLabel.setText(typesPart[1]);
            ColorAndIcon(typeTwoLabel, typesPart[1], typeIconTwo);
        }
    }

    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void weaknessesUpadate() {
        /* O método weaknessesUpadate recebe uma string contendo as fraquezas do pokemon, separa o
        * conteudo e o colocaem um Array. Em seguida ele faz um teste, para descobrir quantas fraquezas
        * o pokemon tem e as distribui nas labels de fraqueza.*/
        String splitWeaknesses = i.getWeaknesses().toString();
        splitWeaknesses = splitWeaknesses.replace("[", "");
        splitWeaknesses = splitWeaknesses.replace("]", "");
        String[] weaknessesPart = splitWeaknesses.split(", ");

        // O if a seguir checa a quantidade de fraquezas e posiciona a label de acordo com a quantidade.
        if (weaknessesPart.length == 1) {
            clean();

            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorAndIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            weaknessesLabelOne.setLayoutX(547);
            weaknessesIconOne.setLayoutX(547+6);

        } else if (weaknessesPart.length == 2) {
            clean();

            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorAndIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            weaknessesLabelOne.setLayoutX(522);
            weaknessesIconOne.setLayoutX(522+6);

            weaknessesLabelTwo.setText(weaknessesPart[1]);
            ColorAndIcon(weaknessesLabelTwo, weaknessesPart[1], weaknessesIconTwo);
            weaknessesLabelTwo.setLayoutX(572);
            weaknessesIconTwo.setLayoutX(572+6);

        } else if (weaknessesPart.length == 3) {
            clean();

            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorAndIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            weaknessesLabelOne.setLayoutX(507);
            weaknessesIconOne.setLayoutX(507+6);
            
            weaknessesLabelTwo.setText(weaknessesPart[1]);
            ColorAndIcon(weaknessesLabelTwo, weaknessesPart[1], weaknessesIconTwo);
            weaknessesLabelTwo.setLayoutX(547);
            weaknessesIconTwo.setLayoutX(547+6);
            
            weaknessesLabelThree.setText(weaknessesPart[2]);
            ColorAndIcon(weaknessesLabelThree, weaknessesPart[2], weaknessesIconThree);
            weaknessesLabelThree.setLayoutX(590);
            weaknessesIconThree.setLayoutX(590+6);

        } else if (weaknessesPart.length == 4) {
            clean();

            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorAndIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            weaknessesLabelOne.setLayoutX(473);
            weaknessesIconOne.setLayoutX(473+6);

            weaknessesLabelTwo.setText(weaknessesPart[1]);
            ColorAndIcon(weaknessesLabelTwo, weaknessesPart[1], weaknessesIconTwo);
            weaknessesLabelTwo.setLayoutX(522);
            weaknessesIconTwo.setLayoutX(522+6);
            
            weaknessesLabelThree.setText(weaknessesPart[2]);
            ColorAndIcon(weaknessesLabelThree, weaknessesPart[2], weaknessesIconThree);
            weaknessesLabelThree.setLayoutX(572);
            weaknessesIconThree.setLayoutX(572+6);
            
            weaknessesLabelFour.setText(weaknessesPart[3]);
            ColorAndIcon(weaknessesLabelFour, weaknessesPart[3], weaknessesIconFour);
            weaknessesLabelFour.setLayoutX(621);
            weaknessesIconFour.setLayoutX(621+6);

        } else if (weaknessesPart.length == 5) {
            clean();

            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorAndIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            weaknessesLabelOne.setLayoutX(462);
            weaknessesIconOne.setLayoutX(462+6);
            
            weaknessesLabelTwo.setText(weaknessesPart[1]);
            ColorAndIcon(weaknessesLabelTwo, weaknessesPart[1], weaknessesIconTwo);
            weaknessesLabelTwo.setLayoutX(505);
            weaknessesIconTwo.setLayoutX(505+6);
            
            weaknessesLabelThree.setText(weaknessesPart[2]);
            ColorAndIcon(weaknessesLabelThree, weaknessesPart[2], weaknessesIconThree);
            weaknessesLabelThree.setLayoutX(547);
            weaknessesIconThree.setLayoutX(547+6);
            
            weaknessesLabelFour.setText(weaknessesPart[3]);
            ColorAndIcon(weaknessesLabelFour, weaknessesPart[3], weaknessesIconFour);
            weaknessesLabelFour.setLayoutX(590);
            weaknessesIconFour.setLayoutX(590+6);
            
            weaknessesLabelFive.setText(weaknessesPart[4]);
            ColorAndIcon(weaknessesLabelFive, weaknessesPart[4], weaknessesIconFive);
            weaknessesLabelFive.setLayoutX(633);
            weaknessesIconFive.setLayoutX(633+6);

        } else if (weaknessesPart.length == 6) {
            clean();

            weaknessesLabelOne.setText(weaknessesPart[0]);
            ColorAndIcon(weaknessesLabelOne, weaknessesPart[0], weaknessesIconOne);
            weaknessesLabelOne.setLayoutX(447);
            weaknessesIconOne.setLayoutX(447+6);
            
            weaknessesLabelTwo.setText(weaknessesPart[1]);
            ColorAndIcon(weaknessesLabelTwo, weaknessesPart[1], weaknessesIconTwo);
            weaknessesLabelTwo.setLayoutX(486);
            weaknessesIconTwo.setLayoutX(486+6);
            
            weaknessesLabelThree.setText(weaknessesPart[2]);
            ColorAndIcon(weaknessesLabelThree, weaknessesPart[2], weaknessesIconThree);
            weaknessesLabelThree.setLayoutX(526);
            weaknessesIconThree.setLayoutX(526+6);
            
            weaknessesLabelFour.setText(weaknessesPart[3]);
            ColorAndIcon(weaknessesLabelFour, weaknessesPart[3], weaknessesIconFour);
            weaknessesLabelFour.setLayoutX(567);
            weaknessesIconFour.setLayoutX(567+6);

            weaknessesLabelFive.setText(weaknessesPart[4]);
            ColorAndIcon(weaknessesLabelFive, weaknessesPart[4], weaknessesIconFive);
            weaknessesLabelFive.setLayoutX(608);
            weaknessesIconFive.setLayoutX(608+6);
            
            weaknessesLabelSix.setText(weaknessesPart[5]);
            ColorAndIcon(weaknessesLabelSix, weaknessesPart[5], weaknessesIconSix);
            weaknessesLabelSix.setLayoutX(647);
            weaknessesIconSix.setLayoutX(647+6);
        }
    }
    public void ColorAndIcon(Label label, String tipo, ImageView image) {
        /*O método ColorAndIcon recebe a label que ele deve modificar, o tipo que ele deve levar em
        * consideração e o ImageView que ele deve colocar o icone referente ao tipo.*/
        switch (tipo) {
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
        //O método genderUpdate verifica o genero e coloca o icone correto para cada situação.
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
        /*O método megaIconUpdate verifica se o pokemon tem versão Mega ou outra versão alternativa
        * e coloca o icone caso necessario.*/
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
        /*Esse método tem a função de garantir que apenas números possam ser digitados na área de pesquisa.
        *(searchTextField)*/
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
        searchTextField.setTextFormatter(textFormatter);
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
