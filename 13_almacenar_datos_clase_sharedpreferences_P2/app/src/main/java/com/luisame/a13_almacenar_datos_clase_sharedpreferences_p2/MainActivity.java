package com.luisame.a13_almacenar_datos_clase_sharedpreferences_p2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.RippleDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNombrePersona, etDatos;
    private Button btnGrabar, btnRecuperar,  btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombrePersona = (EditText)findViewById(R.id.etNombrePersona);
        etDatos = (EditText)findViewById(R.id.etDatos);

        btnGrabar = (Button)findViewById(R.id.btnGrabar);
        btnRecuperar = (Button)findViewById(R.id.btnRecuperar);
        btnLimpiar = (Button)findViewById(R.id.btnLimpiar);

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDatos.setText("");
                etNombrePersona.setText("");
            }
        });


        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombrePersona = etNombrePersona.getText().toString().trim();
                String datosPersona = etDatos.getText().toString().trim();

                String valida = validarCampos(nombrePersona,datosPersona);

                //Toast.makeText(getApplicationContext(),valida,Toast.LENGTH_LONG).show();

                if(valida.equals("OK"))
                {
                    SharedPreferences preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString("nombrePersona",nombrePersona);
                    editor.putString("datosPersona",datosPersona);
                    editor.commit();
                    Toast.makeText(getApplicationContext(),"Datos grabados",Toast.LENGTH_LONG).show();
                }

            }

            public String validarCampos(String nombrePersona,String datosPersona)
            {
                String valida = "OK";
                if(nombrePersona.length()==0)
                {
                    valida="Error";
                    Toast.makeText(getApplicationContext(),"Debe ingresar el nombre de la persona",Toast.LENGTH_LONG).show();
                    etNombrePersona.requestFocus();
                }
                else
                {
                    if(datosPersona.length()==0)
                    {
                        valida="Error";
                        Toast.makeText(getApplicationContext(),"Debe ingresar los datos de la persona",Toast.LENGTH_LONG).show();
                        etDatos.requestFocus();
                    }
                }

                return valida;

            }

        });




        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefe = getSharedPreferences("agenda",Context.MODE_PRIVATE);
                String  recNombrePersona = prefe.getString("nombrePersona","");
                String  recDatosPersona = prefe.getString("datosPersona","");
                if(recNombrePersona.length()>0)
                {
                    etNombrePersona.setText(recNombrePersona);

                    if(recDatosPersona.length()> 0)
                    {
                        etDatos.setText(recDatosPersona);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"No se a encontrado los datos de la persona",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {


                    Toast.makeText(getApplicationContext(),"No se a encontrado el nombre de la persona",Toast.LENGTH_LONG).show();
                }


            }

        });


    }
}
