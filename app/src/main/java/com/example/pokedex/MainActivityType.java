package com.example.pokedex;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.ViewModel;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Handler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonByType;
import com.example.pokedex.models.PokemonResponse;
import com.example.pokedex.pokeapi.PokeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityType extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG = "POKEDEX";
    private RecyclerView recyclerView;
    private RecyclerView rv;
    private PokemonByTypeListAdapter pokemonListAdapterByType;
    private PokemonListAdapter pokemonListAdapter;
    private boolean test;
    Handler handler = new Handler();
    public String para;
    public GridLayoutManager gridLayout;

    //fonction qui depuis la classe PokemnListAdapter récupère le numero du pokemon et remplace le fragment du main activity by type
    //par fragment list of pokemon
    BroadcastReceiver showDetail = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent b) {
            if (b.getAction().toString().equals(Common.KEY_ENABLE_HOME)) {
                Log.i(TAG, "Pokemon:  abccccccccccccc");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);

                Fragment detailFragment1 = ListOfPokemon.getInstance();

                int num = b.getIntExtra("num", 0);
                Bundle bun = new Bundle();
                bun.putInt("num", num);


                detailFragment1.setArguments(bun);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAlpha(0);
                        fragmentTransaction.replace(R.id.badidi, detailFragment1);
                        fragmentTransaction.addToBackStack("detail");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commitAllowingStateLoss();
                    }
                },1);

                int Pokemon=Common.commonPokemonList.get(num).getNumber();
                Log.i(TAG, "Pokemon:  abccccccccccccc2"+Common.commonPokemonList.get(num).getNumber());
            }


        }
    };
    @Override
    public boolean onSupportNavigateUp() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment bottomFragment = manager.findFragmentById(R.id.badidi);
        ft.remove(bottomFragment);
        ft.commit();
        recyclerView.setAlpha(1);
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(false);
        return true;
    }
//methode qui permet de creer les pokemons selon le type donner
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitybytype);
        LocalBroadcastManager.getInstance(this).registerReceiver(showDetail,new IntentFilter(Common.KEY_ENABLE_HOME));
        recyclerView = findViewById(R.id.recyclerView);
        pokemonListAdapterByType = new PokemonByTypeListAdapter(this);
        recyclerView.setAdapter(pokemonListAdapterByType);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManger = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManger);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        test = true;
        ObtenirData(para);
    }
//methode qui prend en argument le type des categories et  affiche les pokemons de ce dernier
    private void ObtenirData(String j) {

        Intent intent= getIntent();
        para = intent.getStringExtra(Categorie_activity.parat);
        Log.i(TAG, "onCreate: "+ para);
        j=para;
        Log.i(TAG, "ObtenirData: "+j);
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokemonResponse> pokemonResponseCall = service.ListPokemonbyType(j);

        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                test = true;
                if (response.isSuccessful()) {
                    PokemonResponse pokemonResponse = response.body();

                    ArrayList<PokemonByType> listPokemon = pokemonResponse.getPokemons();
                    pokemonListAdapterByType.AddByType(listPokemon);
                    Common.commonPokemonList2=listPokemon;
                    Log.i(TAG, "Pokemon: " + listPokemon);

                    for (int i = 0; i < listPokemon.size(); i++) {
                        PokemonByType pokemons = listPokemon.get(i);

                          Pokemon p = pokemons.getPokemon();
                          Common.commonPokemonList.add(p);
                        Log.i(TAG, "Pokemon: " + p.getUrl());
                    }
                } else {
                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                test = true;
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

}