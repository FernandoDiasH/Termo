package com.example.meujogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        String MSG = intent.getStringExtra("msg");
        String resposta = intent.getStringExtra("palavra");

        TextView msg = findViewById(R.id.msg);
        TextView Resposta = findViewById(R.id.resposta);



        Button voltar = findViewById(R.id.jogar_de_novo);
        Button sair = findViewById(R.id.sair);

        if (resposta  != null){
            Resposta.setText("Resposta: " + resposta);
        }

        msg.setText(MSG);

        sair.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        getApplicationContext(),
                        MainActivity.class
                ) ;
                startActivity(intent1);
            }
        }));

        voltar.setOnClickListener(new View.OnClickListener() {
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