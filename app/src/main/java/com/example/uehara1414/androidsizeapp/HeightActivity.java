package com.example.uehara1414.androidsizeapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class HeightActivity extends AppCompatActivity {
    public static final String HEIGHT = "HEIGHT";
    private TextView myHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        myHeight = (TextView)findViewById(R.id.my_height);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final int height = pref.getInt(HEIGHT, 160);
        myHeight.setText(String.valueOf(height));

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Spinner spinner = (Spinner)adapterView;
                        String item = spinner.getSelectedItem().toString();
                        if(!item.isEmpty()){
                            myHeight.setText(item);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

        SeekBar seekBar = (SeekBar)findViewById(R.id.seek_bar);
        seekBar.setProgress(height);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        String value = String.valueOf(i);
                        myHeight.setText(value);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                        RadioButton radioButton = (RadioButton)findViewById(i);
                        String value = radioButton.getText().toString();
                        myHeight.setText(value);
                    }
                }
        );

    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(HEIGHT, Integer.parseInt(myHeight.getText().toString()));
        editor.commit();
    }

}
