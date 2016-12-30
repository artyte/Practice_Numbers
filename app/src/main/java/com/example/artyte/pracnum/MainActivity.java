package com.example.artyte.pracnum;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends Activity {

    TextToSpeech tts;
    TextView t1;
    static Locale loc = Locale.JAPAN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(loc);

                    t1 = (TextView) findViewById(R.id.text);
                    String toSpeak = t1.getText().toString();
                    tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, "speak");
                }
            }
        });

    }

    protected void listen(View v) {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(loc);

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
                    tts.setLanguage(loc);

                    int min = 0;
                    int max = 0;
                    int choice = v.getId();

                    switch(choice) {
                        case R.id.r0t10:
                            min = 0;
                            max = 10;
                            break;
                        case R.id.r11t100:
                            min = 11;
                            max = 100;
                            break;
                        case R.id.r101t10k:
                            min = 101;
                            max = 10000;
                            break;
                        case R.id.r10_1kt100m:
                            min = 10001;
                            max = 100000000;
                            break;
                    }

                    int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

                    t1 = (TextView) findViewById(R.id.text);
                    t1.setText(Integer.toString(randomNum));
                    tts.speak(Integer.toString(randomNum), TextToSpeech.QUEUE_FLUSH, null, "speak");
                }
            }
        });
    }

    protected void langChoose(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_language)
                .setItems(R.array.lang_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        t1 = (TextView) findViewById(R.id.voice_lang_text);
                        switch(which) {
                            case 0:
                                loc = Locale.ENGLISH;
                                t1.setText("English Voice");
                                break;
                            case 1:
                                loc = Locale.CHINESE;
                                t1.setText("Chinese Voice");
                                break;
                            case 2:
                                loc = Locale.JAPANESE;
                                t1.setText("Japanese Voice");
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    public void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
