package com.example.mwars.brailleapp;

import android.app.Activity;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity{

    private static final String TAG = "MAIN ACTIVITY";

    private TextToSpeech tts;

    public Button bttn1, bttn2, bttn3, bttn4, bttn5, bttn6;
    public RadioButton rbttn1, rbttn2, rbttn3, rbttn4, rbttn5, rbttn6;
    public TextView txt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
    }


    private void initControls(){
        bttn1 = (Button) findViewById(R.id.button1);
        bttn2 = (Button) findViewById(R.id.button2);
        bttn3 = (Button) findViewById(R.id.button3);
        bttn4 = (Button) findViewById(R.id.button4);
        bttn5 = (Button) findViewById(R.id.button5);
        bttn6 = (Button) findViewById(R.id.button6);

        rbttn1 = (RadioButton) findViewById(R.id.radioButton1);
        rbttn2 = (RadioButton) findViewById(R.id.radioButton2);
        rbttn3 = (RadioButton) findViewById(R.id.radioButton3);
        rbttn4 = (RadioButton) findViewById(R.id.radioButton4);
        rbttn5 = (RadioButton) findViewById(R.id.radioButton5);
        rbttn6 = (RadioButton) findViewById(R.id.radioButton6);

        txt1 = (TextView) findViewById(R.id.textView);

    }


    public void onClick(View v){
        Button b = (Button) v;

        int bttnNr = Integer.parseInt(b.getText().toString());

        switch (bttnNr) {
            case 1:
                rbttn1.setChecked(true); break;
            case 2:
                rbttn2.setChecked(true); break;
            case 3:
                rbttn3.setChecked(true); break;
            case 4:
                rbttn4.setChecked(true); break;
            case 5:
                rbttn5.setChecked(true); break;
            case 6:
                rbttn6.setChecked(true); break;
        }

        new CountDownTimer(1000,1000){
            String signKey = "135";
            @Override
            public void onTick(long l) {
                txt1.setText("SEC:" + l);
            }

            @Override
            public void onFinish() {
                addSign(signKey);
                clearChoices();
            }
        }.start();

//        String toSpeak = "Null";

//
//        Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_LONG).show();
//
//        final String finalToSpeak = toSpeak;
//
//        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status != TextToSpeech.ERROR) {
//                    tts.setLanguage(Locale.UK);
//                }
//                tts.speak(finalToSpeak, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });
    }

    public void addSign(String signKey){
        return;
    }


    private void clearChoices(){
        rbttn1.setChecked(false);
        rbttn2.setChecked(false);
        rbttn3.setChecked(false);
        rbttn4.setChecked(false);
        rbttn5.setChecked(false);
        rbttn6.setChecked(false);
    }


    public void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
