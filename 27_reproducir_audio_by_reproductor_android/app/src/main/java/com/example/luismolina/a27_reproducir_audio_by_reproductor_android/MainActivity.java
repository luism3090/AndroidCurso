package com.example.luismolina.a27_reproducir_audio_by_reproductor_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnReproducir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReproducir = (Button)findViewById(R.id.btnEjecutar);

        btnReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data1 = Uri.parse("file:///sdcard"+"/stressed.mp3");
                Log.i("dato1:",String.valueOf(data1));
                intent.setDataAndType(data1,"audio/mp3");
                startActivity(intent);




            }
        });


    }
}
