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


        // codigo para indicarle a android que invocamos un archivo llamado datos de la clase SharedPreferences
        SharedPreferences mipreferencia1 = getSharedPreferences("datos", Context.MODE_PRIVATE);


        // codigo para obtener el valor guardado en sharedpreferences  y colocarlo en la caja de texto
        txt_email.setText(mipreferencia1.getString("mail",""));

    }

    public  void guardar(View v){

        // codigo para indicarle a android que invocamos un archivo llamado datos de la clase SharedPreferences
        SharedPreferences mipreferencia2 = getSharedPreferences("datos", Context.MODE_PRIVATE);

        // codigo para poder editar el archivo datos de la clase SharedPreferences
        SharedPreferences.Editor obj_editor  = mipreferencia2.edit();

        // codigo para indicarle el dato a guardar del archivo datos de la clase SharedPreferences
        // el dato a guardar se almacenará en el atributo mail con el valor que contenga la caja de texto mail
        obj_editor.putString("mail",txt_email.getText().toString());


        // Y por ultimo debemos usar el metodo commit para confirmar la informacion a guardar
        obj_editor.commit();

        // codigo para indicarle a android que la activity se cierre
        finish();

    }


}
