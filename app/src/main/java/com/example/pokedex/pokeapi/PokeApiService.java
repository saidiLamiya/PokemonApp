package com.example.pokedex.pokeapi;


import android.database.Observable;
import android.view.View;

import com.example.pokedex.PokemonDetail;
import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonById;
import com.example.pokedex.models.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PokeApiService {
    @GET("pokemon/?limit=1000")
    Call<PokemonResponse> ListPokemon();
    //@Query("limit") int limit, @Query("offset") int offset

   @GET("type/{j}")
   Call<PokemonResponse> ListPokemonbyType(@Path("j") String Buttonid);


    @GET("pokemon/{id}")
    Call<PokemonById> OnePokemon(@Path("id")int id);



}
