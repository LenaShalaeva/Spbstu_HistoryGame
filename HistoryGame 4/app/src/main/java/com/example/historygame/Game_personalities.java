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


public class Game_personalities extends AppCompatActivity {
    public int num;
    public int control;//объявляется перменная, которая будет соответсовать событию и сверяться с датами
    Array3 array = new Array3();//объявляется массив?????
    Random random = new Random();
    public int[] controlDates = new int[4];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalities);

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

        for (int i = 0; i < 6; i++) {items.add(i);}

        for (int i = 0; i < 4; i++) {
            num = random.nextInt(items.size());
            int num2=items.get(num);
            controlDates[i] = array.check[num2];
            indices.add(num2);
            items.remove(num);
            dates[i].setText(array.data[num2]);
        }
        int k = random.nextInt(indices.size());
        int number = indices.get(k);
        txt.setText(array.text[number]);
        control=array.check[number];

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

                                ArrayList<Integer> indices = new ArrayList<>();
                                ArrayList<Integer> items = new ArrayList<>();

                                for (int i = 0; i < 6; i++) {items.add(i);}

                                for (int i = 0; i < 4; i++) {
                                    num = random.nextInt(items.size());
                                    int num2=items.get(num);
                                    controlDates[i] = array.check[num2];
                                    indices.add(num2);
                                    items.remove(num);
                                    dates[i].setText(array.data[num2]);
                                }
                                int k = random.nextInt(indices.size());
                                int number = indices.get(k);
                                txt.setText(array.text[number]);
                                control=array.check[number];
                                for(int j=0; j < 4; j++){
                                    dates[j].setEnabled(true);
                                }
                                dates[work].setBackgroundColor((getResources().getColor(R.color.yellow_500)));
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
