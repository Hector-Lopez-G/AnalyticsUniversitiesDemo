package com.hector.analyticsuniversitiesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huawei.hms.analytics.HiAnalytics;
import com.huawei.hms.analytics.HiAnalyticsInstance;
import com.huawei.hms.analytics.HiAnalyticsTools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText editTextName, editTextScore;
    Button buttonName, buttonScore;

    HiAnalyticsInstance instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextScore = findViewById(R.id.editTextScore);
        buttonName = findViewById(R.id.buttonName);
        buttonScore = findViewById(R.id.buttonScore);

        reportNameEvt("Test");

        // Enable Analytics Kit Log
        HiAnalyticsTools.enableLog();
        // Generate the Analytics Instance
        instance = HiAnalytics.getInstance(this);

        buttonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                reportNameEvt(name);
            }
        });

        buttonScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scoreString = editTextScore.getText().toString();
                int score = Integer.parseInt(scoreString);
                reportScoreEvt(score);
            }
        });
    }

    private void reportNameEvt(String name) {
        // Initialize parameters.
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        bundle.putString("answerTime",sdf.format(new Date()));

        // Report a custom event.
        instance.onEvent("Name", bundle);
    }

    private void reportScoreEvt(int score) {
        // Initialize parameters.
        Bundle bundle = new Bundle();
        bundle.putInt("score", score);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        bundle.putString("answerTime",sdf.format(new Date()));

        // Report a custom event.
        instance.onEvent("Score", bundle);
    }
}