package com.example.libraryview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    int fontSize;
    public static final String FONT = "FONT_SIZE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        //shared preferences for font and current book reading/read
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        //setting text views
        fontSize = settings.getInt("FONT", 20);
        TextView currentFont = findViewById( R.id.tv_curFontSizeNum );
        currentFont.setText(""+fontSize);

        String last = settings.getString("LAST_BOOK", " ");
        TextView currentB = findViewById(R.id.tv_curbook);
        currentB.setText(last+"");

        // handle update button
        Button updateButton = findViewById( R.id.btn_update );
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the value from the switch
                EditText newFontSize = findViewById(R.id.et_newFontSize);
                fontSize = Integer.parseInt(newFontSize.getText().toString());
                if(fontSize < 20 || fontSize > 50){
                    Toast.makeText(getApplicationContext(),"Font must be between 20 and 50",Toast.LENGTH_SHORT).show();
                    fontSize=20;
                    return;
                }
                //shared preference input
                editor.putInt("FONT", fontSize);
                editor.commit();

                finish();
            }
        });
    }

}