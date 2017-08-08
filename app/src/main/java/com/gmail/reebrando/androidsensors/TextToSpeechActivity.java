package com.gmail.reebrando.androidsensors;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{


    private TextToSpeech tts;
    private EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        tts = new TextToSpeech(this, this);
        etText = (EditText) findViewById(R.id.etText);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale localeBR = new Locale("en", "uk");
            int result = tts.setLanguage(localeBR);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speak();
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    public void speak(View view) {
        speak();
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


    private void speak() {
        String text = etText.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}
