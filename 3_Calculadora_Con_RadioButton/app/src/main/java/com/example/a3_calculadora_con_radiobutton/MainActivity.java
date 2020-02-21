package com.example.a3_calculadora_con_radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView tvr;
    private RadioButton rb_sumar;
    private RadioButton rb_restar;
    private RadioButton rb_dividir;
    private RadioButton rb_multilicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.txt_num1);
        et2 = (EditText)findViewById(R.id.txt_num2);
        tvr = (TextView)findViewById(R.id.tv_resultado);
        rb_sumar = (RadioButton)findViewById(R.id.rb_sumar);
        rb_restar = (RadioButton)findViewById(R.id.rb_restar);
        rb_dividir = (RadioButton)findViewById(R.id.rb_dividir);
        rb_multilicar = (RadioButton)findViewById(R.id.rb_multiplicar);


    }


    public void Calcular(View view){

        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        if(valor1.equals("") || valor2.equals("")){

            Toast.makeText(this ,"Debe ingresar los valores",Toast.LENGTH_SHORT).show();

        }
        else{

            int num1 = Integer.parseInt(valor1);
            int num2 = Integer.parseInt(valor2);

            String resultado = "";

            if(rb_sumar.isChecked()==true){

                int suma = num1 + num2;

                resultado = String.valueOf(suma);

                tvr.setText(resultado);

            }
            else if(rb_restar.isChecked()==true){

                int resta = num1 - num2;

                resultado = String.valueOf(resta);

                tvr.setText(resultado);
            }
            else if(rb_dividir.isChecked()==true){

                if(num2 !=0){

                    int division = num1 / num2;

                    resultado = String.valueOf(division);

                    tvr.setText(resultado);
                }
                else{
                    Toast.makeText(this ,"No se puede dividir entre 0",Toast.LENGTH_SHORT).show();
                }


            }
            else if(rb_multilicar.isChecked()==true){
                int multiplicacion = num1 * num2;

                resultado = String.valueOf(multiplicacion);

                tvr.setText(resultado);
            }
        }






    }


}
