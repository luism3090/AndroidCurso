package com.example.a6_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_resultado;
    private ListView lv_datos;

    private String nombres [] = {"Samuel","Valentina","Santiago","Alejandro","Valeria","Benjamin","Gerardo","Carlos","David","Sofia"};
    private String edades [] = {"18","25","32","17","24","20","27","15","19","23"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_resultado = (TextView)findViewById(R.id.tv_resultado);
        lv_datos = (ListView)findViewById(R.id.lv_datos);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,nombres);
        lv_datos.setAdapter(adapter);

        lv_datos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_resultado.setText("La edad de "+lv_datos.getItemAtPosition(position) + " es  "+ edades[position] + " años.");
            }
        });


    }
}
