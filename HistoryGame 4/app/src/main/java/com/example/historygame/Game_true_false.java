package com.example.historygame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class Game_true_false extends AppCompatActivity {
    public int num;//объявляется переменная, которая рандомом будет генерирвоаться и от неё будут и текст события и правдивость события
    public int control;//объявляется перменная, которая будет сверяться с правдивость события
    Array array = new Array();//объявляется массив?????
    Random random = new Random();
    public int count; //счетчик правильных ответов, надо его как-то потом использовать, но я пока не придумала как именно

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);

        final TextView txt = findViewById(R.id.task);
        final Button truebutton = findViewById(R.id.button_believe);
        final Button falsebutton = findViewById(R.id.button_not_believe);

        Button button_back = (Button) findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Game_true_false.this, Games_centuries.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });

        num = random.nextInt(12);//тут рнадомом генерируется число от 0 до 12 не включая, т.к. пока столько у меня есть в массиве

        txt.setText(array.text[num]);//тут извлекается текст по num
        control = array.check[num];//тут извлекается код правдивости по num

//Код обработк нажатия кнопки Верю -начало
        truebutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { //строка обороботки ивента - касания кнопки
                    //Если коснулся кнопки
                    falsebutton.setEnabled(false);//блокирую другую кнопку, если нажата первая
                    if (control == 1) { //проверка правдивое ли событие
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

                            num = random.nextInt(12); //заново рандомом выбирает число

                            txt.setText(array.text[num]); //вытаскивается текст для числа num из массива текстов
                            control = array.check[num]; //вытаскивается число правды (0 или 1) для события
                            falsebutton.setEnabled(true); //тут возвращается работоспособность другой кнопки
                            truebutton.setBackgroundColor((getResources().getColor(R.color.yellow_500))); //тут возвращается цвет кнопки
                        }
                    }, 500);
                    //конец кода задержки
                }
                return true;
            }
        });
//Код обработк нажатия кнопки Верю - конец

//Код обработк нажатия кнопки НЕ Верю -начало
        falsebutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //Если коснулся кнопки
                    truebutton.setEnabled(false);//блокирую другую кнопку, если нажата первая
                    if (control == 0) {
                        falsebutton.setBackgroundColor((getResources().getColor(R.color.green)));
                        count = count + 1;
                    } else {
                        falsebutton.setBackgroundColor((getResources().getColor(R.color.red)));
                    }

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    //Если отпустил кнопку

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            num = random.nextInt(12);

                            txt.setText(array.text[num]);
                            control = array.check[num];
                            truebutton.setEnabled(true);
                            falsebutton.setBackgroundColor((getResources().getColor(R.color.yellow_500)));
                        }
                    }, 500);
                }
                return true;
            }
        });

    }

    //Код системной кнопки назад -начало
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Game_true_false.this, Games_centuries.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }
    //Код системной кнопки назад - конец
}
