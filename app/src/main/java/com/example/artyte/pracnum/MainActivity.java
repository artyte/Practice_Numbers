package com.example.artyte.pracnum;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends Activity {

    TextToSpeech tts;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    protected void listen(View v) {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.JAPAN);
                }
            }
        });


        t1 = (TextView) findViewById(R.id.text);
        String toSpeak = t1.getText().toString();
        tts.speak("hello", TextToSpeech.QUEUE_ADD, null, "speak");
    }

    protected void newNum(View v) {
        int min = 0;
        int max = 100000000;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.JAPAN);
                }
            }
        });

        t1 = (TextView) findViewById(R.id.text);
        t1.setText(Integer.toString(randomNum));
        tts.speak(Integer.toString(randomNum), TextToSpeech.QUEUE_ADD, null, "speak");
    }

    public void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
