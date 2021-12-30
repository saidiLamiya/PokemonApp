package com.example.pokedex.models;
//class utilisee pour selectionner les reponses des API.

import com.example.pokedex.PokemonDetail;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonResponse {
    @SerializedName("results")
    private ArrayList<Pokemon> results;
    @SerializedName("pokemon")
    private ArrayList<PokemonByType> pokemon;



    public ArrayList<PokemonByType> getPokemons() {
        return pokemon;
    }

    public void setPokemons(ArrayList<PokemonByType> pokemons) {
        this.pokemon = pokemons;
    }





    public ArrayList<Pokemon> getResults() {
        return results;
    }


    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }




}
