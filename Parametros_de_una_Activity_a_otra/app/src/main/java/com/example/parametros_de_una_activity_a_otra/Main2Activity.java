package com.example.parametros_de_una_activity_a_otra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView tv_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_nombre = (TextView)findViewById(R.id.tv_nombre);

        String dato = getIntent().getStringExtra("dato");

        tv_nombre.setText("Hola "+dato);

    }

    public void Regresar (View view){

        Intent regresar = new Intent(this,MainActivity.class);
        startActivity(regresar);


    }

}
