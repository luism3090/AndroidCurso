package com.example.luismolina.a26_reproduccion_de_audio_archivo_internet;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnReproducir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReproducir = (Button)findViewById(R.id.btnReproducir);

        btnReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = new MediaPlayer();
                try
                {
                    mp.setDataSource("http://www.javaya.com.ar/recursos/gato.mp3");
                    mp.prepare();
                    mp.start();
                }
                catch (IOException e)
                {
                    Toast.makeText(getApplicationContext(),"No se pudo reproducir el archivo",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
