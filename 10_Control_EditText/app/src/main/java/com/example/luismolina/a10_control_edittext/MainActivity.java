package com.example.luismolina.a10_control_edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnVerificar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword  = (EditText)findViewById(R.id.etPassword);
        btnVerificar = (Button)findViewById(R.id.btnVerificar);

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(username.length()==0)
                {
                    Toast msjUsername = Toast.makeText(getApplicationContext(),"Ingrese su nombre de usuario",Toast.LENGTH_LONG);
                    msjUsername.show();
                    etUsername.requestFocus();
                }
                else
                {

                    if(password.length()==0)
                    {
                        Toast msjPassword = Toast.makeText(getApplicationContext(),"Ingrese su password",Toast.LENGTH_LONG);
                        msjPassword.show();
                        etPassword.requestFocus();
                    }
                    else
                    {
                        Toast msjTodoOk = Toast.makeText(getApplicationContext(),"Datos llenados correctamente",Toast.LENGTH_LONG);
                        msjTodoOk.show();
                    }

                }


            }
        });


    }
}
