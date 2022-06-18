package com.example.gamesuite;

import static android.text.InputType.TYPE_CLASS_TEXT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class WordleActivity extends AppCompatActivity {
    public static TextView[][] wordleBoxArray = new TextView[6][5];

    //static char[][] letters = new char[6][5];

    static int currWord = 0;
    static int currLetter = 0;
    static String[] quotes = new String[] {"place", "words", "right", "heere"};
    static String word;
    //static String word = quotes[(int) (Math.random() * quotes.length)];
    static boolean gameOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordle);
        FrameLayout layout2 = findViewById(R.id.layout);

        //EditText editText = findViewById(R.id.editText);
        KeyboardClass keyboard = findViewById(R.id.keyboard);
        //editText.setRawInputType(TYPE_CLASS_TEXT);
        //editText.setTextIsSelectable(true);
        //InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        //keyboard.setInputConnection(null);

        initialiazeArray();
        ImageButton btn = findViewById(R.id.BackGame);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            restart();
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(WordleActivity.this, MainActivity2.class);

            startActivity(intent);
        });

        ImageButton info = findViewById(R.id.infoWordle);
        info.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Info Button works!");
            showInstructions();
        });
    }

    void showInstructions() {
        Dialog instrWordle = new Dialog(WordleActivity.this);
        //Have already added custom title in layout. So disable the default title
        instrWordle.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Can cancel the dialog by clicking anywhere outside the dialog
        instrWordle.setCancelable(true);
        //Set layout of dialog
        instrWordle.setContentView(R.layout.instr_wordle);

        ImageButton close = instrWordle.findViewById(R.id.close);
        close.setOnClickListener(v -> instrWordle.dismiss());

        instrWordle.show();
    }

    public static void submitWord() {
        if (currLetter != 5) {
            System.out.println("Incomplete Word");
        } else if (currWord < 6) {
            int counter = 0;
            for (int i = 0; i < 5; i++) {
                char c = wordleBoxArray[currWord][i].getText().toString().toLowerCase(Locale.ROOT).charAt(0);
                if (c == word.charAt(i)) {
                    wordleBoxArray[currWord][i].setBackgroundColor(Color.GREEN);
                    //wordleBoxArray[currWord][i].setBackgroundResource(R.drawable.wordleboxes);
                    counter++;
                } else if (word.indexOf(c) >= 0) {
                    wordleBoxArray[currWord][i].setBackgroundColor(Color.YELLOW);
                } else {
                    wordleBoxArray[currWord][i].setBackgroundColor(Color.RED);
                }
            }
            if (counter == 5) {
                System.out.println("Congratulations! You guessed the word!");
                gameOn = false;
            }
            currWord++;
            currLetter = 0;
            if (currWord == 6) {
                System.out.println("Game Over! The word was " + word);
                gameOn = false;
            }
        }

    }

    public static void type(String c) {
        if (currLetter <= 4) {
            wordleBoxArray[currWord][currLetter].setText(c.toUpperCase(Locale.ROOT));
            System.out.println("You pressed " + c);
            currLetter++;
        }
    }

    public static void backspace() {
        System.out.println("You pressed backspace");
        if (currLetter > 0) {
            wordleBoxArray[currWord][currLetter - 1].setText(" ");
            currLetter--;
        }
    }

    public static void restart() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                wordleBoxArray[i][j].setText(" ");
                wordleBoxArray[i][j].setBackgroundColor(Color.WHITE);
            }
        }
        gameOn = true;
        currWord = 0;
        currLetter = 0;
        word = quotes[(int) (Math.random() * quotes.length)];
        System.out.println(word);
    }

    void initialiazeArray() {
//        GridLayout board = findViewById(R.id.wordleBoard);
//        for (int row = 0; row < 6; row++) {
//            for (int column = 0; column < 5; column++){
//                TextView box = new TextView(this);
//                box.setId(row * 10 + column);
//                box.setBackgroundResource(R.drawable.wordleboxes);
//                box.setHeight(( int) (40 * Resources.getSystem().getDisplayMetrics().density));
//                box.setWidth(( int) (35 * Resources.getSystem().getDisplayMetrics().density));
//                box.setText(" ");
//                board.addView(box);
//                wordleBoxArray[row][column] = box;
//            }
//        }
        wordleBoxArray[0][0] = findViewById(R.id.textView00);
        wordleBoxArray[0][1] = findViewById(R.id.textView01);
        wordleBoxArray[0][2] = findViewById(R.id.textView02);
        wordleBoxArray[0][3] = findViewById(R.id.textView03);
        wordleBoxArray[0][4] = findViewById(R.id.textView04);
        wordleBoxArray[1][0] = findViewById(R.id.textView10);
        wordleBoxArray[1][1] = findViewById(R.id.textView11);
        wordleBoxArray[1][2] = findViewById(R.id.textView12);
        wordleBoxArray[1][3] = findViewById(R.id.textView13);
        wordleBoxArray[1][4] = findViewById(R.id.textView14);
        wordleBoxArray[2][0] = findViewById(R.id.textView20);
        wordleBoxArray[2][1] = findViewById(R.id.textView21);
        wordleBoxArray[2][2] = findViewById(R.id.textView22);
        wordleBoxArray[2][3] = findViewById(R.id.textView23);
        wordleBoxArray[2][4] = findViewById(R.id.textView24);
        wordleBoxArray[3][0] = findViewById(R.id.textView30);
        wordleBoxArray[3][1] = findViewById(R.id.textView31);
        wordleBoxArray[3][2] = findViewById(R.id.textView32);
        wordleBoxArray[3][3] = findViewById(R.id.textView33);
        wordleBoxArray[3][4] = findViewById(R.id.textView34);
        wordleBoxArray[4][0] = findViewById(R.id.textView40);
        wordleBoxArray[4][1] = findViewById(R.id.textView41);
        wordleBoxArray[4][2] = findViewById(R.id.textView42);
        wordleBoxArray[4][3] = findViewById(R.id.textView43);
        wordleBoxArray[4][4] = findViewById(R.id.textView44);
        wordleBoxArray[5][0] = findViewById(R.id.textView50);
        wordleBoxArray[5][1] = findViewById(R.id.textView51);
        wordleBoxArray[5][2] = findViewById(R.id.textView52);
        wordleBoxArray[5][3] = findViewById(R.id.textView53);
        wordleBoxArray[5][4] = findViewById(R.id.textView54);
    }
}