package com.example.mwars.brailleapp;

import android.app.Activity;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity{

    private static final String TAG = "MAIN ACTIVITY";

    private TextToSpeech tts;

    public Button bttn1, bttn2, bttn3, bttn4, bttn5, bttn6;
    public RadioButton rbttn1, rbttn2, rbttn3, rbttn4, rbttn5, rbttn6;
    public TextView txt1;
    public Map<Integer, String> keyMap = new HashMap<Integer, String>();
    public StringBuilder sbKey = new StringBuilder();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
        initKeyMap();
//        for(String s : keyMap.values()){
//            Log.d(TAG, s);
//        }


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


    private void initKeyMap(){
        keyMap.put(1, "a");
        keyMap.put(2, ",");
        keyMap.put(3, ".");
        keyMap.put(12, "b");
        keyMap.put(13, "k");
        keyMap.put(14, "c");
        keyMap.put(15, "e");
        keyMap.put(16, "ą");
        keyMap.put(23, ";");
        keyMap.put(24, "i");
        keyMap.put(25, ":");
        keyMap.put(26, "?");
        keyMap.put(35, "*");
        keyMap.put(36, "-");
        keyMap.put(46, "shift");
        keyMap.put(123, "l");
        keyMap.put(124, "f");
        keyMap.put(125, "h");
        keyMap.put(126, "ł");
        keyMap.put(134, "m");
        keyMap.put(135, "o");
        keyMap.put(136, "u");
        keyMap.put(145, "d");
        keyMap.put(146, "ć");
        keyMap.put(156, "ę");
        keyMap.put(234, "s");
        keyMap.put(235, "!");
        keyMap.put(245, "j");
        keyMap.put(246, "ś");
        keyMap.put(256, "*");
        keyMap.put(236, "\"");
        keyMap.put(345, "@");
        keyMap.put(346, "ó");
        keyMap.put(456, "ctrl+i");
        keyMap.put(1234, "p");
        keyMap.put(1235, "r");
        keyMap.put(1236, "v");
        keyMap.put(1245, "g");
        keyMap.put(1256, ":");
        keyMap.put(1345, "n");
        keyMap.put(1346, "x");
        keyMap.put(1356, "z");
        keyMap.put(1456, "ń");
        keyMap.put(2345, "t");
        keyMap.put(2346, "ź");
        keyMap.put(2356, "(");
        keyMap.put(2356, ")");
        keyMap.put(2456, "w");
        keyMap.put(3456, "ZNAK LICZBY");
//        keyMap.put(, "to jest to liczebnik porzadkowy");
        keyMap.put(12345, "q");
        keyMap.put(12346, "ż");
        keyMap.put(13456, "y");

    }


    public void onClick(View v){
        final Button b = (Button) v;
        int bttnNr = Integer.parseInt(b.getText().toString());

        switch (bttnNr) {
            case 1:
                rbttn1.setChecked(true); sbKey.append("1"); break;
            case 2:
                rbttn2.setChecked(true); sbKey.append("2"); break;
            case 3:
                rbttn3.setChecked(true); sbKey.append("3"); break;
            case 4:
                rbttn4.setChecked(true); sbKey.append("4"); break;
            case 5:
                rbttn5.setChecked(true); sbKey.append("5"); break;
            case 6:
                rbttn6.setChecked(true); sbKey.append("6"); break;
        }

        new CountDownTimer(1350,10){
            @Override
            public void onTick(long l) {
                txt1.setText("SEC:" + l);
            }

            @Override
            public void onFinish() {
                char [] c = sbKey.toString().toCharArray();
                Arrays.sort(c);
                String temp = new String(c);
                Log.d("SB KEY ", sbKey.toString());
//                Log.d("KEYMAP ", Boolean.toString(keyMap.containsKey(Integer.parseInt(sbKey.toString()))));
                try {
                    addSign(temp);
                    speech(temp);
                    clear();
                }
                catch (Exception e)
                {
                    Log.d("EXCEPTION ", e.toString());
                    clear();
                    return;
                }

//                if(keyMap.containsKey(Integer.parseInt(sbKey.toString())) == false){
//                    clear();
//                    return;
//                }
//                else{
//                    addSign(sbKey.toString());
//                    speech(sbKey.toString());
//                }
//                clear();
            }
        }.start();
    }


    public void speech(String sign){
        final String tempSign = keyMap.get(Integer.parseInt(sign)).toString();
        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.getDefault());
//                    tts.setLanguage(Locale.UK);
                }
                tts.speak(tempSign, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }


    public void addSign(String signKey){
        Log.d(TAG, signKey);
        return;
    }



    private void clear(){
        rbttn1.setChecked(false);
        rbttn2.setChecked(false);
        rbttn3.setChecked(false);
        rbttn4.setChecked(false);
        rbttn5.setChecked(false);
        rbttn6.setChecked(false);
        sbKey.setLength(0);
        txt1.setText("");
    }



    public void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
