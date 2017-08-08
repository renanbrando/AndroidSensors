package com.gmail.reebrando.androidsensors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAcelerometer;
    private Button btnTTS;
    private Button btnSTT;
    private Button btnSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcelerometer = (Button) findViewById(R.id.btnAcelerometer);
        btnTTS = (Button) findViewById(R.id.btnTextToSpeech);
        btnSTT = (Button) findViewById(R.id.btnSpeechToText);
        btnSensors = (Button) findViewById(R.id.btnSensors);

        btnAcelerometer.setOnClickListener(this);
        btnTTS.setOnClickListener(this);
        btnSTT.setOnClickListener(this);
        btnSensors.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAcelerometer:
                startActivity(new Intent(MainActivity.this, AcelerometerActivity.class));
                break;
            case R.id.btnTextToSpeech:
                startActivity(new Intent(MainActivity.this, TextToSpeechActivity.class));
                break;
            case R.id.btnSpeechToText:
                startActivity(new Intent(MainActivity.this, SpeechToTextActivity.class));
                break;
            case R.id.btnSensors:
                Toast.makeText(this, "Not yet, your fool.", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(MainActivity.this, AcelerometerActivity.class));
                break;
        }
    }
}
