package com.luisame.a13_almacenar_datos_clase_sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etMail;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMail = (EditText)findViewById(R.id.etMail);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);

        SharedPreferences prefe = getSharedPreferences("datos",Context.MODE_PRIVATE);
        etMail.setText(prefe.getString("mail",""));

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = etMail.getText().toString().trim();

                if(mail.length()>0)
                {
                    SharedPreferences preferencia = getSharedPreferences("datos",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencia.edit();
                    editor.putString("mail",mail);
                    editor.commit();
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Debe ingresar su mail",Toast.LENGTH_LONG);
                }



            }
        });

    }
}
