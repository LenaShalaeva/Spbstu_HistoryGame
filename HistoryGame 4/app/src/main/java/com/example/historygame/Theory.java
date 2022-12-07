package com.example.historygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Theory extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.theory);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.theory:
                        return true;
                    case R.id.games:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        TextView textView1=(TextView)findViewById(R.id.textViewG1);
        textView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    Intent intent=new Intent(Theory.this,Theory_Ancient.class);
                    intent.putExtra("header","Древний Казахстан");
                    intent.putExtra("century","1");
                    startActivity(intent);
                }catch(Exception e) {
                }
            }
        });

        TextView textView2=(TextView)findViewById(R.id.textViewG2);
        textView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    Intent intent=new Intent(Theory.this,Theory_Ancient.class);
                    intent.putExtra("header","Средний век");
                    intent.putExtra("century","2");
                    startActivity(intent);
                }catch(Exception e) {
                }
            }
        });

        TextView textView3=(TextView)findViewById(R.id.textViewG3);
        textView3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    Intent intent=new Intent(Theory.this,Theory_Ancient.class);
                    intent.putExtra("header","Новое время");
                    intent.putExtra("century","3");
                    startActivity(intent);
                }catch(Exception e) {
                }
            }
        });

        TextView textView4=(TextView)findViewById(R.id.textViewG4);
        textView4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    Intent intent=new Intent(Theory.this,Theory_Ancient.class);
                    intent.putExtra("header","Новейшее время");
                    intent.putExtra("century","4");
                    startActivity(intent);
                }catch(Exception e) {
                }
            }
        });


    }

}