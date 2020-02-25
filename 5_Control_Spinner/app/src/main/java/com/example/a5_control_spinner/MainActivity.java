package com.example.a5_control_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private Spinner spi_producto;
    private TextView tv_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spi_producto = (Spinner)findViewById(R.id.spi_productos);
        tv_resultado = (TextView)findViewById(R.id.tv_resultado);

        String [] spi_producto_opciones = {"Elegir aquí","Xbox 360","Playstation 4","Nintendo Switch","Iphone 11","Samsung galaxy 10"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,spi_producto_opciones);
        spi_producto.setAdapter(adapter);

        spi_producto.setOnItemSelectedListener(this);



    }

    public void Aceptar(View view){

       String producto = spi_producto.getSelectedItem().toString();

       if(producto.equals("Elegir aquí"))
       {
           Toast.makeText(this,"Debe elegir un producto",Toast.LENGTH_SHORT).show();
       }
        else{

           tv_resultado.setText("El producto elegido es: " + producto);
       }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String producto = parent.getItemAtPosition(position).toString();

        if(producto.equals("Elegir aquí"))
        {
            Toast.makeText(this,"Debe elegir un producto",Toast.LENGTH_SHORT).show();
        }
        else{

            tv_resultado.setText("El producto elegido es: " + producto);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
