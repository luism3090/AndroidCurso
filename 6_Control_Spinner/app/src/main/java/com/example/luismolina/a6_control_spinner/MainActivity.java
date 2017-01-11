package com.example.luismolina.a6_control_spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etNum1,etNum2;
    private Spinner spinner;
    private Button btnCalcular;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = (EditText)findViewById(R.id.etNum1);
        etNum2 = (EditText)findViewById(R.id.etNum2);
        spinner = (Spinner)findViewById(R.id.spinner);
        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        tvResultado = (TextView)findViewById(R.id.tvResultado);

        String opciones[] = {"Sumar","Restar","Multiplicar","Dividir"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opciones);

        spinner.setAdapter(adapter);



        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String operacion = spinner.getSelectedItem().toString();

                String cadNum1 = etNum1.getText().toString();
                String cadNum2 = etNum2.getText().toString();

                int num1 = Integer.parseInt(cadNum1);
                int num2 = Integer.parseInt(cadNum2);

                int calcOperacion = 0;

                if(operacion.equals("Sumar"))
                {
                    calcOperacion = num1 + num2;
                }
                else
                {
                    if(operacion.equals("Restar"))
                    {
                        calcOperacion = num1 - num2;
                    }
                    else
                    {
                        if(operacion.equals("Multiplicar"))
                        {
                            calcOperacion = num1 * num2;
                        }
                        else
                        {
                            if(operacion.equals("Dividir"))
                            {
                                calcOperacion = num1 / num2;
                            }
                        }
                    }
                }

                String resultado = String.valueOf(calcOperacion);
                tvResultado.setText(resultado);


            }
        });




    }
}
