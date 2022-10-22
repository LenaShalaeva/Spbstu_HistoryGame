package com.example.historygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Games_centuries extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_centuries);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.games);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.theory:
                        startActivity(new Intent(getApplicationContext(),Theory.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.games:
                        return true;
                }
                return false;
            }
        });

        //Knopka BACKKKKKK
        Button button_back=(Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Games_centuries.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });
        //Zakochilsya apdeit knopki Nazad

    }

    //Апдейт кода: эта хуйня делает так, чтобы системной кнопкой назад было назад
    @Override
    public void onBackPressed(){
        try{
            Intent intent=new Intent(Games_centuries.this,MainActivity.class);
            startActivity(intent); finish();
        }catch(Exception e) {
        }
    }
    //Законичлся апдейт кода
}
