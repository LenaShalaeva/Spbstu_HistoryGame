package com.example.historygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Theory_Ancient extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_ancient);


    }

    @Override
    public void onBackPressed(){
        try{
            Intent intent=new Intent(Theory_Ancient.this,Theory.class);
            startActivity(intent); finish();
        }catch(Exception e) {
        }
    }
}