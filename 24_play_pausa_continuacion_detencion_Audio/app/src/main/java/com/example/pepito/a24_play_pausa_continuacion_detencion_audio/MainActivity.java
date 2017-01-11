package com.example.pepito.a24_play_pausa_continuacion_detencion_audio;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar,btnPausar, btnContinuar, btnDetener, btnIniciarNoCircular;
    MediaPlayer mp;
    int posicion = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = (Button)findViewById(R.id.btnIniciar);
        btnPausar = (Button)findViewById(R.id.btnPausar);
        btnContinuar = (Button)findViewById(R.id.btnContinuar);
        btnDetener = (Button)findViewById(R.id.btnDetener);
        btnIniciarNoCircular = (Button)findViewById(R.id.btnIniciarNoCircular);


        btnIniciar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                destruir();
                mp = MediaPlayer.create(getApplicationContext(),R.raw.stressed);
                mp.start();
                String inicioCircular = btnIniciarNoCircular.getText().toString();
                if(inicioCircular.equals("No reproducir en forma circular"))
                {
                    mp.setLooping(false);  // desactivar la repeticion del reproductor
                }
                else
                {
                    mp.setLooping(true); // activar la repeticion del reproductor
                }
            }
        });

        btnPausar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               if(mp!=null && mp.isPlaying())
               {
                   posicion = mp.getCurrentPosition();  // carga la posicion en la que se quedo el reproductor al estar reproduciendo el archivo de musica getCurrentPosition()
                   mp.pause();
               }
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp!=null && mp.isPlaying()==false)
                {
                    mp.seekTo(posicion); // consigue la posicion en la que se quedo la reproduccion de la musica seekTo()
                    mp.start();
                }
            }
        });

        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null)
                {
                    mp.stop();
                    posicion = 0;
                }
            }
        });

        btnIniciarNoCircular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp!=null)
                {
                    mp.stop();
                    posicion =0;
                }

                String formaCircular = btnIniciarNoCircular.getText().toString();
                if(formaCircular.equals("No reproducir en forma circular"))
                {
                    btnIniciarNoCircular.setText("Reproducir en forma circular");
                }
                else
                {
                    btnIniciarNoCircular.setText("No reproducir en forma circular");
                }

            }
        });


    }

    public void destruir()
    {
        if(mp!=null)
        {
            mp.release(); // liberar recursos del reproductor ( metodo release )
        }
    }

}
