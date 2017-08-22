package com.example.uehara1414.androidsizeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Debug;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String NECK = "NECK";
    private static final String SLEEVE = "SLEEVE";
    private static final String WAIST = "WAIST";
    private static final String INSERM = "INSERM";

    private EditText editNeck;
    private EditText editSleeve;
    private EditText editWaist;
    private EditText editInserm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        editNeck = (EditText)findViewById(R.id.neck);
        editSleeve = (EditText)findViewById(R.id.sleeve);
        editWaist = (EditText)findViewById(R.id.waist);
        editInserm = (EditText)findViewById(R.id.inseam);

        editNeck.setText(sp.getString(NECK, ""));
        editSleeve.setText(sp.getString(SLEEVE, ""));
        editWaist.setText(sp.getString(WAIST, ""));
        editInserm.setText(sp.getString(INSERM, ""));

        findViewById(R.id.height_button)
                .setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Log.d("I", "onClick");
                        Intent intent = new Intent(MainActivity.this, HeightActivity.class);
                        startActivity(intent);
                    }
                });
    }

    public void onSaveTapped(View view){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(NECK, editNeck.getText().toString());
        editor.putString(SLEEVE, editSleeve.getText().toString());
        editor.putString(WAIST, editWaist.getText().toString());
        editor.putString(INSERM, editInserm.getText().toString());
        editor.commit();
    }
}
