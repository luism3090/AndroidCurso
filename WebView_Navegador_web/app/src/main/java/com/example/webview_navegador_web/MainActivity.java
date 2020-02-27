package com.example.webview_navegador_web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    private Spinner spin_paginas;



    String [] spi_paginas_urls = {  "",
            "https://www.facebook.com/",
            "https://www.instagram.com/",
            "https://www.youtube.com/",
            "https://www.tiktok.com/",
            "https://twitter.com/"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin_paginas = (Spinner)findViewById(R.id.spin_paginas);

        String [] spi_paginas_opciones = {  "Elija la red social",
                                                "Facebook",
                                                "Instagram",
                                                "Youtube",
                                                "TickTock",
                                                "Twitter"
                                        };

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,spi_paginas_opciones);
        spin_paginas.setAdapter(adapter);

        spin_paginas.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String pagina = parent.getItemAtPosition(position).toString();

        if(pagina.equals("Elija la red social"))
        {
            Toast.makeText(this,"Debe elegir una red social",Toast.LENGTH_SHORT).show();
        }
        else{

            //Toast.makeText(this,spi_paginas_urls[position].toString(),Toast.LENGTH_LONG).show();

            String url_elegida = spi_paginas_urls[position].toString();

            Intent enviar = new Intent(this,Main2Activity.class);

            enviar.putExtra("url",url_elegida);

            startActivity(enviar);


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
