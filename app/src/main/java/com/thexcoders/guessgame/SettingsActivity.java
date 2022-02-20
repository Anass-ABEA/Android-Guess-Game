package com.thexcoders.guessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.thexcoders.guessgame.model.Guess;

public class SettingsActivity extends AppCompatActivity {

    private EditText editMax, editMin;
    private EditText editCold, editWarm, editHot, editSoHot, editSuperHot;

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();

        fillEditViews();

        clickListeners();
    }

    private void clickListeners() {
        btnSave.setOnClickListener(v -> {
            Guess.MIN = getIntegerValue(editMin);
            Guess.MAX = getIntegerValue(editMax);

            Guess.LITTLE_COLD = getIntegerValue(editCold);
            Guess.WARM = getIntegerValue(editWarm);
            Guess.HOT = getIntegerValue(editHot);
            Guess.SO_HOT = getIntegerValue(editSoHot);
            Guess.SUPER_HOT = getIntegerValue(editSuperHot);
            onBackPressed();
        });
    }

    private int getIntegerValue(EditText editText) {
        try {
            return Integer.parseInt(editText.getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    @SuppressLint("DefaultLocale")
    private void fillEditViews() {
        editCold.setText(String.format("%d", Guess.LITTLE_COLD));
        editWarm.setText(String.format("%d", Guess.WARM));
        editHot.setText(String.format("%d", Guess.HOT));
        editSoHot.setText(String.format("%d", Guess.SO_HOT));
        editSuperHot.setText(String.format("%d", Guess.SUPER_HOT));

        editMax.setText(String.format("%d", Guess.MAX));
        editMin.setText(String.format("%d", Guess.MIN));


    }

    private void initViews() {
        editMax = findViewById(R.id.edit_max_guess);
        editMin = findViewById(R.id.edit_min_guess);

        editCold = findViewById(R.id.edit_little_cold);
        editWarm = findViewById(R.id.edit_warm);
        editHot = findViewById(R.id.edit_hot);
        editSoHot = findViewById(R.id.edit_so_hot);
        editSuperHot = findViewById(R.id.edit_super_hot);

        btnSave = findViewById(R.id.btn_save);
    }
}