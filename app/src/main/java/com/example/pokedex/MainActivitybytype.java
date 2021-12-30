package com.example.pokedex;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivitybytype extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitybytype);
        ActionBar actionBar = getSupportActionBar();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }


    // And override this method
    @Override
    public boolean onNavigateUp() {
        setContentView(R.layout.search_by_categorie);
        //finish();
        return true;
    }
}