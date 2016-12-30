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
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.JAPAN);

                    t1 = (TextView) findViewById(R.id.text);
                    String toSpeak = t1.getText().toString();
                    tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, "speak");
                }
            }
        });
    }

    protected void newNum(final View v) {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.JAPAN);

                    int min = 0;
                    int max = 0;
                    int choice = v.getId();

                    switch(choice) {
                        case R.id.r0t10: min = 0; max = 10; break;
                        case R.id.r11t100: min = 11; max = 100; break;
                        case R.id.r101t10k: min = 101; max = 10000; break;
                        case R.id.r10_1kt100m: min = 10001; max = 100000000; break;
                    }

                    int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

                    t1 = (TextView) findViewById(R.id.text);
                    t1.setText(Integer.toString(randomNum));
                    tts.speak(Integer.toString(randomNum), TextToSpeech.QUEUE_FLUSH, null, "speak");
                }
            }
        });
    }

    public void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
