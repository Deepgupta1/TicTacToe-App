package com.example.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    TextView txt;
    int player_0=0;
    int player_X=1;
    int activePlayer=player_0;
    int[] filledpos={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean isgameactive= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        txt=findViewById(R.id.headertext);


        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if(!isgameactive) return;
        Button clicked=findViewById(v.getId());
        int clickedTag=Integer.parseInt(v.getTag().toString());

        if(filledpos[clickedTag]!=-1){
            return;
        }
        filledpos[clickedTag]=activePlayer;

        if(activePlayer==player_0){
            clicked.setText("0");
            activePlayer=player_X;
           // clicked.setBackground(getDrawable(android.R.color.holo_blue_bright));
            txt.setText("X turn");
        }
        else {
            clicked.setText("X");
            activePlayer=player_0;
           // clicked.setBackground(getDrawable(android.R.color.holo_orange_light));

            txt.setText("0 turn");

        }

        checkForWin();



    }
    private void checkForWin(){
        int[][] winpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int i=0;i<8;i++){
            int val0=winpos[i][0];
            int val1=winpos[i][1];
            int val2=winpos[i][2];
            if(filledpos[val0]==filledpos[val1] && filledpos[val1]==filledpos[val2]){
                if(filledpos[val0] != -1){
                    isgameactive=false;
                    if(filledpos[val0]== player_0) {
                       showDialog("0 is winner");

                    }
                    else{
                        showDialog("X is winner");
                    }
                }
            }
        }

    }
    private void showDialog(String winnerText){
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartgame();

                    }
                })
                .show();

    }
    private void restartgame(){
        activePlayer=player_0;
        filledpos= new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        isgameactive= true;

    }
}