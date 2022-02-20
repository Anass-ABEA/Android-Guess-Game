package com.thexcoders.guessgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thexcoders.guessgame.model.Guess;
import com.thexcoders.guessgame.model.GuessFeedback;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private Button btnCheck;
    private Button btnClear;
    private Button btnErase;

    private ImageView imageView;
    private TextView userInput;
    private Guess guessGame;

    private ImageButton btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setClickListeners();
    }

    private void setClickListeners() {
        appendValue(btn0, 0);
        appendValue(btn1, 1);
        appendValue(btn2, 2);
        appendValue(btn3, 3);
        appendValue(btn4, 4);
        appendValue(btn5, 5);
        appendValue(btn6, 6);
        appendValue(btn7, 7);
        appendValue(btn8, 8);
        appendValue(btn9, 9);

        btnClear.setOnClickListener(v -> {
            userInput.setText("");
        });

        btnErase.setOnClickListener(v -> {
            String value = userInput.getText().toString();
            if (value.length() == 0) return;
            value = value.substring(0, value.length() - 1);
            userInput.setText(value);
        });

        btnCheck.setOnClickListener(v -> {
            String input = userInput.getText().toString();
            if (input.length() == 0) return;
            GuessFeedback feedback = guessGame.getFeedback(Integer.parseInt(input));
            imageView.setImageResource(feedback.getResId());
            if (feedback.equals(GuessFeedback.FOUND)) {
                showSuccessDialog();
            } else {
                Toast.makeText(this, String.format("%s for %s", feedback.getMessage(), input), Toast.LENGTH_SHORT).show();
            }
            userInput.setText("");
        });

        btnSettings.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(String.format("You made the right guess after %d tries!", guessGame.getTries()))
                .setTitle("You guessed it!")
                .setIcon(R.drawable.ic_well_done)
                .setCancelable(true)
                .setPositiveButton("Replay", (d, i) -> {
                    replayGame();
                });
        builder.create().show();
    }

    private void replayGame() {
        guessGame.regenerate();
        imageView.setImageDrawable(null);
    }


    private void appendValue(Button btn, int i) {
        btn.setOnClickListener(v -> {
            String value = userInput.getText().toString();
            if (value.length() == 0) {
                value = "0";
            }
            userInput.setText(String.valueOf(Integer.parseInt(value) * 10 + i));
        });
    }

    private void initViews() {
        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn0 = findViewById(R.id.btn_0);
        btnClear = findViewById(R.id.btn_clear);
        btnErase = findViewById(R.id.btn_erase);
        btnCheck = findViewById(R.id.btn_check);

        userInput = findViewById(R.id.user_input);
        imageView = findViewById(R.id.image_result);

        btnSettings = findViewById(R.id.btn_settings);

        guessGame = new Guess();
        Log.e("VALUE", "" + guessGame.getNumber());

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Refreshed the number", Toast.LENGTH_SHORT).show();
        replayGame();
    }
}