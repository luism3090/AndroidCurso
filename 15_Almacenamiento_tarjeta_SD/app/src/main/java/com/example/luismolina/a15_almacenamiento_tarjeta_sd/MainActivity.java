package com.example.luismolina.a15_almacenamiento_tarjeta_sd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText etArchivo, etDatos;
    public Button btnGuardar, btnRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etArchivo = (EditText)findViewById(R.id.etArchivo);
        etDatos = (EditText)findViewById(R.id.etDatos);

        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnRecuperar = (Button)findViewById(R.id.btnRecuperar);



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String nameArchivo = etArchivo.getText().toString().trim();
                String datos = etDatos.getText().toString().trim();

                boolean error = false;

                error = validaForm(nameArchivo,datos);

                if(!error)
                {

                }

            }
        });

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameArchivo = etArchivo.getText().toString().trim();

                if(nameArchivo.length()>0)
                {

                }
                else
                {
                    etArchivo.requestFocus();
                    Toast.makeText(getApplicationContext(),"Ingrese el nombre del archivo a recuperar",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public boolean validaForm(String nameArchivo,String datos)
    {
        boolean error = false;

        if(nameArchivo.length()==0)
        {
            error = true;
            etArchivo.requestFocus();
            Toast.makeText(getApplicationContext(),"Ingrese el nombre del archivo a guardar",Toast.LENGTH_SHORT).show();
        }
        else if(datos.length()==0)
        {
            error = true;
            etDatos.requestFocus();
            Toast.makeText(getApplicationContext(),"Ingrese los datos a guardar",Toast.LENGTH_SHORT).show();
        }

        return error;
    }

}
