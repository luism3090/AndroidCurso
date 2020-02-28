package com.example.a12_agenda_de_contactos_con_la_clase_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txt_nombre;
    private EditText txt_telefono;
    private EditText txt_direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_nombre = (EditText)findViewById(R.id.txt_nombre);
        txt_telefono = (EditText)findViewById(R.id.txt_telefono);
        txt_direccion = (EditText)findViewById(R.id.txt_direccion);

    }


    public void Guardar(View view){

        String nombre = txt_nombre.getText().toString();
        String telefono = txt_telefono.getText().toString();
        String direccion = txt_direccion.getText().toString();

        if(nombre.equals("") || telefono.equals("") || direccion.equals("")){

            Toast.makeText(this,"Debe ingresar todos los datos de contacto",Toast.LENGTH_LONG).show();

        }
        else{

            // codigo para indicarle a android que invocamos un archivo llamado agenda de la clase SharedPreferences
            SharedPreferences mipreferencia = getSharedPreferences("agenda", Context.MODE_PRIVATE);


            // codigo para poder editar el archivo agenda de la clase SharedPreferences
            SharedPreferences.Editor obj_editor  = mipreferencia.edit();


            // codigo para indicarle el dato a guardar del archivo agenda de la clase SharedPreferences
            // el dato a guardar se almacenará en el atributo mail con el valor que contenga la caja de texto mail

            obj_editor.putString(nombre,txt_telefono.getText().toString()+"_"+txt_direccion.getText().toString());


            // Y por ultimo debemos usar el metodo commit para confirmar la informacion a guardar
            obj_editor.commit();

            Toast.makeText(this,"Contacto guardado correctamente",Toast.LENGTH_LONG).show();

            txt_nombre.setText("");
            txt_telefono.setText("");
            txt_direccion.setText("");

        }

    }

    public void Buscar(View view){

        String nombre = txt_nombre.getText().toString();

        if(nombre.equals("")){

            Toast.makeText(this,"Debe ingresar el nombre del contacto a buscar",Toast.LENGTH_LONG).show();

        }
        else{

            SharedPreferences mipreferencia = getSharedPreferences("agenda", Context.MODE_PRIVATE);
            String datos = mipreferencia.getString(nombre,"");

            if(datos.length()>0){

                String[] contacto = datos.split("_");

                txt_telefono.setText(contacto[0]);
                txt_direccion.setText(contacto[1]);

            }
            else{
                Toast.makeText(this,"No se ha encontrado ningun contacto",Toast.LENGTH_LONG).show();
                txt_telefono.setText("");
                txt_direccion.setText("");

            }
        }





    }


}
