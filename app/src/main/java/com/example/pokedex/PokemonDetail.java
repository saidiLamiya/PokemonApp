package com.example.pokedex;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokedex.models.Ability;
import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonAbility;
import com.example.pokedex.models.PokemonById;
import com.example.pokedex.models.PokemonByType;
import com.example.pokedex.models.PokemonResponse;
import com.example.pokedex.pokeapi.PokeApiService;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokemonDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonDetail<retrofit> extends Fragment {
    //private Retrofit retrofit;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "height";
    private static final String ARG_PARAM2 = "weight";
    private static final String TAG ="H/W" ;

    // TODO: Rename and change types of parameters
    private Retrofit retrofit;

    TextView pokemon_name,pokemon_height,pokemon_weight,pokemon_order,pokemon_base_experience,
            pokemon_ability;
    RecyclerView recycle_type,recycle_weakness,recycle_next_evolution,recycle_prev_evolution;
    ImageView pokemon_img;
    public PokemonDetail() {
        // Required empty public constructor
    }
    static PokemonDetail instance;
    public static PokemonDetail getInstance(){
        if(instance == null)
            instance = new PokemonDetail();
        return instance;}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PokemonDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static PokemonDetail newInstance(float param1, float param2) {
        PokemonDetail fragment = new PokemonDetail();
        Bundle args = new Bundle();
        args.putFloat(ARG_PARAM1, param1);
        args.putFloat(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewItem = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        int i =0;
        i= getArguments().getInt("num")+1;
        Log.i(TAG, "onCreateView: "+i);
        pokemon_ability=(TextView) viewItem.findViewById(R.id.abilities);
        pokemon_img = (ImageView)viewItem.findViewById(R.id.pokemon_image);
        pokemon_name = (TextView) viewItem.findViewById(R.id.name);
        pokemon_weight = (TextView) viewItem.findViewById(R.id.weight);
        pokemon_height= (TextView) viewItem.findViewById(R.id.height);
        pokemon_order= (TextView) viewItem.findViewById(R.id.order);
        pokemon_base_experience=(TextView) viewItem.findViewById(R.id.base_experience);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        setDetailPokemon(i);
        return  viewItem;
    }


    private void setDetailPokemon(int j) {
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokemonById> pokemonResponseCall = service.OnePokemon(j);
        Log.i(TAG, "setdetail: "+j);
        pokemonResponseCall.enqueue(new Callback<PokemonById>() {
            @Override
            public void onResponse(Call<PokemonById> call, Response<PokemonById> response) {
                if (response.isSuccessful()) {
                    PokemonById onePokemon = response.body();
                    ArrayList<PokemonAbility> abilities=onePokemon.getPokemonAbility();
                    Log.i(TAG, "onResponse36: "+abilities);
                    if( abilities.size()==3){
                        PokemonAbility pokemonAbility1= abilities.get(0);
                        PokemonAbility pokemonAbility2= abilities.get(1);
                        PokemonAbility pokemonAbility3= abilities.get(2);
                        String name1= pokemonAbility1.getabilityName().getName();
                        String name2=pokemonAbility2.getabilityName().getName();
                        String name3=pokemonAbility3.getabilityName().getName();
                        pokemon_ability.setText("Abilities: "+name1+","+name2+","+name3+"");}
                    else if(abilities.size()==2) {
                        PokemonAbility pokemonAbility1= abilities.get(0);
                        PokemonAbility pokemonAbility2= abilities.get(1);
                        String name1= pokemonAbility1.getabilityName().getName();
                        String name2=pokemonAbility2.getabilityName().getName();
                        pokemon_ability.setText("Abilities: "+name1+","+name2+"");}
                    else {
                        PokemonAbility pokemonAbility=abilities.get(0);
                        String name= pokemonAbility.getabilityName().getName();
                        pokemon_ability.setText("Abilities:"+name+"");

                    }

                    int height= onePokemon.getHeight();
                    int weight= onePokemon.getWeight();
                    int order=onePokemon.getOrder();
                    int base_experience=onePokemon.getBaseExperience();
                    pokemon_name.setText(onePokemon.getName());
                    pokemon_weight.setText( "Weight:          "+weight);
                    pokemon_height.setText( "Height:          "+height);
                    pokemon_order.setText("order:               "+ order);
                    pokemon_base_experience.setText("base experience:    "+base_experience);
                    Glide.with(getActivity()).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+j+".png").into(pokemon_img);

                    Log.i(TAG, "onResponse: "+height+" "+weight+""+order+" "+base_experience);
                } else {
                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonById> call, Throwable t) {

                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });

    }
}