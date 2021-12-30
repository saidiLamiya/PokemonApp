package com.example.pokedex;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonByType;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static final String KEY_ENABLE_HOME="enable_home";
    public static final String KEY_ENABLE_TYPE="enable_home";
    public static final String KEY_NUM_EVOLUTION = "num";
    public static List<Pokemon> commonPokemonList = new ArrayList<>();
    public static ArrayList<PokemonByType> commonPokemonList2 = new ArrayList<>();
    public static Pokemon findPokemonByNum(int num) {
        for(Pokemon pokemon : commonPokemonList)
        {
            if(pokemon.getNumber()==num)
                return pokemon;
        }
        return null;
    }
    public static PokemonByType findPokemonByNum2(int num) {
        for(PokemonByType pokemon : commonPokemonList2)
        {
            if(pokemon.getSlot()==num)
                return pokemon;
        }
        return null;
    }


}
