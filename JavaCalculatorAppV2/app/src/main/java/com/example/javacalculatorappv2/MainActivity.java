package com.example.taschenrechner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.WindowCallbackWrapper;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    TextView tv = null;
    TextView tvAus = null;
    String[]zeichen = null;
    String[]numbers = null;
    double ergebnis;
    int count;
    String text = "";
    String anzeigeText = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.Zahlen);
        tvAus = findViewById(R.id.textAnzeige);
        zeichen = new String[10];
        numbers = new String[10];
        for (int i = 0; i < 10;i++) {
            zeichen[i] = "";
            numbers[i] = "";
        }
    }
    public void click0(View v){
        Log.d(this.getClass().getName(),  "0");
        String click00 = tv.getText().toString();
        click00 += 0;
        tv.setText(click00);
    }
    public void click1(View v){
        Log.d(this.getClass().getName(),  "1");
        String text = tv.getText().toString();
        text += 1;
        tv.setText(text);
    }
    public void click2(View v){
        Log.d(this.getClass().getName(),  "2");
        String text = tv.getText().toString();
        text += 2;
        tv.setText(text);
    }
    public void click3(View v){
        Log.d(this.getClass().getName(),  "3");
        String text = tv.getText().toString();
        text += 3;
        tv.setText(text);
    }
    public void click4(View v){
        Log.d(this.getClass().getName(),  "4");
        String text = tv.getText().toString();
        text += 4;
        tv.setText(text);
    }
    public void click5(View v){
        Log.d(this.getClass().getName(),  "5");
        String text = tv.getText().toString();
        text += 5;
        tv.setText(text);
    }
    public void click6(View v){
        Log.d(this.getClass().getName(),  "6");
        String text = tv.getText().toString();
        text += 6;
        tv.setText(text);
    }
    public void click7(View v){
        Log.d(this.getClass().getName(),  "7");
        String text = tv.getText().toString();
        text += 7;
        tv.setText(text);
    }
    public void click8(View v){
        Log.d(this.getClass().getName(),  "8");
        String text = tv.getText().toString();
        text += 8;
        tv.setText(text);
    }
    public void click9(View v){
        Log.d(this.getClass().getName(),  "9");
        String text = tv.getText().toString();
        text += 9;
        tv.setText(text);
    }
    public void clickC(View v){
        Log.d(this.getClass().getName(),  "leer");
        tv.setText("");
        tvAus.setText("");
        anzeigeText = "";
        text = "";
        zeichen = new String[10];
        numbers = new String[10];
        for (int i = 0; i < 10;i++) {
            zeichen[i] = "";
            numbers[i] = "";
        }
        double ergebnis= 0;
    }
    public void clickCE(View v){
        Log.d(this.getClass().getName(),  "leer");
        String text = tv.getText().toString();
        int b = text.length();
        CharSequence a = text.subSequence(0, b-1);
        text =  a.toString();
        tv.setText(text);
    }
    public void clickPlus(View v){
        Log.d(this.getClass().getName(),  "addiert");
        text = tv.getText().toString();
        int time = 1;
        for(int i = 0; i< time;i++){
            if(numbers[i] == "" && zeichen[i] == ""){
                numbers[i] = text;
                zeichen[i] = "+";
                anzeigeText += text;
                anzeigeText += "+";
            }else {
                time += 1;
            }
        }
        tv.setText("");
        tvAus.setText(anzeigeText);
        text = "";
    }
    public void clickMinus(View v){
        Log.d(this.getClass().getName(),  "minus");
        text = tv.getText().toString();
        int time = 1;
        for(int i = 0; i< time;i++){
            if(numbers[i] == "" && zeichen[i] == ""){
                numbers[i] = text;
                zeichen[i] = "-";
                anzeigeText += text;
                anzeigeText += "-";
            }else {
                time += 1;
            }
        }
        tv.setText("");
        tvAus.setText(anzeigeText);
        text = "";
    }
    public void clickMulti(View v){
        Log.d(this.getClass().getName(),  "multipliziert");
        String text = tv.getText().toString();
        int time = 1;
        for(int i = 0; i< time;i++){
            if(numbers[i] == "" && zeichen[i] == ""){
                numbers[i] = text;
                zeichen[i] = "x";
                anzeigeText += text;
                anzeigeText += "x";
            }else{
                time += 1;
            }
        }
        tv.setText("");
        tvAus.setText(anzeigeText);
        text = "";
    }
    public void clickTeilen(View v){
        Log.d(this.getClass().getName(),  "geteilt");
        String text = tv.getText().toString();
        int time = 1;
        for(int i = 0; i< time;i++){
            if(numbers[i] == "" && zeichen[i] == ""){
                numbers[i] = text;
                zeichen[i] = "/";
                anzeigeText += text;
                anzeigeText += "/";
            }else{
                time += 1;
            }
        }
        tv.setText("");
        tvAus.setText(anzeigeText);
        text = "";
    }
    public void clickGleich(View v){
        Log.d(this.getClass().getName(),  "ergebnis");
        String text = tv.getText().toString();
        int time = 1;
        for(int i = 0; i< time;i++){
            if(numbers[i] == "" && zeichen[i] == ""){
                numbers[i] = text;
                anzeigeText += text;
            }else{
                time += 1;
            }
        }
        tvAus.setText(anzeigeText);
        text = "";
        tv.setText("");
        for (int i = 0; i < numbers.length; i++){
            if(zeichen[i].contains("x")){
                double a = Integer.parseInt(numbers[i]);
                double b = Integer.parseInt(numbers[i+1]);
                double c = a * b;
                numbers[i+1] = String.valueOf(c);
                numbers[i]= "";
                zeichen[i]= "";
            }else if(zeichen[i].contains("/")){
                double a = Double.parseDouble(numbers[i]);
                double b = Double.parseDouble(numbers[i+1]);
                double c = a / b;
                numbers[i+1] = String.valueOf(c);
                numbers[i]= "";
                zeichen[i]= "";
            }
        }
        for(int j = 0; j < zeichen.length;j++){
            if(!(zeichen[j].contains("*")) && !(zeichen[j].contains("/")) && j == zeichen.length-1){
                for(int s = 0; s < zeichen.length;s++) {
                    if (zeichen[s].contains("+")) {
                        for (int n = 0; n < numbers.length; n++) {
                            if (!(numbers[n].isEmpty())) {
                                for (int k = 1; k < zeichen.length -1; k++) {
                                    if (!(numbers[n+k] == "" && n+k <=numbers.length)) {
                                        double a = Double.parseDouble(numbers[n]);
                                        double b = Double.parseDouble(numbers[n+k]);
                                        double c = a + b;
                                        numbers[s] = "";
                                        numbers[s + k] = "";
                                        zeichen[s] = "";
                                        ergebnis += c;
                                    }
                                }
                            }
                        }
                    } else if (zeichen[s].contains("-")) {
                        for (int n = 0; n < numbers.length; n++) {
                            if (!(numbers[n].isEmpty())) {
                                for (int k = 1; k < zeichen.length - 1; k++) {
                                    if (!(numbers[n + k] == "" && n + k <= numbers.length)) {
                                        double a = Double.parseDouble(numbers[n]);
                                        double b = Double.parseDouble(numbers[n + k]);
                                        double c = a - b;
                                        numbers[s] = "";
                                        numbers[s + k] = "";
                                        zeichen[s] = "";
                                        ergebnis += c;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0 ; i < zeichen.length;i++){
            if(zeichen[i].isEmpty() && numbers[i].isEmpty()){
                count++;
                if(count == zeichen.length){
                    String finalErgebnis = String.valueOf(ergebnis);
                    tv.setText(finalErgebnis);
                }
            }else if(!numbers[i].isEmpty()){
                ergebnis += Double.parseDouble(numbers[i]);
                tv.setText(String.valueOf(ergebnis));
            }else{
                Log.d("Fehler:", "Etwas ist schief gelaufen!");
            }
        }
        numbers = null;
        zeichen = null;
        count = 0;
        ergebnis = 0.0;
        anzeigeText = "";
        text = "";
    }
}
