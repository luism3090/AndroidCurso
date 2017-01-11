package com.luisame.a12_un_segundo_activity_pasar_parametros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etWeb;
    private Button btnVerWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeb = (EditText)findViewById(R.id.etWeb);
        btnVerWeb = (Button)findViewById(R.id.btnVerWeb);

        btnVerWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),"Hola",Toast.LENGTH_LONG).show();

                String rutaWeb = etWeb.getText().toString();

                Intent i = new Intent(getApplicationContext(),Actividad2.class);
                i.putExtra("direccion",rutaWeb);
                startActivity(i);

            }
        });



    }
}
