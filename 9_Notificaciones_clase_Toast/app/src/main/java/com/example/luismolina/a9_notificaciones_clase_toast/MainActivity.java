package com.example.luismolina.a9_notificaciones_clase_toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private Button btnControlar;
    private int numeroAleatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero = (EditText)findViewById(R.id.etNumero);
        btnControlar = (Button)findViewById(R.id.btnControlar);

         numeroAleatorio = (int)(Math.random()*100001);
         String CadNumeroAleatorio = String.valueOf(numeroAleatorio);

        Toast msjNumAleatorio = Toast.makeText(getApplicationContext(),"El número es " + CadNumeroAleatorio,Toast.LENGTH_LONG);
        msjNumAleatorio.show();

        btnControlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cadNumIngresado = etNumero.getText().toString();
                int numeroIngresado = Integer.parseInt(cadNumIngresado);

                if(numeroIngresado == numeroAleatorio)
                {
                    Toast msjResultadoCorrecto =  Toast.makeText(getApplicationContext(), "Bien acertaste al número", Toast.LENGTH_LONG);
                    msjResultadoCorrecto.show();
                }
                else
                {
                    Toast msjResultadoIncorrecto =  Toast.makeText(getApplicationContext(), "Lo sentimos no acertaste al número", Toast.LENGTH_LONG);
                    msjResultadoIncorrecto.show();
                }

            }
        });


    }
}
