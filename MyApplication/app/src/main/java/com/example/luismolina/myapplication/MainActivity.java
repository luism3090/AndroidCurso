package com.example.luismolina.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText etNumero1, etNumero2;
    private TextView tvResultado;
    private Button btnOperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNumero1 = (EditText)findViewById(R.id.etNumero1);
        etNumero2 = (EditText)findViewById(R.id.etNumero2);
        tvResultado = (TextView)findViewById(R.id.tvResultado);
        btnOperar = (Button)findViewById(R.id.btnOperar);

       btnOperar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String dato1 = etNumero1.getText().toString();
               String dato2 = etNumero2.getText().toString();

               int valor1 = Integer.parseInt(dato1);
               int valor2 = Integer.parseInt(dato2);

               int suma = valor1 + valor2;

               String resultado = String.valueOf(suma);

               tvResultado.setText(resultado);

           }
       });




























        /*

        public EditText etNumero1 , etNumero2;
    public TextView tvResultado;
    public  Button btnOperar;

        etNumero1 = (EditText)findViewById(R.id.etNumero1);
        etNumero2 = (EditText)findViewById(R.id.etNumero2);
        tvResultado = (TextView)findViewById(R.id.tvResultado);


        btnOperar = (Button)findViewById(R.id.btnOperar);

        btnOperar.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                String dato1 = etNumero1.getText().toString();
                String dato2 = etNumero2.getText().toString();

                int valor1 = Integer.parseInt(dato1);
                int valor2 = Integer.parseInt(dato2);

                int suma = valor1 + valor2;

                String resultado = String.valueOf(suma);

                tvResultado.setText(resultado);

            }
        });
            */




    }




}
