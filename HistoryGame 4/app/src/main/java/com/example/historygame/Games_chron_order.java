package com.example.historygame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Games_chron_order extends AppCompatActivity {

    Array2 array = new Array2();//
    int N = array.check.length;
    Random random = new Random();
    public int count;
    public int clear;
    public int[] number = new int[4];
    public int[] numberD = new int[4];
    ArrayList<Integer> items = new ArrayList<>();
    public int[] control = new int[4];
    public int[] controlD = new int[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chron_order);

        final TextView txt1A = findViewById(R.id.textViewG1);
        final TextView txt2A = findViewById(R.id.textViewG2);
        final TextView txt3A = findViewById(R.id.textViewG3);
        final TextView txt4A = findViewById(R.id.textViewG4);

        TextView[] txt = new TextView[4];
        txt[0] = txt1A;
        txt[1] = txt2A;
        txt[2] = txt3A;
        txt[3] = txt4A;

        final TextView txt1D = findViewById(R.id.textViewD1);
        final TextView txt2D = findViewById(R.id.textViewD2);
        final TextView txt3D = findViewById(R.id.textViewD3);
        final TextView txt4D = findViewById(R.id.textViewD4);

        TextView[] txtD = new TextView[4];
        txtD[0] = txt1D;
        txtD[1] = txt2D;
        txtD[2] = txt3D;
        txtD[3] = txt4D;

        Button button_back = (Button) findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Games_chron_order.this, Games_centuries.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });

        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            indices.add(i);
        }
        for (int i = 0; i < 4; i++) {
            int k = random.nextInt(indices.size());
            number[i] = indices.get(k);
            indices.remove(k);
            txt[i].setText(array.text[number[i]]);
            control[i] = array.check[number[i]];
        }
        items.add(number[0]);
        items.add(number[1]);
        items.add(number[2]);
        items.add(number[3]);
        ArrayList<Integer> indicesD = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            indicesD.add(i);
        }
        for (int i = 0; i < items.size(); ++i) {
            int k = random.nextInt(indicesD.size());
            numberD[i] = items.get(indicesD.get(k));
            indicesD.remove(k);
            txtD[i].setText(array.data[numberD[i]]);
            controlD[i] = array.check[numberD[i]];
        }
        count = 0;
        clear = 0;
        for (int i = 0; i < 4; i++) {
            int finalI = i;

            txt[finalI].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) { //строка обороботки ивента - касания кнопки
                        //Если коснулся кнопки
                        txt[finalI].setBackgroundColor((getResources().getColor(R.color.teal_200)));
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        for (int j = 0; j < 4; j++) {
                            int k = j;
                            txtD[k].setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View view, MotionEvent motionEvent) {
                                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                        txtD[k].setBackgroundColor((getResources().getColor(R.color.teal_200)));
                                        if (controlD[k] == control[finalI]) {
                                            txt[finalI].setBackgroundColor((getResources().getColor(R.color.green)));
                                            txtD[k].setBackgroundColor((getResources().getColor(R.color.green)));
                                            count = count + 1;
                                            clear = clear + 1;
                                        } else {
                                            txt[finalI].setBackgroundColor((getResources().getColor(R.color.red)));
                                            txtD[k].setBackgroundColor((getResources().getColor(R.color.red)));
                                        }
                                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                        if (count == 0) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    txt[finalI].setBackgroundColor((getResources().getColor(R.color.yellow_500)));
                                                    txtD[k].setBackgroundColor((getResources().getColor(R.color.yellow_500)));
                                                }
                                            }, 500);
                                        }
                                        if (clear % 4 == 0) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    for (int s = 0; s < 4; s++) {
                                                        txt[s].setBackgroundColor((getResources().getColor(R.color.yellow_500)));
                                                        txtD[s].setBackgroundColor((getResources().getColor(R.color.yellow_500)));
                                                    }
                                                }
                                            }, 200);
                                        }
                                    }
                                    return true;
                                }
                            });
                        }
                        count = 0;
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Games_chron_order.this, Games_centuries.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }

}
