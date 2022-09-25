package com.example.meujogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button buttonteste;
    private TextView txtTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Mitr-SemiBold.ttf");
        TextView txtTitle = findViewById(R.id.txt_title);

        txtTitle.setTypeface(customFont);
        buttonteste= findViewById(R.id.button_jogar_teste);

        buttonteste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        MainActivity2.class
                );
                startActivity(intent);
            }
        });
    }
}







