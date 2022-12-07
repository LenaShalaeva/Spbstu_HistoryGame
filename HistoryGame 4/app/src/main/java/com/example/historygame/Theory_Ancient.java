package com.example.historygame;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Theory_Ancient extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView header, centuryHeader, theory;
    ScrollView scroll;
    String centuryName;
    String century;
    ArrayList<String> headers = new ArrayList<String>();
    ArrayList<String> texts = new ArrayList<String>();
    int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_ancient);

        Bundle arguments = getIntent().getExtras();
        centuryName = arguments.get("header").toString();
        century = arguments.get("century").toString();


        centuryHeader = findViewById(R.id.textView);
        centuryHeader.setText(centuryName);
        header = findViewById(R.id.textView3);
        theory = findViewById(R.id.textView4);
        scroll = (ScrollView) findViewById(R.id.scroll_view);

        Database db = new Database();
        centuryHeader.setText(centuryName);
        Connection connection = db.conclass();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT header, theory_text FROM theory_table WHERE century ='" + century + "'");
            String headerText, theoryText;
            while (resultSet.next()) {
                headerText = resultSet.getString("header");
                theoryText = resultSet.getString("theory_text");
                headers.add(headerText);
                texts.add(theoryText);
            }
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        header.setText(headers.get(num));
        theory.setText(texts.get(num));

        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == 1){
                    try {
                    Intent intent = new Intent(Theory_Ancient.this, Theory.class);
                    startActivity(intent);
                    finish();
                    } catch (Exception e) {
                    }
                }
                else{
                    num--;
                    scroll.scrollTo(0,0);
                    header.setText(headers.get(num));
                    theory.setText(texts.get(num));
                }
            }
        });

        Button button_forward = (Button) findViewById(R.id.button_forward);
        button_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                scroll.scrollTo(0,0);
                header.setText(headers.get(num));
                theory.setText(texts.get(num));
            }
        });
    }

    class Task extends AsyncTask<Void, Void, Void> {
        String headerText, theoryText;

        @Override
        protected Void doInBackground(Void... voids) {
            Database db = new Database();
            Connection connection = db.conclass();
            int n = 0;
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT header, theory_text FROM theory_table WHERE century ='" + century + "'");
                while (resultSet.next() && n < num) {
                    headerText = resultSet.getString("header");
                    theoryText = resultSet.getString("theory_text");
                    headers.add(headerText);
                }
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            header.setText(headerText);
            theory.setText(theoryText);
            centuryHeader.setText(centuryName);
            super.onPostExecute(unused);
        }
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
