package com.example.luismolina.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero1, etNumero2;
    private TextView tvResultado;
    private RadioButton rdSumar, rdRestar;
    private Button btnCalcular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero1 = (EditText)findViewById(R.id.etNumero1);
        etNumero2 = (EditText)findViewById(R.id.etNumero2);
        tvResultado = (TextView)findViewById(R.id.tvResultado);
        rdSumar = (RadioButton)findViewById(R.id.rdSumar);
        rdRestar = (RadioButton)findViewById(R.id.rdRestar);
        btnCalcular = (Button)findViewById(R.id.btnCalcular);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dato1 = etNumero1.getText().toString();
                String dato2 = etNumero2.getText().toString();

                int valor1 = Integer.parseInt(dato1);
                int valor2 = Integer.parseInt(dato2);

                int operacion = 0;


                if(rdSumar.isChecked())
                {
                    operacion = valor1 + valor2;
                    String resultado = String.valueOf(operacion);
                    tvResultado.setText(resultado);

                }
                else
                {
                    if(rdRestar.isChecked())
                    {
                        operacion = valor1 - valor2;
                        String resultado = String.valueOf(operacion);
                        tvResultado.setText(resultado);
                    }
                }

            }
        });




    }




}
