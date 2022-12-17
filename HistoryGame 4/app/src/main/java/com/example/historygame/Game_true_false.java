package com.example.historygame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;


public class Game_true_false extends AppCompatActivity {

    int num;//объявляется переменная, которая рандомом будет генерирвоаться и от неё будут и текст события и правдивость события
    public Boolean control;//объявляется перменная, которая будет сверяться с правдивость события
    public int count; //счетчик правильных ответов
    Random random = new Random();

    Connection connection;

    ArrayList<String> events = new ArrayList<String>();
    ArrayList<Boolean> answers = new ArrayList<Boolean>();
    int levelCount = 1;

    String queryParam;
    Integer gameParam;
    Integer numOfLevels;

    Dialog dialog;
    TextView dialogCloseButton;
    Button dialogRepeatButton;


    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_true_false);

        //Принимаем аргументы с предыдущего активити
        Bundle arguments = getIntent().getExtras();
        queryParam = arguments.get("century").toString();
        gameParam = (Integer) arguments.get("gameParam");
        numOfLevels = (Integer) arguments.get("numOfLevels");


        //Вытаскиеваем из базы данных все для уровней
        Database db = new Database();
        connection = db.conclass();
        if (db != null) {
            try {
                String query = "SELECT level_event, answer FROM trueorfalse " + queryParam;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String event;
                    boolean answer;
                    event = resultSet.getString("level_event");
                    answer = resultSet.getBoolean("answer");
                    events.add(event);
                    answers.add(answer);
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
                    Intent intent = new Intent(Game_true_false.this, Games_themes.class);
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

        //Находим все кнопки активити по id
        final TextView txt = findViewById(R.id.task);
        final Button truebutton = findViewById(R.id.button_believe);
        final Button falsebutton = findViewById(R.id.button_not_believe);
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Game_true_false.this, Games_themes.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });
//Выбираем начальные параметры для отображения на активити
        num = random.nextInt(events.size());
        txt.setText(events.get(num));
        control = answers.get(num);
        events.remove(num);
        answers.remove(num);

//Код обработк нажатия кнопки Верю -начало
        truebutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { //строка обороботки ивента - касания кнопки
                    //Если коснулся кнопки
                    falsebutton.setEnabled(false);//блокирую другую кнопку, если нажата первая
                    falsebutton.setBackgroundColor((getResources().getColor(R.color.yellow_500)));
                    if (control == true) { //проверка правдивое ли событие
                        truebutton.setBackgroundColor((getResources().getColor(R.color.green))); //кнопка становится зеленой
                    } else {
                        truebutton.setBackgroundColor((getResources().getColor(R.color.red))); //кнопка становится красной
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) { //строка обработки ивента - отпускания кнопки
                    //Если отпустил кнопку
                    //начало кода задержки, т.е. действия внутри выполняются через время delayMilis
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (levelCount == numOfLevels){
                                if (gameParam == 1){
                                    dialog.show();
                                }
                                else {
                                    try {
                                        Intent intent = new Intent(Game_true_false.this, Game_personalities.class);
                                        intent.putExtra("century",queryParam);
                                        intent.putExtra("numOfLevels", 2);
                                        intent.putExtra("gameParam",2);
                                        startActivity(intent);
                                        finish();
                                    } catch (Exception e) {
                                    }
                                    return;
                                }
                            }
                            num = random.nextInt(events.size()); //заново рандомом выбирает число
                            txt.setText(events.get(num));
                            control = answers.get(num);
                            events.remove(num);
                            answers.remove(num);
                            falsebutton.setEnabled(true); //тут возвращается работоспособность другой кнопки
                            truebutton.setBackgroundColor((getResources().getColor(R.color.yellow_500))); //тут возвращается цвет кнопки
                            levelCount++;
                        }
                    }, 500);
                    //конец кода задержки
                }
                return true;
            }
        });


//Код обработк нажатия кнопки НЕ Верю -начало
        falsebutton.setOnTouchListener((view, motionEvent) -> {
            if (MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
                //Если коснулся кнопки
                truebutton.setEnabled(false);//блокирую другую кнопку, если нажата первая
                truebutton.setBackgroundColor((getResources().getColor(R.color.yellow_500))); //тут возвращается цвет кнопки
                if (control == false) {
                    Resources resources = this.getResources();
                    falsebutton.setBackgroundColor((resources.getColor(R.color.green)));
                    count = count + 1;
                } else {
                    falsebutton.setBackgroundColor((Game_true_false.this.getResources().getColor(R.color.red)));
                }
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                //Если отпустил кнопку
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (levelCount == numOfLevels){
                            if (gameParam == 1){
                                dialog.show();
                            }
                            else {
                                try {
                                    Intent intent = new Intent(Game_true_false.this, Game_personalities.class);
                                    intent.putExtra("century",queryParam);
                                    intent.putExtra("numOfLevels", 2);
                                    intent.putExtra("gameParam",2);
                                    startActivity(intent);
                                    finish();
                                } catch (Exception e) {
                                }
                                return;
                            }
                        }
                        num = random.nextInt(events.size());
                        txt.setText(events.get(num));
                        control = answers.get(num);
                        events.remove(num);
                        answers.remove(num);
                        truebutton.setEnabled(true);
                        falsebutton.setBackgroundColor((getResources().getColor(R.color.yellow_500)));
                        levelCount++;
                    }
                }, 500);
            }
            return true;
        });
    }

    //Код системной кнопки назад -начало
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Game_true_false.this, Games_themes.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }
    //Код системной кнопки назад - конец
}
