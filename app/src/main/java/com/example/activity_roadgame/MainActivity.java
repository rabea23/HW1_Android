package com.example.activity_roadgame;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import android.os.Vibrator;
import android.content.Context;
import android.os.VibrationEffect;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class MainActivity extends AppCompatActivity {
    private AppCompatImageView game_IMG_heart1;
    private AppCompatImageView game_IMG_heart2;
    private AppCompatImageView game_IMG_heart3;
    private AppCompatImageView game_IMG_hole00;
    private AppCompatImageView game_IMG_hole10;
    private AppCompatImageView game_IMG_hole20;
    private AppCompatImageView game_IMG_hole30;
    private AppCompatImageView game_IMG_hole40;
    private AppCompatImageView game_IMG_hole01;
    private AppCompatImageView game_IMG_hole11;
    private AppCompatImageView game_IMG_hole21;
    private AppCompatImageView game_IMG_hole31;
    private AppCompatImageView game_IMG_hole41;
    private AppCompatImageView game_IMG_hole02;
    private AppCompatImageView game_IMG_hole12;
    private AppCompatImageView game_IMG_hole22;
    private AppCompatImageView game_IMG_hole32;
    private AppCompatImageView game_IMG_hole42;
    private AppCompatImageView game_IMG_car1;
    private AppCompatImageView game_IMG_car2;
    private AppCompatImageView game_IMG_car3;
    private AppCompatImageView game_IMG_rightArrow;
    private AppCompatImageView game_IMG_leftArrow;
    private AppCompatImageView[][] traps;
    private AppCompatImageView[] carMoving;
    private AppCompatImageView[] hearts;
    private int lives = 3;
    private int carIndex =1 ;
    private int col1Rows = 0;
    private int col2Rows =1;
    private int col3Rows =2;
    private final int rows = 5;
    private final int colums = 3;
    private final int delay = 10;
    private mySignal mysignal;
    private Handler handler = new Handler();




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initView();
        new Handler().postDelayed(new Runnable() {
            @Override

            public void run() {
                movingTrap();
                updateGame();
                if(lives <= 0)
                    return;
                else
                    new Handler().postDelayed(this, 1000);

            }


        }, 1000); // 1000 milliseconds = 1 second


    }

private void updateGame(){

        for(int i = 0 ; i < colums ; i++) {
            // when the car crash the obstacles
            if (traps[rows - 1][i].getVisibility() == View.VISIBLE && carIndex == i) {
                // toast message
                Toast.makeText(this, "you crash with the obstacles", Toast.LENGTH_SHORT).show();

                // vibration
                mySignal.init(this);
                 mysignal.getInstance().vibrate();
                reduceLive();

            }
        }
        // endless game
            if(lives == 0)
                lives=3;

    refreshLivesUI();
    }

// refresh the three hearts
    private void refreshLivesUI() {

        for (int i = 0; i < lives; i++) {
            hearts[i].setVisibility(View.VISIBLE);
        }

        for (int i = lives; i < hearts.length; i++) {
            hearts[i].setVisibility(View.INVISIBLE);
        }
    }
    // moving each trap down one row in the matrix
    private void movingTrap() {


                traps[col1Rows][0].setVisibility(View.INVISIBLE);
                traps[col2Rows][1].setVisibility(View.INVISIBLE);
                traps[col3Rows][2].setVisibility(View.INVISIBLE);
                col1Rows = (col1Rows + 1) % 5;
                col2Rows = (col2Rows + 1) % 5;
                col3Rows = (col3Rows + 1) % 5;
                traps[col1Rows][0].setVisibility(View.VISIBLE);
                traps[col2Rows][1].setVisibility(View.VISIBLE);
                traps[col3Rows][2].setVisibility(View.VISIBLE);

    }



    private void reduceLive() {
        lives--;
    }


    private void initView() {
        game_IMG_leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(carIndex > 0){
                    carMoving[carIndex].setVisibility(view.INVISIBLE);
                    carIndex--;
                    carMoving[carIndex].setVisibility(view.VISIBLE);
                }
            }
        });

        game_IMG_rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(carIndex <2 ){
                    carMoving[carIndex].setVisibility(view.INVISIBLE);
                    carIndex++;
                    carMoving[carIndex].setVisibility(view.VISIBLE);
                }
            }
        });

    }

    private void findViews() {
        game_IMG_heart1 = findViewById(R.id.game_IMG_heart1);
        game_IMG_heart2 = findViewById(R.id.game_IMG_heart2);
        game_IMG_heart3 = findViewById(R.id.game_IMG_heart3);
        game_IMG_hole00 = findViewById(R.id.game_IMG_hole00);
        game_IMG_hole10 = findViewById(R.id.game_IMG_hole10);
        game_IMG_hole20 = findViewById(R.id.game_IMG_hole20);
        game_IMG_hole30 = findViewById(R.id.game_IMG_hole30);
        game_IMG_hole40 = findViewById(R.id.game_IMG_hole40);
        game_IMG_hole01 = findViewById(R.id.game_IMG_hole01);
        game_IMG_hole11 = findViewById(R.id.game_IMG_hole11);
        game_IMG_hole21 = findViewById(R.id.game_IMG_hole21);
        game_IMG_hole31 = findViewById(R.id.game_IMG_hole31);
        game_IMG_hole41 = findViewById(R.id.game_IMG_hole41);
        game_IMG_hole02 = findViewById(R.id.game_IMG_hole02);
        game_IMG_hole12 = findViewById(R.id.game_IMG_hole12);
        game_IMG_hole22 = findViewById(R.id.game_IMG_hole22);
        game_IMG_hole32 = findViewById(R.id.game_IMG_hole32);
        game_IMG_hole42 = findViewById(R.id.game_IMG_hole42);
        game_IMG_car1 = findViewById(R.id.game_IMG_car1);
        game_IMG_car2 = findViewById(R.id.game_IMG_car2);
        game_IMG_car3 = findViewById(R.id.game_IMG_car3);
        game_IMG_rightArrow = findViewById(R.id.game_IMG_rightArrow);
        game_IMG_leftArrow = findViewById(R.id.game_IMG_leftArrow);
        hearts = new AppCompatImageView[]{
                game_IMG_heart1,
                game_IMG_heart2,
                game_IMG_heart3,
        };
        carMoving = new AppCompatImageView[]{game_IMG_car1,game_IMG_car2,game_IMG_car3};
        // init game with a car in a middle lane road
        carMoving[0].setVisibility(View.INVISIBLE);
        carMoving[2].setVisibility(View.INVISIBLE);
        traps = new AppCompatImageView[][]{{game_IMG_hole00,game_IMG_hole01,game_IMG_hole02},{game_IMG_hole10,game_IMG_hole11,game_IMG_hole12}
        ,{game_IMG_hole20,game_IMG_hole21,game_IMG_hole22},{game_IMG_hole30,game_IMG_hole31,game_IMG_hole32},{game_IMG_hole40,game_IMG_hole41,game_IMG_hole42}};

        // init the game with three traps
        for (int i = 0 ; i <rows ; i++ ){

            for (int j = 0 ; j < colums ; j++){

                if((i==0 && j==0) || (i==1 && j==1) ||(i==2 && j==2) )
                    traps[i][j].setVisibility(View.VISIBLE);
                else
                    traps[i][j].setVisibility(View.INVISIBLE);

            }
        }
    }
}