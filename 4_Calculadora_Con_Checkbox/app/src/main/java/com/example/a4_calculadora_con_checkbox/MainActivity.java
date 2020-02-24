package com.example.a4_calculadora_con_checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText et_num1;
    private EditText et_num2;
    private CheckBox chk_sumar;
    private CheckBox chk_restar;
    private TextView tv_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_num1 = (EditText)findViewById(R.id.et_num1);
        et_num2 = (EditText)findViewById(R.id.et_num2);

        chk_sumar = (CheckBox) findViewById(R.id.chk_sumar);
        chk_restar = (CheckBox)findViewById(R.id.chk_restar);

        tv_resultado = (TextView)findViewById(R.id.tv_resultado);


    }

    public void Calcular (View view) {

        String valor1 = et_num1.getText().toString();
        String valor2 = et_num2.getText().toString();

        if(valor1.equals("") || valor2.equals("")){
            Toast.makeText(this, "Debe ingresar los valores",Toast.LENGTH_LONG).show();
        }
        else{
            int num1 = Integer.parseInt(valor1);
            int num2 = Integer.parseInt(valor2);

            String cad_resultado = "";

            if(chk_sumar.isChecked() == true){

                int suma = num1 + num2;

                cad_resultado += "La suma es " +  String.valueOf(suma)+"\n";

            }
            if(chk_restar.isChecked() == true){

                int resta = num1 - num2;

                cad_resultado += "La resta es " +  String.valueOf(resta);

            }

            tv_resultado.setText(cad_resultado);
        }

    }

}
