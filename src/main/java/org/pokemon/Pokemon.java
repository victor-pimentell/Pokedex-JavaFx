package org.pokemon;

import java.util.List;

public class Pokemon {
    private String name;
    private int id;
    private String number;
    private String image;
    private List<String> type;
    private String backGroundColor;
    private List<String> weaknesses;
    private int generation;
    private String region;
    private String shiny;
    private List<String> specialFormName;
    private List<String> specialFormImage;
    private String height;
    private String weight;
    private String category;
    private String gender;
    private boolean mega;
    private boolean alternativeVersion;

    public boolean isAlternativeVersion() {
        return alternativeVersion;
    }

    public void setAlternativeVersion(boolean alternativeVersion) {
        this.alternativeVersion = alternativeVersion;
    }

    public boolean isMega() {
        return mega;
    }

    public void setMega(boolean mega) {
        this.mega = mega;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getShiny() {
        return shiny;
    }

    public void setShiny(String shiny) {
        this.shiny = shiny;
    }

    public List<String> getSpecialFormName() {
        return specialFormName;
    }

    public void setSpecialFormName(List<String> specialFormName) {
        this.specialFormName = specialFormName;
    }

    public List<String> getSpecialFormImage() {
        return specialFormImage;
    }

    public void setSpecialFormImage(List<String> specialFormImage) {
        this.specialFormImage = specialFormImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", number='" + number + '\'' +
                ", image='" + image + '\'' +
                ", type=" + type +
                ", backGroundColor='" + backGroundColor + '\'' +
                ", weaknesses=" + weaknesses +
                '}';
    }
}
