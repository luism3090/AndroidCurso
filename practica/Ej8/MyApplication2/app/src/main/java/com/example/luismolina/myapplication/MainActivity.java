package com.example.luismolina.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvCantPuntaje;
    private EditText etNumero;
    private Button btnVerificar;

    int numAleatorio = 0 ;

    String cadNumIngresado = "";
    int numeroIngresado = 0;

    int puntaje=0;
    String cadPuntaje="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero = (EditText)findViewById(R.id.etNumero);
        btnVerificar = (Button)findViewById(R.id.btnVerificar);
        tvCantPuntaje = (TextView)findViewById(R.id.tvCantPuntaje);

        numAleatorio = (int)(Math.random()*500+1);


        getPuntajeInicial();

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                cadNumIngresado = etNumero.getText().toString().trim();

                if(cadNumIngresado.length()>0)
                {
                    numeroIngresado = Integer.parseInt(cadNumIngresado);

                    if(numeroIngresado>numAleatorio)
                    {
                        Toast.makeText(getApplicationContext(),"Ingrese un número MENOR", Toast.LENGTH_SHORT).show();
                        etNumero.setText("");
                    }
                    else if(numeroIngresado<numAleatorio)
                    {
                        Toast.makeText(getApplicationContext(),"Ingrese un número MAYOR", Toast.LENGTH_SHORT).show();
                        etNumero.setText("");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Muy Bien has ganado le atinaste al número, el número a adivinar era: "+String.valueOf(numAleatorio), Toast.LENGTH_LONG).show();


                        cadPuntaje = tvCantPuntaje.getText().toString();
                        puntaje = Integer.parseInt(cadPuntaje);
                        puntaje ++;
                        cadPuntaje = String.valueOf(puntaje);
                        tvCantPuntaje.setText(cadPuntaje);

                        SharedPreferences preferencias=getSharedPreferences("FilePuntaje", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferencias.edit();
                        editor.putString("puntaje", cadPuntaje);
                        editor.commit();
                        etNumero.setText("");

                        Toast.makeText(getApplicationContext(),"Puedes seguir jugando solo ingresa un número de nuevo", Toast.LENGTH_LONG).show();
                        numAleatorio = (int)(Math.random()*500+1);

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Debe ingresar un número", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void getPuntajeInicial()
    {
        SharedPreferences prefere = getSharedPreferences("FilePuntaje", Context.MODE_PRIVATE);
        tvCantPuntaje.setText(prefere.getString("puntaje","0"));
    }
}
