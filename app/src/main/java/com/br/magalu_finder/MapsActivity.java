package com.br.magalu_finder;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Definiçao do layout da activity
        setContentView(R.layout.activity_maps);

        //Retornando o fragment do res/layout
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        //MapaFragment para criar um mapafragment
        tx.replace(R.id.frameMaps, new MapaFragment());
        tx.commit();
    }
}
