package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button[][] buttonArray = new Button[3][3];
    TextView status, player1_score, player2_score;
    Button playAgain, resetGame;
    GameStatus gameStatus;
    enum GameStatus{
        X_TURN,
        O_TRUN,
        X_WON,
        O_WON,
        DRAW
    }
    int player1ScoreCount = 0;
    int player2ScoreCount = 0;
    char[][] values = {{'0','0','0'},
                        {'0','0','0'},
                        {'0','0','0'}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        gameStatus = GameStatus.X_TURN;
        status.setText("X Turn");
        int c = 0;

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i<3; i++){
                    for(int j = 0; j<3;j++){
                        values[i][j]='0';
                        buttonArray[i][j].setText("");
                        gameStatus = GameStatus.X_TURN;
                        status.setText("X Turn");
                    }
                }
            }
        });
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i<3; i++){
                    for(int j = 0; j<3;j++){
                        values[i][j]='0';
                        buttonArray[i][j].setText("");
                        gameStatus = GameStatus.X_TURN;
                        status.setText("X Turn");
                    }
                }
                player1ScoreCount =0;
                player2ScoreCount =0;
                player1_score.setText("0");
                player2_score.setText("0");
            }
        });
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3;j++){
                int finalC = c;
                int finalI = i;
                int finalJ = j;
                buttonArray[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(values[finalI][finalJ] == '0'){
                            switch (gameStatus){
                                case X_TURN:{
                                    values[finalI][finalJ] = 'x';
                                    buttonArray[finalI][finalJ].setText("X");
                                    char winner = checkWinner();
                                    if(winner == 'x'){
                                        gameStatus = GameStatus.X_WON;
                                        status.setText("X Won The Game!");
                                        player1ScoreCount ++;
                                        player1_score.setText(String.valueOf(player1ScoreCount));
                                        break;
                                    }
                                    else{
                                        if(checkDraw()){
                                            gameStatus = GameStatus.DRAW;
                                            status.setText("Draw");
                                            break;
                                        }
                                        else{
                                            gameStatus = GameStatus.O_TRUN;
                                            status.setText("O Turn");
                                            break;
                                        }
                                    }
                                }
                                case O_TRUN:{
                                    values[finalI][finalJ] = 'o';
                                    buttonArray[finalI][finalJ].setText("O");
                                    char winner = checkWinner();
                                    if(winner == 'o'){
                                        gameStatus = GameStatus.O_WON;
                                        status.setText("O Won The Game!");
                                        player2ScoreCount ++;
                                        player2_score.setText(String.valueOf(player2ScoreCount));
                                        break;
                                    }
                                    else{
                                        if(checkDraw()){
                                            gameStatus = GameStatus.DRAW;
                                            status.setText("Draw");
                                            break;
                                        }
                                        else{
                                            gameStatus = GameStatus.X_TURN;
                                            status.setText("X Turn");
                                            break;
                                        }
                                    }
                                }
                                default:
                                    break;
                            }
                        }
                        else{

                        }
                    }
                });
                c++;
            }
        }
    }
    private char checkWinner(){
//        if ((values[0][0]==values[0][1] && values[0][0]==values[0][2])
//        || (values[1][0]==values[1][1] && values[1][0]==values[1][2])
//        ||(values[2][0]==values[2][1] && values[2][0]==values[2][2])
//        ||(values[0][0]==values[1][0] && values[0][0]==values[2][0])
//        ||(values[0][1]==values[1][1] && values[0][1]==values[2][1])
//        ||(values[0][2]==values[1][2] && values[0][2]==values[2][2])
//        ||(values[0][0]==values[1][1] && values[0][0]==values[2][2])
//        ||(values[0][2]==values[1][2] && values[0][2]==values[2][0])){
//
//        }

        for(int i=0;i<3;i++){
            if(values[i][0]== values[i][1] && values[i][0]==values[i][2]){
                if(values[i][0]=='x')
                    return 'x';
                else if(values[i][0]=='o')
                    return 'o';
                else
                    return 0;
            }
            if(values[0][i]== values[1][i] &&values[0][i] ==values[2][i]){
                if (values[0][i]=='x')
                    return 'x';
                else if(values[0][i]=='o')
                    return 'o';
                else
                    return 0;
            }
        }
        if ((values[0][0]==values[1][1] && values[0][0]==values[2][2])){
            if (values[0][0]=='x')
                return 'x';
            else if(values[0][0]=='o')
                return 'o';
            else
                return 0;
        }
        if ((values[0][2]==values[1][1] && values[0][2]==values[2][0])){
            if (values[0][2]=='x')
                return 'x';
            else if(values[0][2]=='o')
                return 'o';
            else
                return 0;
        }
        return 0;
    }

    private boolean checkDraw(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3;j++){
                if(values[i][j]=='0')
                    return false;
            }
        }
        return true;
    }

    private void findViews(){
        buttonArray[0][0] = findViewById(R.id.btn_11);
        buttonArray[0][1] = findViewById(R.id.btn_12);
        buttonArray[0][2] = findViewById(R.id.btn_13);

        buttonArray[1][0] = findViewById(R.id.btn_21);
        buttonArray[1][1] = findViewById(R.id.btn_22);
        buttonArray[1][2] = findViewById(R.id.btn_23);

        buttonArray[2][0] = findViewById(R.id.btn_31);
        buttonArray[2][1] = findViewById(R.id.btn_32);
        buttonArray[2][2] = findViewById(R.id.btn_33);

        status = findViewById(R.id.game_status);
        player1_score = findViewById(R.id.player_1_score);
        player2_score = findViewById(R.id.player_2_score);

        playAgain = findViewById(R.id.play_again_btn);
        resetGame = findViewById(R.id.reset_game_btn);
    }
}