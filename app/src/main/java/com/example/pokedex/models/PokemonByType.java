package com.example.pokedex.models;
//class Pokemon BY Type : donne tous les pokemons par type cherche.
import com.google.gson.annotations.SerializedName;

public class PokemonByType {
    @SerializedName("slot")
    private int slot;
    @SerializedName("pokemon")
    private Pokemon pokemon;

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }


}
