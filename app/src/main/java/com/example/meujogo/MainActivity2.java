package com.example.meujogo;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.gridlayout.widget.GridLayout;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class MainActivity2 extends AppCompatActivity {


    private static Context mContext;

    int fundo_cinza = R.drawable.textview_border;
    int fundo_amarelo = R.drawable.textview_amarelo;
    int fundo_verde = R.drawable.textview_verde;


    GridLayout grid;
    Button button;
    TextInputEditText input;
    TextView winTitle;

    public boolean[] encontrados = new boolean[] {false, false, false, false, false};

    int lifes = 0;

    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = findViewById(R.id.grid_layout);
        button = findViewById(R.id.button_guess);
        input = findViewById(R.id.input_text_guess);

        answer = getRandomWord(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPalavra(input.getText().toString());
            }
        });
    }


    private void addPalavra(String palavra) {

        encontrados = new boolean[] {false, false, false, false, false};
        if ((palavra.trim().length() != 5) || lifes == 5)  {
            return;
        }

        int ind = 0;
        ArrayList<Integer> resultado = verifier(palavra);

        for (char letra: palavra.toCharArray()) {
            grid.addView(createLabelView(Character.toString(letra), resultado.get(ind)));
            ind++;
        }

        if (palavra.toUpperCase().equals(answer)) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    MainActivity3.class
            );
            intent.putExtra("msg", "Você Ganhou");
            startActivity(intent);
//
            return;
        }

        lifes++;


        if (lifes == 5) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    MainActivity3.class
            );
            intent.putExtra("msg", "Você Perdeu");
            intent.putExtra("palavra", answer);
            startActivity(intent);
        }
    }

    protected TextView createLabelView(String texto, int cor) {

        texto = texto.toUpperCase();
        TextView labelView = new TextView(this);
        labelView.setText(texto);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Mitr-SemiBold.ttf");
        labelView.setTypeface(customFont);
        labelView.setWidth(grid.getWidth()/6);
        labelView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        labelView.setTextSize(48);
        labelView.setTextColor(Color.parseColor("#fafaff"));
        labelView.setBackgroundResource(cor);

        return labelView;
    }

    private ArrayList<Integer> verifier(String resposta) {

        ArrayList<Integer> resultado = new ArrayList<>();
        resposta = resposta.toUpperCase();
        String certa = answer;
        int iterador = 0;

        for (char letra : resposta.toCharArray()) {

            if (certa.contains(letra + "")) {
                if (answer.toCharArray()[iterador] == letra){
                    resultado.add(fundo_verde);
                    encontrados[iterador] = true;
                    certa = certa.replaceFirst(Character.toString(letra), "");
                } else {
                    resultado.add(fundo_amarelo);
                }
            } else {
                resultado.add(fundo_cinza);
            }
            System.out.println(certa);
            iterador++;
        }
        return resultado;
    }


    static int countOccurrences(String str, String word)
    {
        // split the string by spaces in a
        String a[] = str.split(" ");

        // search for pattern in a
        int count = 0;
        for (int i = 0; i < a.length; i++)
        {
            // if match found increase count
            if (word.equals(a[i]))
                count++;
        }

        return count;
    }


    static String getRandomWord(Context context) {
        ArrayList<String> palavras = new ArrayList<>();

        mContext = context;

        AssetManager am = mContext.getAssets();

        try {
            InputStream is = am.open("data/words.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null)
                palavras.add(line.toUpperCase());


            Random r = new Random();
            int rItem = r.nextInt(palavras.size());
            return palavras.get(rItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "TESTE";

    }



}