package com.example.a11_almacenamiento_de_datos_la_clase_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    private EditText txt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_email = (EditText)findViewById(R.id.txt_email);

        SharedPreferences mipreferencia = getSharedPreferences("datos", Context.MODE_PRIVATE);

        txt_email.setText(mipreferencia.getString("mail",""));

    }

    public  void guardar(View v){



    }


}
