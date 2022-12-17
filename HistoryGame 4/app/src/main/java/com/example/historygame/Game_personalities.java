package com.example.historygame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;


public class Game_personalities extends AppCompatActivity {
    int N = 0;
    public int num;
    public int control;//объявляется перменная, которая будет соответсовать событию и сверяться с датами
    public int[] controlDates = new int[4];
    Random random = new Random();

    Connection connection;

    ArrayList<String> persons = new ArrayList<String>();
    ArrayList<String> years = new ArrayList<String>();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    int levelCount = 1;

    String queryParam;
    Integer gameParam;
    Integer numOfLevels;

    Dialog dialog;
    TextView dialogCloseButton;
    Button dialogRepeatButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalities);

        Bundle arguments = getIntent().getExtras();
        queryParam = arguments.get("century").toString();
        gameParam = (Integer) arguments.get("gameParam");
        numOfLevels = (Integer) arguments.get("numOfLevels");

        Database db = new Database();
        connection = db.conclass();
        if (db != null) {
            try {
                String query = "SELECT person_name, birth_year, death_year FROM periodoflive " + queryParam;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String person;
                    String birth, death;
                    person = resultSet.getString("person_name");
                    birth = resultSet.getString("birth_year");
                    death = resultSet.getString("death_year");
                    persons.add(person);
                    years.add(birth +" - "+ death);
                    ids.add(N);
                    N++;
                }
                connection.close();
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
        }

        //Создаем диалоговое окно и команды для него
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.end_game_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialogCloseButton = (TextView)dialog.findViewById(R.id.button_close);
        dialogCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Game_personalities.this, Games_centuries.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
                dialog.hide();
            }
        });
        dialogRepeatButton = (Button) dialog.findViewById(R.id.repeat_game);
        dialogRepeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelCount = 1;
                dialog.hide();
            }
        });

        final TextView txt= findViewById(R.id.task);
        final Button date1 = findViewById(R.id.button_date1);
        final Button date2 = findViewById(R.id.button_date2);
        final Button date3 = findViewById(R.id.button_date3);
        final Button date4 = findViewById(R.id.button_date4);

        Button[] dates = new Button[4];
        dates[0]=date1;
        dates[1]=date2;
        dates[2]=date3;
        dates[3]=date4;

        //Код кнокпи назад верхей - начало
        Button button_back=(Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Game_personalities.this, Games_centuries.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });
        //Код кнокпи назад верхей - конец

        ArrayList<Integer> indices = new ArrayList<>();
        ArrayList<Integer> items = new ArrayList<>();

        for (int i = 0; i < ids.size(); i++) {items.add(i);}

        for (int i = 0; i < 4; i++) {
            num = random.nextInt(items.size());
            int num2=items.get(num);
            controlDates[i] = ids.get(num2);
            indices.add(num2);
            items.remove(num);
            dates[i].setText(years.get(num2));
        }
        int k = random.nextInt(indices.size());
        int number = indices.get(k);
        txt.setText(persons.get(number));
        control=ids.get(number);
        persons.remove(number);
        ids.remove(number);

        for(int i = 0; i < 4; i++){
            int work=i;
            dates[work].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        for(int j=0; j < 4; j++){
                            if (j!=work){dates[j].setEnabled(false);}
                            dates[j].setBackgroundColor((getResources().getColor(R.color.yellow_500)));
                        }
                        if (control==controlDates[work]){
                            dates[work].setBackgroundColor((getResources().getColor(R.color.green)));
                        }else{
                            dates[work].setBackgroundColor((getResources().getColor(R.color.red)));
                        }
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (levelCount == numOfLevels) {
                                    if (gameParam == 1) {
                                        dialog.show();
                                    }
                                    else {
                                        try {
                                            Intent intent = new Intent(Game_personalities.this, Games_chron_order.class);
                                            intent.putExtra("century", queryParam);
                                            intent.putExtra("numOfLevels", 2);
                                            intent.putExtra("gameParam",2);
                                            startActivity(intent);
                                            finish();
                                        } catch (Exception e) {
                                        }
                                        return;
                                    }
                                }
                                ArrayList<Integer> indices = new ArrayList<>();
                                ArrayList<Integer> items = new ArrayList<>();

                                for (int i = 0; i < ids.size(); i++) {items.add(i);}

                                for (int i = 0; i < 4; i++) {
                                    num = random.nextInt(ids.size());
                                    int num2=items.get(num);
                                    controlDates[i] = ids.get(num2);
                                    indices.add(num2);
                                    items.remove(num);
                                    dates[i].setText(years.get(num2));
                                }
                                int k = random.nextInt(indices.size());
                                int number = indices.get(k);
                                txt.setText(persons.get(number));
                                control=ids.get(number);
                                persons.remove(number);
                                ids.remove(number);
                                for(int j=0; j < 4; j++){
                                    dates[j].setEnabled(true);
                                }
                                dates[work].setBackgroundColor((getResources().getColor(R.color.yellow_500)));
                                levelCount++;
                            }
                        }, 500);
                    }
                    return true;
                }
            });
        }
    }

    //Код системной кнопки назад -начало
    @Override
    public void onBackPressed(){
        try{
            Intent intent=new Intent(Game_personalities.this, ChooseGame_Ancient.class);
            startActivity(intent); finish();
        }catch(Exception e) {
        }
    }
    //Код системной кнопки назад - конец
}
