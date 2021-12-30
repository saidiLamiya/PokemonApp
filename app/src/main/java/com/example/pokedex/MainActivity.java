package com.example.pokedex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonResponse;
import com.example.pokedex.pokeapi.PokeApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG="POKEDEX";
    private RecyclerView recyclerView;
    private PokemonListAdapter pokemonListAdapter;
    private boolean test;
    private Toolbar toolbar1;
    List<String> Last_suggest = new ArrayList<>();
    Handler handler = new Handler();
    SearchView searchView;
    @Override

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //fonction qui depuis la classe PokemnListAdapter récupère le numero du pokemon et remplace le fragment du main activity
    //par fragment pokemon detail

    BroadcastReceiver showDetail = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().toString().equals(Common.KEY_ENABLE_HOME)) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);

                Fragment detailFragment = PokemonDetail.getInstance();

                Bundle bundle = new Bundle();

                int num = intent.getIntExtra("num",0);
                bundle.putInt("num",num-1);
                if (detailFragment.getArguments() == null) {
                    detailFragment.setArguments(bundle);
                } else {
                    //Consider explicitly clearing arguments here
                    detailFragment.getArguments().putAll(bundle);
                }

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        recyclerView.setAlpha(0);
                        fragmentTransaction.replace(R.id.anything, detailFragment);
                        fragmentTransaction.addToBackStack("detail");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commitAllowingStateLoss();
                    }
                },1);


                Pokemon pokemon = Common.findPokemonByNum(num);
            }


        }
    };

    //fait l'appelle du brodcastmanager lorsque le pokemon est cliquer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastManager.getInstance(this).registerReceiver(showDetail,new IntentFilter(Common.KEY_ENABLE_HOME));
        recyclerView = findViewById(R.id.recyclerView);
        pokemonListAdapter = new PokemonListAdapter(this);
        recyclerView.setAdapter(pokemonListAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManger = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManger);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        test = true;
        ObtenirData();

    }
    // button retour detruit le contenu du fragment qui a été remplacer et récupere le main activity
    @Override
    public boolean onSupportNavigateUp() {
        //finish();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment bottomFragment = manager.findFragmentById(R.id.anything);
        bottomFragment.getArguments().clear();
        Fragment detailFragment = PokemonDetail.getInstance();

        ft.remove(detailFragment).commit();
        recyclerView.setAlpha(1);
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(false);
        return true;
    }

    //De l'api on obtient le data du pokemon(nom et numero) et on le met dans commonPokemonList
    private void ObtenirData(){
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokemonResponse> pokemonResponseCall = service.ListPokemon();
        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                test = true;
                if(response.isSuccessful()){
                    PokemonResponse pokemonResponse = response.body();
                    ArrayList<Pokemon> listPokemon = pokemonResponse.getResults();
                    Common.commonPokemonList = listPokemon;
                    pokemonListAdapter.Add(listPokemon);
                    for(int i=0;i<listPokemon.size();i++){
                        Pokemon p = listPokemon.get(i);
                        Log.i(TAG,"Pokemon: "+p.getUrl());
                    }
                }else{
                    Log.e(TAG,"onResponse:"+response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                test = true;
                Log.e(TAG,"onFailure:"+t.getMessage());
            }
        });
    }


    public boolean onOptionItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toolbar1.setTitle("Pokemon List");
                //Clear all fragment detail and pop list fragment
                getSupportFragmentManager().popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);


                break;
            default:
                break;
        }
        return true;
    }

    ///menu search khedam perfect
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                pokemonListAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

        //menu dyal categorie w favoris
    //kaydik l categorie mazal lokhrin
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.categorie:
                Intent intent = new Intent(MainActivity.this,Categorie_activity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}