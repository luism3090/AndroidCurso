package com.example.parametros_de_una_activity_a_otra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText et_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre = (EditText)findViewById(R.id.et_nombre);
    }

    public void Enviar (View view){

        String nombre = et_nombre.getText().toString();

        if(nombre.equals("")){
            Toast.makeText(this,"Ingrese el nombre",Toast.LENGTH_LONG).show();
        }
        else{
            Intent enviar = new Intent(this,Main2Activity.class);

            enviar.putExtra("dato",et_nombre.getText().toString());

            startActivity(enviar);
        }



    }

}
