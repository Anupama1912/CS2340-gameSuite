package com.example.gamesuite;

import static android.text.InputType.TYPE_CLASS_TEXT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WordleActivity extends AppCompatActivity {
    public static TextView[][] wordleBoxArray = new TextView[6][5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordle);
        FrameLayout layout2 = findViewById(R.id.layout);

        EditText editText = findViewById(R.id.editText);
        KeyboardClass keyboard = findViewById(R.id.keyboard);
        editText.setRawInputType(TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

        initialiazeArray();
        ImageButton btn = findViewById(R.id.BackGame);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
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

     void initialiazeArray() {
        GridLayout board = findViewById(R.id.wordleBoard);
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 5; column++){
                TextView box = new TextView(this);
                box.setId(row * 10 + column);
                box.setBackgroundResource(R.drawable.wordleboxes);
                box.setHeight(( int) (40 * Resources.getSystem().getDisplayMetrics().density));
                box.setWidth(( int) (35 * Resources.getSystem().getDisplayMetrics().density));
                board.addView(box);
                wordleBoxArray[row][column] = box;
            }
        }
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
}