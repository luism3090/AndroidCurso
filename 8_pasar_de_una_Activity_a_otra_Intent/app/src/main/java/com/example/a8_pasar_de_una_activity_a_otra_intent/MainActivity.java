package com.example.a8_pasar_de_una_activity_a_otra_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Siguiente (View view){

        Intent siguiente = new Intent(this,SegundoActivity.class);
        startActivity(siguiente);

    }
}
