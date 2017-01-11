package com.example.luismolina.a14_almacenamiento_memoria_interna;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    public Button btnGuardar;
    public EditText etDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        etDatos = (EditText)findViewById(R.id.etDatos);

        String [] archivos = fileList();

       // Toast.makeText(getApplicationContext(),fileList()[0],Toast.LENGTH_SHORT).show();

        if(existe(archivos,"notas.txt"))
        {
            try
            {

                InputStreamReader archivo = new InputStreamReader(openFileInput("notas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();

                String todo="";
                //Toast.makeText(getApplicationContext(),br.readLine(),Toast.LENGTH_SHORT).show();
                while (linea!=null)
                {
                  //  Toast.makeText(getApplicationContext(),br.readLine(),Toast.LENGTH_SHORT).show();
                    todo=todo+linea+"\n";
                    linea=br.readLine();
                   /* btnGuardar.setText(String.valueOf(todo));*/
                   // Toast.makeText(getApplicationContext(),br.readLine(),Toast.LENGTH_SHORT).show();
                }

                br.close();
                archivo.close();

                etDatos.setText(todo);

            }
            catch (IOException e)
            {

            }

        }



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String datos = etDatos.getText().toString().trim();

                if(datos.length()>0)
                {
                    try
                    {
                        OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("notas.txt",Activity.MODE_PRIVATE));
                        archivo.write(datos);
                        archivo.flush();
                        archivo.close();

                        Toast.makeText(getApplicationContext(),"Los datos fueron grabados",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                    catch(IOException e)
                    {

                    }

                    //OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("notas.txt", Activity.MODE_PRIVATE));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Debe Ingresar los datos a guardar",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean existe(String[] archivos,String archbusca)
    {
        for(int f=0;f<archivos.length;f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }
}
