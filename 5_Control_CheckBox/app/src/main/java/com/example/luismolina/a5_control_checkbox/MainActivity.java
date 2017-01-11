package com.example.luismolina.a5_control_checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText etNumero1,etNumero2;
    private CheckBox chkSumar, chkRestar;
    private Button btnCalcular;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNumero1 = (EditText)findViewById(R.id.etNumero1);
        etNumero2 = (EditText)findViewById(R.id.etNumero2);

        chkSumar = (CheckBox)findViewById(R.id.chkSumar);
        chkRestar = (CheckBox)findViewById(R.id.chkRestar);

        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        tvResultado = (TextView)findViewById(R.id.tvResultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dato1 = etNumero1.getText().toString();
                String dato2 = etNumero2.getText().toString();

                int valor1 = Integer.parseInt(dato1);
                int valor2 = Integer.parseInt(dato2);

                int operacion =0;
                String resultado = "";


                if(chkSumar.isChecked())
                {
                    operacion = valor1+valor2;
                    String suma = String.valueOf(operacion);
                    resultado = "La suma es "+suma+ " ";
                }

                if(chkRestar.isChecked())
                {
                    operacion = valor1-valor2;
                    String resta = String.valueOf(operacion);
                    resultado += "La resta es " +resta+" ";
                }


                tvResultado.setText(resultado);

            }
        });



    }
}
