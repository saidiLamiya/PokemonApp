package com.example.pokedex.models;
// class Pokemon Ability : donne tous les  abilites des pokemons

import com.google.gson.annotations.SerializedName;

public class PokemonAbility {
    @SerializedName("ability")
    private Ability abilityName;
    @SerializedName("url")
    private String url;

    public Ability getabilityName() {
        return abilityName;
    }

    public void setabilityName(Ability name) {
        this.abilityName = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
