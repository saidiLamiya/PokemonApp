package com.example.pokedex.models;
//class Pokemon: est le pokemon de la page principale

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    private int number;
    @SerializedName("img")
    private String img;
    @SerializedName("height")
    private String height;
    @SerializedName("weight")
    private String weight;
    private List<String> type;
    private List<String> weaknesses;


    public Pokemon() {
    }

    public String getImg() {
        return img;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public List<String> getType() {
        return type;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }


    public Pokemon(String name, int number, String img, String height, String weight, List<String> type, List<String> weaknesses) {
        this.name = name;
        this.number = number;
        this.img = img;
        this.height = height;
        this.weight = weight;
        this.type = type;
        this.weaknesses = weaknesses;

    }

    public Pokemon(String img, String height, String weight) {
        this.img = img;
        this.height = height;
        this.weight = weight;

    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlPart=url.split("/");
        return Integer.parseInt(urlPart[urlPart.length - 1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
