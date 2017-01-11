package com.example.luismolina.a11_lanzar_un_segundo_activity_p1;

/*
*
* Realizar un programa que contenga dos Activity. En el primero que solicite el
* ingreso de una clave (control Password) Si ingresa la clave "abc123" activar el segundo
* Activity mostrando en un TextView un mensaje de bienvenida (mostrar en Toast si se ingresa
* la clave incorrecta en el primer Activity).
* */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etClave;
    private Button btnVerificar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etClave = (EditText)findViewById(R.id.etClave);
       btnVerificar = (Button)findViewById(R.id.btnVerificar);

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String clave = etClave.getText().toString();

                if(clave.trim().length() > 0)
                {
                    if(clave.equals("abc123"))
                    {
                        Intent i = new Intent(getApplicationContext(),Bienvenido.class);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "La clave ingresada no es correcta", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Debe ingresar su clave", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


}
