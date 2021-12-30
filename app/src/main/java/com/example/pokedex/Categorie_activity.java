package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.pokedex.models.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Categorie_activity extends AppCompatActivity implements View.OnClickListener {
    Button getFire, getWater, getRock, getNormal, getDark, getVenom, getGrass, getElectrik, getIce, getCombat, getGround,
            getFlying, getPsychic, getBug, getGhost, getDragon, getFairy, getSteel;
    public static final  String parat = "com.example.pokedex.parat";
    Handler handler = new Handler();
    private static final String TAG = "POKEDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_categorie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        getFire = findViewById(R.id.Fire);
        getWater = findViewById(R.id.Water);
        getRock = findViewById(R.id.Rock);
        getNormal = findViewById(R.id.Normal);
        getDark = findViewById(R.id.Dark);
        getDragon = findViewById(R.id.Dragon);
        getVenom = findViewById(R.id.Venom);
        getGrass = findViewById(R.id.Grass);
        getElectrik = findViewById(R.id.Electrik);
        getIce = findViewById(R.id.Ice);
        getCombat = findViewById(R.id.Combat);
        getGround = findViewById(R.id.Ground);
        getFlying = findViewById(R.id.Flying);
        getPsychic = findViewById(R.id.Psychic);
        getBug = findViewById(R.id.Bug);
        getGhost = findViewById(R.id.Ghost);
        getFairy = findViewById(R.id.Fairy);
        getSteel = findViewById(R.id.Steel);

        getFairy.setOnClickListener(this);
        getFire.setOnClickListener(this);
        getWater.setOnClickListener(this);
        getBug.setOnClickListener(this);
        getFlying.setOnClickListener(this);
        getSteel.setOnClickListener(this);
        getRock.setOnClickListener(this);
        getNormal.setOnClickListener(this);
        getIce.setOnClickListener(this);
        getDark.setOnClickListener(this);
        getDragon.setOnClickListener(this);
        getElectrik.setOnClickListener(this);
        getGhost.setOnClickListener(this);
        getCombat.setOnClickListener(this);
        getGrass.setOnClickListener(this);
        getGround.setOnClickListener(this);
        getVenom.setOnClickListener(this);
        getPsychic.setOnClickListener(this);

    }

//switch categories selon le bouton click :affiche un message Toast et envoie le type en  parametre.

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Fire:
                Toast.makeText(Categorie_activity.this, "You clicked on FIRE" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Categorie_activity.this,MainActivityType.class);
                String para="fire";
               intent.putExtra(parat,para);
                      startActivity(intent);
                      break;
            case R.id.Water:
                Toast.makeText(Categorie_activity.this, "You clicked on WATER" , Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para1="water";
                intent1.putExtra(parat,para1);
                   startActivity(intent1);
                   break;
            case R.id.Normal:
                Toast.makeText(Categorie_activity.this, "You clicked on NORMAL" , Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para2="normal";
                intent2.putExtra(parat,para2);
                startActivity(intent2);
                break;
            case R.id.Dragon:
                Toast.makeText(Categorie_activity.this, "You clicked on DRAGON" , Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para4="dragon";
                intent4.putExtra(parat,para4);
                startActivity(intent4);
                break;
            case R.id.Fairy:
                Toast.makeText(Categorie_activity.this, "You clicked on FAIRY" , Toast.LENGTH_SHORT).show();
                Intent intent18 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para18= "fairy";
                intent18.putExtra(parat,para18);
                startActivity(intent18);
                break;
            case R.id.Ghost:
                Toast.makeText(Categorie_activity.this, "You clicked on GHOST" , Toast.LENGTH_SHORT).show();
                Intent intent17 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para17= "ghost";
                intent17.putExtra(parat,para17);
                startActivity(intent17);
                break;
            case R.id.Ice:
                Toast.makeText(Categorie_activity.this, "You clicked on ICE" , Toast.LENGTH_SHORT).show();
                Intent intent16 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para16= "ice";
                intent16.putExtra(parat,para16);
                startActivity(intent16);
                break;
            case R.id.Flying:
                Toast.makeText(Categorie_activity.this, "You clicked on FLYING"  , Toast.LENGTH_SHORT).show();
                Intent intent15 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para15= "flying";
                intent15.putExtra(parat,para15);
                startActivity(intent15);
                break;
            case R.id.Dark:
                Toast.makeText(Categorie_activity.this, "You clicked on DARK" , Toast.LENGTH_SHORT).show();
                Intent intent14 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para14= "dark";
                intent14.putExtra(parat,para14);
                startActivity(intent14);
                break;
            case R.id.Ground:
                Toast.makeText(Categorie_activity.this, "You clicked on GROUND"  , Toast.LENGTH_SHORT).show();
                Intent intent13 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para13= "ground";
                intent13.putExtra(parat,para13);
                startActivity(intent13);
                break;
            case R.id.Electrik:
                Toast.makeText(Categorie_activity.this, "You clicked on ELECTRIK" , Toast.LENGTH_SHORT).show();
                Intent intent12 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para12= "electric";
                intent12.putExtra(parat,para12);
                startActivity(intent12);
                break;
            case R.id.Venom:
                Toast.makeText(Categorie_activity.this, "You clicked on VENOM" , Toast.LENGTH_SHORT).show();
                Intent intent11 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para11= "poison";
                intent11.putExtra(parat,para11);
                startActivity(intent11);
                break;
            case R.id.Psychic:
                Toast.makeText(Categorie_activity.this, "You clicked on PSYCHIC" , Toast.LENGTH_SHORT).show();
                Intent intent10 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para10= "psychic";
                intent10.putExtra(parat,para10);
                startActivity(intent10);
                break;
            case R.id.Bug:
                Toast.makeText(Categorie_activity.this, "You clicked on BUG" , Toast.LENGTH_SHORT).show();
                Intent intent9 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para9= "bug";
                intent9.putExtra(parat,para9);
                startActivity(intent9);
                break;
            case R.id.Steel:
                Toast.makeText(Categorie_activity.this, "You clicked on STEEL" , Toast.LENGTH_SHORT).show();
                Intent intent8 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para8= "steel";
                intent8.putExtra(parat,para8);
                startActivity(intent8);
                break;
            case R.id.Grass:
                Toast.makeText(Categorie_activity.this, "You clicked on GRASS"  , Toast.LENGTH_SHORT).show();
                Intent intent7 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para7= "grass";
                intent7.putExtra(parat,para7);
                startActivity(intent7);
                break;
            case R.id.Combat:
                Toast.makeText(Categorie_activity.this, "You clicked on COMBAT" , Toast.LENGTH_SHORT).show();
                Intent intent6 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para6= "fighting";
                intent6.putExtra(parat,para6);
                startActivity(intent6);
                break;
            case R.id.Rock:
                Toast.makeText(Categorie_activity.this, "You clicked on ROCK"  , Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(Categorie_activity.this,MainActivityType.class);
                String para5= "rock";
                intent5.putExtra(parat,para5);
                startActivity(intent5);
                break;
        }

    }
}