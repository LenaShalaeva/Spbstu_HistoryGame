package com.example.historygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

// ЭТО ЭКРАН ПО ТЕМАМ, НЕ ПО ЭПОХАМ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// ЭТО ЭКРАН ПО ТЕМАМ, НЕ ПО ЭПОХАМ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// ЭТО ЭКРАН ПО ТЕМАМ, НЕ ПО ЭПОХАМ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// ЭТО ЭКРАН ПО ТЕМАМ, НЕ ПО ЭПОХАМ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// ЭТО ЭКРАН ПО ТЕМАМ, НЕ ПО ЭПОХАМ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// ЭТО ЭКРАН ПО ТЕМАМ, НЕ ПО ЭПОХАМ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


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
//переход с кнопки на экран с игрой правда/ложь
        TextView textView1=(TextView)findViewById(R.id.textViewG1);
        textView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    Intent intent=new Intent(Games_centuries.this,Game_true_false.class);
                    startActivity(intent); finish();
                }catch(Exception e) {
                }
            }
        });

        TextView textView2=(TextView)findViewById(R.id.textViewG2);
        textView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    Intent intent=new Intent(Games_centuries.this,Games_chron_order.class);
                    startActivity(intent);
                }catch(Exception e) {
                }
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
