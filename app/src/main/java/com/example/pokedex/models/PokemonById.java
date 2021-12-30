package com.example.pokedex.models;
//class Pokemon by ID : permet de creer un objet de type pokemon selon le id donne en argument

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonById {
    @SerializedName("name")
    private String name;
    @SerializedName("height")
    private int height;
    @SerializedName("weight")
    private int weight;
    @SerializedName("order")
    private int order;
    @SerializedName("base_experience")
    private int baseExperience;
    @SerializedName("abilities")
    private ArrayList<PokemonAbility> pokemonAbility;

    public ArrayList<PokemonAbility> getPokemonAbility() {
        return pokemonAbility;
    }

    public void setPokemonAbility(ArrayList<PokemonAbility> pokemonAbility) {
        this.pokemonAbility = pokemonAbility;
    }

    public int getOrder() {
        return order;
    }



    public PokemonById(String name, int height, int weight, int order, int baseExperience) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.order = order;
        this.baseExperience = baseExperience;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }
    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
