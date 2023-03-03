package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista_vueltas;
    Button btn_iniciar, btn_reinicia, btn_para, btn_vueltas;
    Chronometer cronometro;
    Boolean correr = false;
    long detenerse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cronometro = findViewById(R.id.cronometro);
        btn_iniciar = findViewById(R.id.btn_iniciar);
        btn_reinicia = findViewById(R.id.btn_reinicia);
        btn_para = findViewById(R.id.btn_para);
        btn_vueltas = findViewById(R.id.btn_vueltas);
        lista_vueltas = findViewById(R.id.lista_vueltas);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarCronometro();
            }
        });

        btn_para.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pararCronometro();
            }
        });

        btn_reinicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarCronometro();
            }
        });

        btn_vueltas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vueltas();
            }
        });
    }

    private void reiniciarCronometro() {
        if (correr) {
            pararCronometro();
        }
        cronometro.setBase(SystemClock.elapsedRealtime());
        detenerse = 0;
        laps.clear();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, laps);
        ListView listLaps = findViewById(R.id.lista_vueltas);
        listLaps.setAdapter(adapter);
    }


    private void pararCronometro() {
        correr = correr ? false : correr;
        cronometro.stop();
        detenerse = SystemClock.elapsedRealtime() - cronometro.getBase();
    }

    private void iniciarCronometro() {
        correr = correr ? correr : true;
        cronometro.setBase(SystemClock.elapsedRealtime() - detenerse);
        cronometro.start();
    }

    ArrayList<String> laps = new ArrayList<>();

    public void vueltas() {
        String tiempoActual = cronometro.getText().toString();
        laps.add(tiempoActual);

        // Crear lista de vueltas con número de vuelta y tiempo
        ArrayList<String> listaVueltas = new ArrayList<>();
        for (int i = 0; i < laps.size(); i++) {
            String vuelta = "Vuelta " + (i+1) + ": " + laps.get(i);
            listaVueltas.add(vuelta);
        }

        // Actualizar adaptador y vista de lista de vueltas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaVueltas);
        ListView listLaps = findViewById(R.id.lista_vueltas);
        listLaps.setAdapter(adapter);
    }
}