package com.example.luismolina.a26_reproduccion_de_audio_archivo_internet_p2;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity  implements  MediaPlayer.OnPreparedListener{

    Button btnReproducir;
    private MediaPlayer mp;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReproducir = (Button)findViewById(R.id.btnReproducir);

        btnReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = new MediaPlayer();
                mp.setOnPreparedListener(MainActivity.this);



                try
                {
                    //Log.i("dato1", String.valueOf(mp));
                    mp.setDataSource("http://www.javaya.com.ar/recursos/gato.mp3");
                    //Log.i("dato2", String.valueOf(mp));
                    mp.prepareAsync();
                }
                catch (IOException e)
                {

                }
                Toast.makeText(getApplicationContext(), "Espere un momento mientras se carga el mp3", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        //Log.i("dato3", String.valueOf(mp));
        mp.start();
    }
}
