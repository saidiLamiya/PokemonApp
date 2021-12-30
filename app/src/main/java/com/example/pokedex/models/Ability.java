package com.example.pokedex.models;
//class Ability : donne le nom des abilites des pokemons le nom et l url.

import com.google.gson.annotations.SerializedName;

public class Ability {
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
